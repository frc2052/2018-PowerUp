package frc.team2052.powerup;

import com.first.team2052.lib.ControlLoop;
import com.first.team2052.lib.RevRoboticsPressureSensor;
import com.first.team2052.lib.vec.RigidTransform2d;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.auto.AutoModeRunner;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.constants.ControlLoopConstants;
import frc.team2052.powerup.subsystems.Controls;
import frc.team2052.powerup.subsystems.Elevator;
import frc.team2052.powerup.subsystems.Intake;
import frc.team2052.powerup.subsystems.Ramp;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class Robot extends IterativeRobot {

    private ControlLoop controlLoop;
    private ControlLoop logLooper;
    private ControlLoop slowerLooper;

    private static DriveTrain driveTrain;
    private Intake intake;
    private Controls controls;
    private Ramp ramp;
    private Elevator elevator;

    private AutoModeRunner autoModeRunner;
    private RobotState robotState;
    private DriveHelper driveHelper;
    private RobotStateEstimator stateEstimator;

    private PowerDistributionPanel pdp;
    private RevRoboticsPressureSensor revRoboticsPressureSensor;


    @Override
    public void robotInit() {
        System.out.println("Starting Robot Code - Hornet");
        driveHelper = new DriveHelper();

        //Subsystems
        driveTrain = DriveTrain.getInstance();
        intake = Intake.getInstance();
        controls = Controls.getInstance();
        ramp = Ramp.getInstance();
        driveHelper = new DriveHelper();
        elevator = Elevator.getInstance();

        pdp = new PowerDistributionPanel();

        //Control loops for auto and teleop
        controlLoop = new ControlLoop(ControlLoopConstants.kControlLoopPeriod);
        slowerLooper = new ControlLoop(ControlLoopConstants.kSlowControlLoopPeriod);

        robotState = RobotState.getInstance();
        stateEstimator = RobotStateEstimator.getInstance();

        controlLoop.addLoopable(driveTrain.getLoopable());
        controlLoop.addLoopable(stateEstimator);

        //Slower loops because why update them 100 times a second
        slowerLooper.addLoopable(intake);

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
        zeroAllSensors();
        Timer.delay(.25);

        driveTrain.setOpenLoop(DriveSignal.NEUTRAL);
        driveTrain.setBrakeMode(false);

        intake.getWantClosed();

        robotState.reset(Timer.getFPGATimestamp(), new RigidTransform2d());
        logLooper.start();
        controlLoop.start();
        slowerLooper.start();
        autoModeRunner.setAutoMode(AutoModeSelector.getAutoInstance());
        autoModeRunner.start();
    }
    @Override

    public void autonomousPeriodic() {

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

        driveTrain.resetEncoders();
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

        if(controls.getIntakeOpenIntake()){
            intake.setWantOpenIntake();
        }else if(controls.getIntakeOpenOuttake()){
            intake.getWantOpenOutake();
        }else if(controls.getIntakeOpenOff()){
            intake.getWantOpenOff();
        }else{
            intake.getWantClosed();
        }

        //Passes values from the button to elevator class
        if (controls.getElevatorPickup())
        {
            elevator.setTarget(Elevator.ElevatorPresetEnum.PICKUP);
        }
        else if (controls.getElevatorSwitch())
        {
            elevator.setTarget(Elevator.ElevatorPresetEnum.SWITCH);
        }
        else if (controls.getElevatorScale1())
        {
            elevator.setTarget(Elevator.ElevatorPresetEnum.SCALE_BALANCED);
        }
        else if (controls.getElevatorScale2())
        {
            elevator.setTarget(Elevator.ElevatorPresetEnum.SCALE_HIGH);
        }
        else if (controls.getElevatorScale3())
        {
            elevator.setTarget(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING);
        }

        if(controls.getElevatorAdjustmentUp() == true)
        {
            elevator.getElevatorAdjustmentUp(controls.getElevatorAdjustmentUp());
        }

        if(controls.getElevatorAdjustmentDown() == true)
        {
            elevator.getElevatorAdjustmentDown(controls.getElevatorAdjustmentUp());
        }


        if(controls.getDropLeftRamp()){ramp.openRampPinLeft();}

        if(controls.getDropRightRamp()){ramp.openRampPinRight();}
        //todo: toggle ramp?? or stick with 4 buttons
        if (controls.getLowerLeftRamp()){
            ramp.openLeftRamp(true);
        }else if(controls.getRaiseLeftRamp()){
            ramp.openLeftRamp(false);
        }

        if (controls.getLowerRightRamp()){
            ramp.openRightRamp(true);
        }else if(controls.getRaiseRightRamp()){
            ramp.openRightRamp(false);
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

    public void zeroAllSensors() { //todo: add this for the elevator
        driveTrain.resetEncoders();
        driveTrain.zeroGyro();
    }
}