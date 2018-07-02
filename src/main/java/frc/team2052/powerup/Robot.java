package frc.team2052.powerup;



/**
 * Example demonstrating the motion magic control mode.
 * Tested with Logitech F710 USB Gamepad inserted into Driver Station.
 *
 * Be sure to select the correct feedback sensor using configSelectedFeedbackSensor() below.
 *
 * After deploying/debugging this to your RIO, first use the left Y-stick
 * to throttle the Talon manually.  This will confirm your hardware setup/sensors
 * and will allow you to take initial measurements.
 *
 * Be sure to confirm that when the Talon is driving forward (green) the
 * position sensor is moving in a positive direction.  If this is not the
 * cause, flip the boolean input to the setSensorPhase() call below.
 *
 * Once you've ensured your feedback device is in-phase with the motor,
 * and followed the walk-through in the Talon SRX Software Reference Manual,
 * use button1 to motion-magic servo to target position specified by the gamepad stick.
 */
//import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.Joystick;
//
//import java.util.concurrent.TimeUnit;
//
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.StatusFrame;
//import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
//import com.ctre.phoenix.motorcontrol.can.*;
//
//public class Robot extends IterativeRobot {
//    TalonSRX _talon = new TalonSRX(Constants.kElevatorMotorID);
//    Joystick _joy = new Joystick(0);
//    StringBuilder _sb = new StringBuilder();
//
//    public void robotInit() {
//
//        /* first choose the sensor */
//        _talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
//        _talon.setInverted(true);
//        _talon.setSensorPhase(true);
//
//        /* Set relevant frame periods to be at least as fast as periodic rate */
//        _talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 10);
//        _talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
//
//        /* set the peak and nominal outputs */
//        _talon.configNominalOutputForward(0, 10);
//        _talon.configNominalOutputReverse(0, 10);
//        _talon.configPeakOutputForward(1, 10);
//        _talon.configPeakOutputReverse(-1, 10);
//
//        /* set closed loop gains in slot0 - see documentation */
//        _talon.selectProfileSlot(0, 0);
//        _talon.config_kF(0, 0.1843, 10);
//        _talon.config_kP(0, .8, 10);
//        _talon.config_kI(0, 0, 10);
//        _talon.config_kD(0, 0, 10);
//        /* set acceleration and vcruise velocity - see documentation */
//        _talon.configMotionCruiseVelocity((int)(5550 * .9), 10);
//        _talon.configMotionAcceleration((int)(5550 * .9 * 2), 10);
//        /* zero the sensor */
//        _talon.setSelectedSensorPosition(0, 0, 10);
//    }
//
//    /**
//     * This function is called periodically during operator control
//     */
//    public void teleopPeriodic() {
//        double targetPos = 0;
//        /* get gamepad axis - forward stick is positive */
//        double leftYstick = -1.0 * _joy.getY();
//        /* calculate the percent motor output */
//        double motorOutput = _talon.getMotorOutputPercent();
//        /* prepare line to print */
//        _sb.append("\tOut%:");
//        _sb.append(motorOutput);
//        _sb.append("\tVel:");
//        _sb.append(_talon.getSelectedSensorVelocity(0));
//
//        //if (_joy.getRawButton(1)) {
//            /* Motion Magic - 4096 ticks/rev * 10 Rotations in either direction */
//            if (leftYstick > .1){
//                double rotation = (Constants.kElevatorMaxHeight - 2) / Constants.kElevatorInchesPerRotation;
//                targetPos = (int) (rotation * Constants.kElevatorTicksPerRot);
//            }
//
//            _talon.set(ControlMode.MotionMagic, targetPos);
//
//            /* append more signals to print when in speed mode. */
//            _sb.append("\terr:");
//            _sb.append(_talon.getClosedLoopError(0));
//            _sb.append("\ttrg:");
//            _sb.append(targetPos);
//        //} else {
//            /* Percent voltage mode */
//            //_talon.set(ControlMode.PercentOutput, leftYstick);
//        //}
//        /* instrumentation */
//        Instrum.Process(_talon, _sb);
//        try {
//            TimeUnit.MILLISECONDS.sleep(10);
//        } catch (Exception e) {
//        }
//    }
//}







