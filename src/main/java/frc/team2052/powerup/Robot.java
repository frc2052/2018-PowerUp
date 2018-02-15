package frc.team2052.powerup;

import com.first.team2052.lib.ControlLoop;
import com.first.team2052.lib.RevRoboticsPressureSensor;
import com.first.team2052.lib.vec.RigidTransform2d;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.subsystems.*;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class Robot extends IterativeRobot {

    private ControlLoop controlLoop = null;
    private ControlLoop logLooper = null;
    private ControlLoop slowerLooper = null;

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

    @Override
    public void robotInit() {
        driveTrain = DriveTrain.getInstance();
        driveHelper = new DriveHelper();
        controls = Controls.getInstance();
        //Camera.getInstance().init();

        //////THESE SUBSYSTEMS ARE FAULT TOLERANT/////
        /////// they will return null if they fail to create themselves////////
        intake = Pickup.getInstance();
//        ramp = Ramp.getInstance();
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

        robotState = RobotState.getInstance();
        stateEstimator = RobotStateEstimator.getInstance();

        controlLoop.addLoopable(driveTrain.getLoopable());
        controlLoop.addLoopable(stateEstimator);


        if (elevator != null) {
            elevator.zeroSensor();
            controlLoop.addLoopable(elevator);
        }
        if (intake != null)
        {
            intake.init();
        }

        //slowerLooper.addLoopable(VisionProcessor.getInstance());

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
        AutoPaths.Init();
        zeroAllSensors();
        Timer.delay(.25);

        if (elevator != null) {
            elevator.zeroSensor();
        }
        driveTrain.setOpenLoop(DriveSignal.NEUTRAL);  //put robot into don't move, no looper mode
        driveTrain.setBrakeMode(false);

        AutoPaths.Init();
        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        logLooper.start();
        controlLoop.start();
        slowerLooper.start();

        AutoModeSelector.AutoModeDefinition currentAutoMode = AutoModeSelector.getAutoDefinition(); //creates a variable we can change
        if (!DriveTrain.getInstance().CheckGyro() ){ //if gyro does not work, set auto path to a path with timer
            switch (AutoModeSelector.getAutoDefinition()) {
                case LSTARTONLYSCALE:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMER;
                    break;
                case LSTARTPERFERSCALE:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMER;
                    break;
                case LSTARTPREFERSWITCH:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMER;
                    break;
                case RSTARTONLYSCALE:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMER;
                    break;
                case RSTARTPREFERSCALE:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMER;
                    break;
                case RSTARTPREFERSWITCH:
                    currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMER;
                    break;
                case CENTER: {
                    if (FieldConfig.isMySwitchLeft()) { //see what switch is ours and change path to a timer path that goes to out switch
                        currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMERCCENTERLEFT;
                    } else {
                        currentAutoMode = AutoModeSelector.AutoModeDefinition.AUTOLINEWITHTIMERCCENTERRIGHT;
                    }
                    break;
                }
            }
        }
        autoModeRunner.setAutoMode(currentAutoMode.getInstance());
        autoModeRunner.start();
    }
    @Override
    public void autonomousPeriodic() {
        SmartDashboard.putNumber("gyro", driveTrain.getGyroAngleDegrees());
        SmartDashboard.putNumber("gyroRate", driveTrain.getGyroRateDegrees());
        SmartDashboard.putNumber("psi", revRoboticsPressureSensor.getAirPressurePsi());
        SmartDashboard.putNumber("LeftVel", driveTrain.getLeftVelocityInchesPerSec());
        SmartDashboard.putNumber("RightVel", driveTrain.getRightVelocityInchesPerSec());
        robotState.outputToSmartDashboard();

    }

    @Override
    public void teleopInit() {
        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());

        zeroAllSensors();

        autoModeRunner.stop();

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

            if (controls.getIntakeUp()){
                intake.pickupPositionRaised();
            }else{
                intake.pickupPositionDown();
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
        }

        if (ramp != null)
        {
            if(controls.getDropLeftRamp())
            {
                ramp.dropRampPinLeft();
            }

            if(controls.getDropRightRamp())
            {
                ramp.dropRampPinRight();
            }

            if (controls.getLowerLeftRamp()){
                ramp.lowerLeftRamp();
            }else if(controls.getRaiseLeftRamp()){
                ramp.raiseLeftRamp();
            }

            if (controls.getLowerRightRamp()){
                ramp.lowerRightRamp();
            }else if(controls.getRaiseRightRamp()){
                ramp.raiseRightRamp();
            }
        }

        SmartDashboard.putNumber("gyro", driveTrain.getGyroAngleDegrees());
        SmartDashboard.putNumber("gyroRate", driveTrain.getGyroRateDegrees());
        SmartDashboard.putNumber("psi", revRoboticsPressureSensor.getAirPressurePsi());
        SmartDashboard.putNumber("LeftVel", driveTrain.getLeftVelocityInchesPerSec());
        SmartDashboard.putNumber("RightVel", driveTrain.getRightVelocityInchesPerSec());
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
