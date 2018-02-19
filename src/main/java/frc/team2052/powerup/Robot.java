package frc.team2052.powerup;

import com.first.team2052.lib.ControlLoop;
import com.first.team2052.lib.RevRoboticsPressureSensor;
import com.first.team2052.lib.vec.RigidTransform2d;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.auto.AutoModeRunner;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.FieldConfig;
import frc.team2052.powerup.subsystems.Controls;
import frc.team2052.powerup.subsystems.Elevator;
import frc.team2052.powerup.subsystems.Pickup;
import frc.team2052.powerup.subsystems.Ramp;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class Robot extends IterativeRobot {

    private ControlLoop controlLoop = null;
    private ControlLoop logLooper = null;
    private ControlLoop slowerLooper = null;
    private ControlLoop fieldLooper = null;

    private static DriveTrain driveTrain = null;
    private Pickup intake = null;
    private Controls controls = null;
    private Ramp ramp = null;
    private Elevator elevator = null;

    private AutoModeRunner autoModeRunner = null;
    private RobotState robotState = null;
    private DriveHelper driveHelper = null;
    private RobotStateEstimator stateEstimator = null;

    private PowerDistributionPanel pdp = null;
    private RevRoboticsPressureSensor revRoboticsPressureSensor = null;
    private Compressor compressor;

    private boolean firstIntakeButtonPressed;

    @Override
    public void robotInit() {
        driveTrain = DriveTrain.getInstance();
        driveHelper = new DriveHelper();
        controls = Controls.getInstance();
        //Camera.getInstance().init();

        //////THESE SUBSYSTEMS ARE FAULT TOLERANT/////
        /////// they will return null if they fail to create themselves////////
        intake = Pickup.getInstance();
        ramp = Ramp.getInstance();
        elevator = Elevator.getInstance();
        //////////////////////////////////////////////

        try {
            compressor = new Compressor();
            compressor.setClosedLoopControl(true);
        } catch (Exception exc) {
            System.out.println("DANGER: No compressor!");
        }

        pdp = new PowerDistributionPanel(Constants.kPDPId);

        //Control loops for auto and teleop
        controlLoop = new ControlLoop(Constants.kControlLoopPeriod);
        slowerLooper = new ControlLoop(Constants.kSlowControlLoopPeriod);
        fieldLooper = new ControlLoop(Constants.kSlowControlLoopPeriod);

        robotState = RobotState.getInstance();
        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        stateEstimator = RobotStateEstimator.getInstance();

        controlLoop.addLoopable(driveTrain.getLoopable());
        controlLoop.addLoopable(stateEstimator);

        if (elevator != null) {
            elevator.zeroSensor();
            controlLoop.addLoopable(elevator);
        }
        if (intake != null)
        {
            intake.pickupPositionStartingConfig();
        }

        //slowerLooper.addLoopable(VisionProcessor.getInstance());

        fieldLooper.addLoopable(new FieldConfig()); //FMS is not gaurenteed to give us the game data on first try, so loop until you get it

        //Logging for auto
        logLooper = new ControlLoop(1.0);
        logLooper.addLoopable(PositionLoggerLoopable.getInstance());

        revRoboticsPressureSensor = new RevRoboticsPressureSensor(0);

        AutoModeSelector.putToSmartDashboard();
        autoModeRunner = new AutoModeRunner();
    }

    @Override
    public void disabledInit() {
        controlLoop.stop();
        logLooper.stop();
        slowerLooper.stop();
        fieldLooper.stop();
        autoModeRunner.stop();
        zeroAllSensors();
    }

    @Override
    public void disabledPeriodic() {
        driveTrain.resetEncoders();
        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        System.gc();
    }

    @Override
    public void autonomousInit() {
        FieldConfig.reset();
        AutoPaths.Init();
        zeroAllSensors();
        Timer.delay(.25);

        if (elevator != null) {
            elevator.zeroSensor();
        }
        driveTrain.setOpenLoop(DriveSignal.NEUTRAL);  //put robot into don't move, no looper mode
        driveTrain.setBrakeMode(false);

        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        fieldLooper.start();
        logLooper.start();
        controlLoop.start();
        slowerLooper.start();

        AutoModeSelector.AutoModeDefinition currentAutoMode = AutoModeSelector.getAutoDefinition(); //creates a variable we can change
        boolean gameDataHasArrived = FieldConfig.hasGameData();
        double startGameDataCheck = Timer.getFPGATimestamp();
        while (!FieldConfig.hasGameData() && (Timer.getFPGATimestamp() - startGameDataCheck) < 3) { //wait upto 3 seconds to get the game data
            System.out.println("No Game Data in wait loop. " + FieldConfig.getGameData());
            Timer.delay(.25);
        }

        System.out.println("GAME DATA: " + FieldConfig.getGameData());
        System.out.println("Selected Auto Mode: " + AutoModeSelector.getAutoDefinition().name());
        System.out.println("Wait Time: " + AutoModeSelector.getWaitTime());
        System.out.println("Disabled Auto Position: " + AutoModeSelector.getDisabledAuto().name());

        if (!FieldConfig.hasGameData()) {
            System.out.println("WARNING GAME DATA NEVER ARRIVED!");

            switch (AutoModeSelector.getAutoDefinition()) {
                case LSTARTONLYSCALE:
                case LSTARTPERFERSCALE:
                case LSTARTPREFERSWITCH:
                case RSTARTONLYSCALE:
                case RSTARTPREFERSCALE:
                case RSTARTPREFERSWITCH:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINE;
                    System.out.println("GAME DATA FAILURE: AUTOLINE RUNNING");
                    break;
                case CENTER:
                    System.out.println("GAME DATA FAILURE: GOING TO RIGHT SWITCH");
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.CENTERRIGHT;
                    break;
            }
        }

        if (!DriveTrain.getInstance().CheckGyro()){ //if gyro does not work, set auto path to a path with timer
            System.out.println("GYRO HAS FAILED DECIDING AUTO");
            switch (AutoModeSelector.getAutoDefinition()) {
                case LSTARTONLYSCALE:
                case LSTARTPERFERSCALE:
                case LSTARTPREFERSWITCH:
                case RSTARTONLYSCALE:
                case RSTARTPREFERSCALE:
                case RSTARTPREFERSWITCH:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMER;
                    break;
                case CENTER:
                    if (FieldConfig.hasGameData()) {
                        if (FieldConfig.isMySwitchLeft()) { //see what switch is ours and change path to a timer path that goes to out switch
                            currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMERCCENTERLEFT;
                        } else {
                            currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMERCCENTERRIGHT;
                        }
                    }else{
                        currentAutoMode = AutoModeSelector.AutoModeDefinition.CENTERRIGHT;
                    }
                    break;
            }
        }
        autoModeRunner.setAutoMode(currentAutoMode.getInstance());

        fieldLooper.stop(); //no reason to keep running this
        //autoModeRunner.setAutoMode(new AutoLine());
        autoModeRunner.start();
    }
    @Override
    public void autonomousPeriodic() {
        SmartDashboard.putNumber("gyro", driveTrain.getGyroAngleDegrees());
        SmartDashboard.putNumber("gyroRate", driveTrain.getGyroRateDegrees());
        SmartDashboard.putNumber("psi", revRoboticsPressureSensor.getAirPressurePsi());
        SmartDashboard.putNumber("LeftInches", driveTrain.getLeftDistanceInches());
        SmartDashboard.putNumber("RightInches", driveTrain.getRightDistanceInches());
        SmartDashboard.putNumber("LeftRaw", driveTrain.getLeftRawTicks());
        SmartDashboard.putNumber("RightRaw", driveTrain.getRightRawTicks());
        robotState.outputToSmartDashboard();
    }

    @Override
    public void teleopInit() {
        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());

        zeroAllSensors();

        autoModeRunner.stop();
        fieldLooper.stop();

        controlLoop.start();
        slowerLooper.start();

        driveTrain.setOpenLoop(DriveSignal.NEUTRAL);
        driveTrain.setBrakeMode(true);
    }

    @Override
    public void teleopPeriodic() {
        /*
        if (controls.wantVisionAlign()) {
            if(!visionTurn) {
                VisionTrackingTurnAngleResult latestTargetResult = VisionProcessor.getInstance().getLatestTargetResult();
                if (latestTargetResult.isValid) {
                    visionTurn = true;
                    visionTurnAngle = Rotation2d.fromDegrees(driveTrain.getGyroAngleDegrees() + latestTargetResult.turnAngle);
                }
                if(visionTurn) {
                    driveTrain.setVelocityHeadingSetpoint(10 * controls.getTank(), visionTurnAngle);
                }
            }
        } else {*/

        driveTrain.setOpenLoop(driveHelper.drive(controls.getTank(), controls.getTurn(), controls.getQuickTurn()));
          //  visionTurn = false;
        //}

        if (intake != null) {

            if (controls.getIntake()) {
                intake.intake();
            } else if (controls.getOuttake()) {
                intake.outtake();
            } else {
                intake.stopped();
            }

            firstIntakeButtonPressed = firstIntakeButtonPressed || controls.getIntakeUp();
            if (firstIntakeButtonPressed) {
                if (controls.getIntakeUp()) {
                    intake.pickupPositionRaised();
                } else if (controls.getStartConfig()) {
                    intake.pickupPositionStartingConfig();
                } else {
                    intake.pickupPositionDown();
                }
            }
        }

        if (elevator != null) {
            //Passes values from the button to elevator class
            if (controls.getElevatorPickup()) {
                elevator.setTarget(Elevator.ElevatorPresetEnum.PICKUP);
            } else if (controls.getElevatorSwitch()) {
                elevator.setTarget(Elevator.ElevatorPresetEnum.SWITCH);
            } else if (controls.getElevatorScale1()) {
                elevator.setTarget(Elevator.ElevatorPresetEnum.SCALE_BALANCED);
            } else if (controls.getElevatorScale2()) {
                elevator.setTarget(Elevator.ElevatorPresetEnum.SCALE_HIGH);
            } else if (controls.getElevatorScale3()) {
                elevator.setTarget(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING);
            }

            //elevator class checks if the button changed its state and adjusts to that
            //so always send if the buttons is up or down
            elevator.setElevatorAdjustmentUp(controls.getElevatorAdjustmentUp());
            elevator.setElevatorAdjustmentDown(controls.getElevatorAdjustmentDown());

            elevator.setEmergencyHold(controls.getElevatorEmergencyHold());
            elevator.setEmergencyUp(controls.getElevatorEmergencyUp());
        }

        if (ramp != null)
        {
            ramp.dropRampPinLeft(controls.getDropLeftRamp());

            ramp.dropRampPinRight(controls.getDropRightRamp());


            if (controls.getLowerLeftRamp()){
                System.out.println("LOWER LEFT RAMP");
                ramp.lowerLeftRamp();
            }else if(controls.getRaiseLeftRamp()){
                System.out.println("RAISE LEFT RAMP________");
                ramp.raiseLeftRamp();
            }

            if (controls.getLowerRightRamp()){
                ramp.lowerRightRamp();
            }else if(controls.getRaiseRightRamp()){
                System.out.println("RAISING RIGHT RAMP");
                ramp.raiseRightRamp();
            }
        }

        SmartDashboard.putNumber("gyroAngle", driveTrain.getGyroAngleDegrees());
        SmartDashboard.putNumber("gyroRate", driveTrain.getGyroRateDegrees());
        SmartDashboard.putNumber("psi", revRoboticsPressureSensor.getAirPressurePsi());
        SmartDashboard.putNumber("LeftInches", driveTrain.getLeftDistanceInches());
        SmartDashboard.putNumber("RightInches", driveTrain.getRightDistanceInches());
        robotState.outputToSmartDashboard();
    }

    @Override
    public void testInit() { }

    @Override
    public void testPeriodic() { }

    public void zeroAllSensors() {
        driveTrain.resetEncoders();
        driveTrain.zeroGyro();
    }
}