import com.first.team2052.lib.ControlLoop;
import com.first.team2052.lib.vec.RigidTransform2d;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.auto.AutoModeRunner;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.FieldConfig;
import frc.team2052.powerup.auto.actions.TimeOutOrHaltedDriveAction;
import frc.team2052.powerup.subsystems.*;
import frc.team2052.powerup.subsystems.Interfaces.ElevatorSubsystem;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class Robot extends IterativeRobot {

    private ControlLoop controlLoop = null;
    private ControlLoop logLooper = null;
    private ControlLoop slowerLooper = null;
    private ControlLoop fieldLooper = null;

    private static DriveTrain driveTrain = null;
    private PickupSubsystem intake = null;
    private Controls controls = null;
    private Ramp ramp = null;
    private ElevatorSubsystem elevator = null;
    private PixyCam pixyCam = null;

    private AutoModeRunner autoModeRunner = null;
    private RobotState robotState = null;
    private DriveHelper driveHelper = null;
    private RobotStateEstimator stateEstimator = null;

    private PowerDistributionPanel pdp = null;
    private Compressor compressor;

    private boolean firstIntakeButtonPressed;
    private Timer spinnerTimer = new Timer();

    private boolean firstSpinPress = true;

    @Override
    public void robotInit() {
        System.out.println("STARTING ROBOT INIT");
        driveTrain = DriveTrain.getInstance();
        driveHelper = new DriveHelper();
        controls = Controls.getInstance();
        //Camera.getInstance().init();

        //////THESE SUBSYSTEMS ARE FAULT TOLERANT/////
        /////// they will return null if they fail to create themselves////////
        intake = SubsystemFactory.getPickup();
        ramp = Ramp.getInstance();
        elevator = SubsystemFactory.getElevator();
        PickupSubsystem pickup = SubsystemFactory.getPickup();  //force the instance to be creared
        ElevatorSubsystem elevator = SubsystemFactory.getElevator(); //force the instance to be created
        pixyCam = PixyCam.getInstance();
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

        if(ramp != null){
            ramp.lowerLeftRamp();
            ramp.lowerRightRamp();
        }

        if(pixyCam != null){
            pixyCam.init();
        }

        fieldLooper.addLoopable(new FieldConfig()); //FMS is not gaurenteed to give us the game data on first try, so loop until you get it

        //Logging for auto
        logLooper = new ControlLoop(1.0);
        logLooper.addLoopable(PositionLoggerLoopable.getInstance());

        AutoModeSelector.putToSmartDashboard();
        autoModeRunner = new AutoModeRunner();
        System.out.println("COMPLETED ROBOT INIT");
    }

    @Override
    public void disabledInit() {
        System.out.println("STARTING DISABLED INIT");
        controlLoop.stop();
        logLooper.stop();
        slowerLooper.stop();
        fieldLooper.stop();
        autoModeRunner.stop();
        zeroAllSensors();
        System.out.println("COMPLETED DISABLED INIT");
    }

    @Override
    public void disabledPeriodic() {
        System.gc();

    }

    @Override
    public void autonomousInit() {
        TimeOutOrHaltedDriveAction.resetCriticalFailure();
        System.out.println("STARTING AUTO INIT");
        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        FieldConfig.reset();
        AutoPaths.Init();
        System.out.println("Auto Paths Initialized");
        zeroAllSensors();
        Timer.delay(.25);

        if (elevator != null) {
            elevator.zeroSensor();
        }
        System.out.println("Sensored Zeros");
        driveTrain.setOpenLoop(DriveSignal.NEUTRAL);  //put robot into don't move, no looper mode
        driveTrain.setBrakeMode(false);

        fieldLooper.start();
        logLooper.start();
        controlLoop.start();
        slowerLooper.start();
        System.out.println("Loopers started");

        AutoModeSelector.AutoModeDefinition currentAutoMode = AutoModeSelector.getAutoDefinition(); //creates a variable we can change
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

        System.out.println("Starting Gyro check");
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

        fieldLooper.stop(); //no reason to keep running this
        System.out.println("Stopped Field Looper");
        //autoModeRunner.setAutoMode(new AutoLine());
        System.out.println("STARTING AUTOMODE " + currentAutoMode.name());
        System.out.println("----------------CREATE AUTO ACTIONS--------------");
        autoModeRunner.start(currentAutoMode.getInstance());
        System.out.println("COMPLETED AUTO INIT");
    }
    @Override
    public void autonomousPeriodic() {
        //force ramps shut
        ramp.dropRampPinLeft(false);
        ramp.dropRampPinRight(false);

        if(TimeOutOrHaltedDriveAction.getEncoderFailureDetected()){
            System.out.println("CRITICAL AUTONOMOUS STOP");
            autoModeRunner.stop();
        }
        SmartDashboard.putNumber("gyro", driveTrain.getGyroAngleDegrees());
        SmartDashboard.putNumber("gyroRate", driveTrain.getGyroRateDegrees());
        SmartDashboard.putNumber("LeftInches", driveTrain.getLeftDistanceInches());
        SmartDashboard.putNumber("RightInches", driveTrain.getRightDistanceInches());
        SmartDashboard.putNumber("LeftRaw", driveTrain.getLeftRawTicks());
        SmartDashboard.putNumber("RightRaw", driveTrain.getRightRawTicks());
        robotState.outputToSmartDashboard();
    }

    @Override
    public void teleopInit() {
        System.out.println("STARTING TELEOP INIT");
        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());

        zeroAllSensors();

        autoModeRunner.stop();
        fieldLooper.stop();

        controlLoop.start();
        slowerLooper.start();

        driveTrain.setOpenLoop(DriveSignal.NEUTRAL);
        driveTrain.setBrakeMode(true);
        if (elevator != null){
//            elevator.setCurrentPosAsTarget(); //if elevator is coasting down, tell it to stay right where it is, otherwise it will go back up
        }
        System.out.println("COMPLETED TELEOP INIT");
    }

    @Override
    public void teleopPeriodic() {

        if (controls.getVisionTrack()) {

            double maxVal = (3.3 / 2);
            try {
                if (pixyCam.getCubeInput()) {
                    System.out.println("I see a cube in teleop");
                    double turn = pixyCam.getPositionVoltage();
                    if (turn > 3.3) //should never happen
                    {
                        System.out.println("Vision: Voltage was off the charts!");
                        turn = 3.3;
                    }
                    turn = turn - maxVal; //shift the voltage so that instead of 0 to 3.3 it is -1.65 to 1.65
                    turn = turn / maxVal; //get a value between -1 and 1 for turn velocity

                    System.out.println("TURN: " + turn);

                    driveTrain.setOpenLoop(driveHelper.drive(controls.getTank(), -turn / 2, false));
                } else {
                    System.out.println("no cube");
                    driveTrain.setOpenLoop(driveHelper.drive(controls.getTank(), controls.getTurn(), controls.getQuickTurn()));
                }
            } catch (Exception exc) {
                System.out.println("ERROR: getting vision inputs " + exc.getMessage());
                exc.printStackTrace();
            }
        }else {
            driveTrain.setOpenLoop(driveHelper.drive(controls.getTank(), controls.getTurn(), controls.getQuickTurn()));
        }

        if (intake != null) {
            if (!controls.getAutotest()){
                firstSpinPress = true;
                spinnerTimer.stop();
            }

            if (controls.getAutotest()){
                if (firstSpinPress){
                    firstSpinPress = false;
                    spinnerTimer.reset();
                    spinnerTimer.start();
                }else if (spinnerTimer.get() < .1){
                    intake.spin();
                    System.out.println("SPINTUIMER" + spinnerTimer.get() + " " + (spinnerTimer.get() < .5));
                }else{
                    intake.stopped();
                }
            } else if (controls.getIntake()) {
                intake.intake();
            } else if (controls.getOuttake()) {
                if (controls.getShoot()){ //if the shoot button is pressed then outtake
                    intake.shoot();
                }else{
                    if(intake.isPickupRaised()){ //if the pickup is raised then set speed to faster
                        intake.mediumOuttake();
                    } else{ //otherwise set the speed to slower
                        intake.outtake();
                    }
                }
            } else {
                intake.stopped();
            }

            if (!controls.getIntake() && !controls.getIntakePrimary()) { //this is to reset the timer that checks if the cube is stuck in the intake
                intake.resetAmpTimer();
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
            intake.openIntake(controls.getIntakePrimary());
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

            System.out.println("OVERRIDE: going up " + controls.getElevatorEmergencyUp());

            if(controls.getElevatorEmergencyUp()){
                elevator.setEmergencyUp(true);
            }else if(!controls.getElevatorEmergencyDown()){
                elevator.setEmergencyUp(false);
            }else{
                elevator.setEmergencyDown(true);
            }

        }

        if (ramp != null)
        {
            ramp.dropRampPinLeft(controls.getDropLeftRamp());

            ramp.dropRampPinRight(controls.getDropRightRamp());

            if (controls.getLowerLeftRamp()){
                System.out.println("LOWER LEFT RAMP");
                ramp.lowerLeftRamp();
            }else if(controls.getRaiseLeftRamp()){
                System.out.println("RAISE LEFT RAMP");
                ramp.raiseLeftRamp();
            }

            if (controls.getLowerRightRamp()){
                ramp.lowerRightRamp();
                System.out.println("LOWER RIGHT RAMP");
            }else if(controls.getRaiseRightRamp()){
                System.out.println("RAISe RIGHT RAMP");
                ramp.raiseRightRamp();
            }
        }

        SmartDashboard.putNumber("gyroAngle", driveTrain.getGyroAngleDegrees());
        SmartDashboard.putNumber("gyroRate", driveTrain.getGyroRateDegrees());
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
