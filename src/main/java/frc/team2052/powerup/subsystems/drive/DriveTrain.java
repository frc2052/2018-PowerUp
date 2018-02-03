package frc.team2052.powerup.subsystems.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.first.team2052.lib.Loopable;
import com.first.team2052.lib.SynchronousPID;
import com.first.team2052.lib.path.AdaptivePurePursuitController;
import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Rotation2d;
import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.Kinematics;
import frc.team2052.powerup.RobotState;
import frc.team2052.powerup.constants.ControlLoopConstants;
import frc.team2052.powerup.constants.DriveConstants;

import java.util.Set;

/**
 * Created by KnightKrawler on 1/19/2018.
 */
public class DriveTrain extends DriveTrainHardware {

    private static DriveTrain instance = new DriveTrain();
    private DriveControlState driveControlState;
    private double mLastHeadingErrorDegrees;
    private AdaptivePurePursuitController pathFollowingController;
    private VelocityHeadingSetpoint velocityHeadingSetpoint;
    private SynchronousPID velocityHeadingPid;
    private double visionForward;
    private final Loopable loopable = new Loopable() {

       @Override
        public void onStart() { //part of loopable interface
            setOpenLoop(DriveSignal.NEUTRAL);
            setBrakeMode(false);
        }

        @Override
        public void update() {

            if (getDriveControlState() == DriveControlState.OPEN_LOOP) {
                //ignore all looper requests while in teleop mode
                return;
            }
            setBrakeMode(true);

            switch (getDriveControlState()) {
                case PATH_FOLLOWING_CONTROL:
                    updatePathFollower();
                    if (isFinishedPath()) {
                        setOpenLoop(DriveSignal.NEUTRAL);
                    }
                    return;
                case VELOCITY_HEADING_CONTROL:
                    updateVelocityHeadingSetpoint();
                    return;
                //case VISION_FOLLOW: // todo:vision fix
                  //  updateVisionFollow();
                    //break;
            }
        }

        @Override
        public void onStop() {
            setOpenLoop(DriveSignal.NEUTRAL);
        }
    };

    //PID loop for drive speed 
    private DriveTrain() {
        setOpenLoop(DriveSignal.NEUTRAL);

        velocityHeadingPid = new SynchronousPID(DriveConstants.kDriveHeadingVelocityKp, DriveConstants.kDriveHeadingVelocityKi,
                DriveConstants.kDriveHeadingVelocityKd);
        velocityHeadingPid.setOutputRange(-30, 30);
    }

    public synchronized static DriveTrain getInstance() {
        return instance;
    }

    private static double rotationsToInches(double rotations) {
        return rotations * (DriveConstants.kDriveWheelDiameterInches * Math.PI);
    }

    private static double rpmToInchesPerSecond(double rpm) {
        return rotationsToInches(rpm) / 60.0;
    }

    private static double inchesToRotations(double inches) {
        return inches / (DriveConstants.kDriveWheelDiameterInches * Math.PI);
    }

    private static double inchesPerSecondToRpm(double inches_per_second) {
        return inchesToRotations(inches_per_second) * 60;
    }

    private static double inchesPerSecondToTicksPer100Ms(double inches_per_second) {
        return inchesToRotations(inches_per_second) * DriveConstants.kDriveEncoderTicksPerRot / 10 ;
    }



    /**
     * Sets the motor speeds in percent mode and disables all controllers
     */
    public void setOpenLoop(DriveSignal signal) {
//        System.out.println("ENCODERS LEFT: " + getLeftDistanceInches() + "   RIGHT: " + getRightDistanceInches());
//        System.out.println("GYRO DEGREES: " + getGyroAngleDegrees());
        driveControlState = DriveControlState.OPEN_LOOP;
        leftMaster.set(ControlMode.PercentOutput, signal.leftMotor);
        rightMaster.set(ControlMode.PercentOutput, signal.rightMotor);
    }

    /**
     * Drives a desired path
     *
     * @param path     the path you want to follow
     * @param reversed weather it is reversed or not (the robot would run backwards)
     */
    public synchronized void followPath(Path path, boolean reversed) {
        //If not already in the path following control state, then configure the talons and reset the PID loop for turning to get rid of any Integral error that may have accumulated
        if (getDriveControlState() != DriveControlState.PATH_FOLLOWING_CONTROL) {
            configureTalonsForSpeedControl();
            driveControlState = DriveControlState.PATH_FOLLOWING_CONTROL;
            velocityHeadingPid.reset();
        }
        //Make a new path following controller under the constraints of the drive train.
        pathFollowingController = new AdaptivePurePursuitController(DriveConstants.kPathFollowingLookahead,
                DriveConstants.kPathFollowingMaxAccel, ControlLoopConstants.kControlLoopPeriod, path, reversed, 0.25);
        //Update the path follower right away
        updatePathFollower();
    }

    //todo: comment out since not created yet?
/*
    private void updateVisionFollow() {
        VisionTrackingTurnAngleResult latestTargetResult = VisionProcessor.getInstance().getLatestTargetResult();
        if (!latestTargetResult.isValid) {
            updateVelocitySetpoint(visionForward, visionForward);
            return;
        }
        double dTheta = getGyroAngleDegrees() - latestTargetResult.turnAngle;
        dTheta *= 4;
        double power = Math.min(dTheta, 12);

        updateVelocitySetpoint(visionForward + power, visionForward - power);
    }

    public synchronized void startVisionFollow(double forward) {
        visionForward = forward;
        if (getDriveControlState() == DriveControlState.VISION_FOLLOW) {
            return;
        }
        if (getDriveControlState() != DriveControlState.VISION_FOLLOW) {
            configureTalonsForSpeedControl();
            driveControlState = DriveControlState.VISION_FOLLOW;
            velocityHeadingPid.reset();
        }
        updateVisionFollow();
    }
*/
    /**
     * Set's te velocity heading a certain direction. This is useful for driving in straight lines at certain speeds. You have no control over position though just angular velocity.
     */
    public synchronized void setVelocityHeadingSetpoint(double forward_inches_per_sec, Rotation2d headingSetpoint) {
        //If not already in the velocity heading control state, then configure the talons and reset the PID loop for turning to get rid of any Integral error that may have accumulated
        if (getDriveControlState() != DriveControlState.VELOCITY_HEADING_CONTROL) {
            configureTalonsForSpeedControl();
            driveControlState = DriveControlState.VELOCITY_HEADING_CONTROL;
            velocityHeadingPid.reset();
        }
        velocityHeadingSetpoint = new VelocityHeadingSetpoint(forward_inches_per_sec, forward_inches_per_sec,
                headingSetpoint);
        updateVelocityHeadingSetpoint();
    }

    /**
     * Updates the left and right wheel velocity based off the params
     */
    private synchronized void updateVelocitySetpoint(double left_inches_per_sec, double right_inches_per_sec) {
        //Check to see if it in the valid states for speed control and it it isn't set the speed to 0
        if (getDriveControlState() == DriveControlState.PATH_FOLLOWING_CONTROL
                || getDriveControlState() == DriveControlState.VELOCITY_HEADING_CONTROL
                || getDriveControlState() == DriveControlState.VISION_FOLLOW) {
//            System.out.printf("Velocity\n\tLeft %s\n\tRight %s\n", leftMaster.getSelectedSensorVelocity(0), rightMaster.getSelectedSensorVelocity(0));
//            System.out.printf("Error\n\tLeft %s\n\tRight %s\n", leftMaster.getClosedLoopError(0), rightMaster.getClosedLoopError(0));
//            System.out.printf("setting velocity final\n\tleft %f\n\tright%f\n", inchesPerSecondToRpm(left_inches_per_sec), inchesPerSecondToRpm(right_inches_per_sec));

            double leftSpeed = inchesPerSecondToTicksPer100Ms(left_inches_per_sec);
            double rightSpeed = inchesPerSecondToTicksPer100Ms(right_inches_per_sec);

            leftMaster.set(ControlMode.Velocity, leftSpeed);
            rightMaster.set(ControlMode.Velocity, rightSpeed);
//            System.out.println("UPDATE VELOCITY SETTING  -----  Angle: " + getGyroAngleDegrees() + "   LEFT: " + leftSpeed + "    RIGHT: " + rightSpeed);
        } else {
            System.out.println("Hit a bad velocity control state");
            leftMaster.set(ControlMode.PercentOutput,0);
            rightMaster.set(ControlMode.PercentOutput,0);
        }
    }

    /**
     * Updates the velocity heading value for turning, this is used to drive a set angle at a desired speed. We use a PID loop to do the turning
     */
    private void updateVelocityHeadingSetpoint() {
        Rotation2d actualGyroAngle = getGyroAngle();

        mLastHeadingErrorDegrees = velocityHeadingSetpoint.getHeading().rotateBy(actualGyroAngle.inverse()).getDegrees();

        double deltaSpeed = velocityHeadingPid.calculate(mLastHeadingErrorDegrees);
        updateVelocitySetpoint(velocityHeadingSetpoint.getLeftSpeed() + deltaSpeed / 2,
                velocityHeadingSetpoint.getRightSpeed() - deltaSpeed / 2);
    }

    /**
     * Update method from the drive train looper to change the drive train's left and right wheel velocity
     * based off the Adaptive Pure Pursuit Controller which calculates motor speeds based off a lookahead point and calculates the speeds necessary to get to that point
     */
    private void updatePathFollower() {
        RigidTransform2d robot_pose = RobotState.getInstance().getLatestFieldToVehicle().getValue();
//        System.out.println("ROBOT POSE ----   DEGREES: " + robot_pose.getRotation().getDegrees() + " X: " + robot_pose.getTranslation().getX() + " Y:" + robot_pose.getTranslation().getY());
        RigidTransform2d.Delta command = pathFollowingController.update(robot_pose, Timer.getFPGATimestamp());
//        System.out.println("COMMAND---  dx: " + command.dx + "  dy: "+ command.dy);
        Kinematics.DriveVelocity setpoint = Kinematics.inverseKinematics(command);

        // Scale the command to respect the max velocity limits
        // We don't want our robot setpoints for turning, etc over it's limits, so we scale our outputs
        double max_vel = 0.0;
        max_vel = Math.max(max_vel, Math.abs(setpoint.left));
        max_vel = Math.max(max_vel, Math.abs(setpoint.right));
        if (max_vel > DriveConstants.kPathFollowingMaxVel) {
            double scaling = DriveConstants.kPathFollowingMaxVel / max_vel;
            setpoint = new Kinematics.DriveVelocity(setpoint.left * scaling, setpoint.right * scaling);
        }

        updateVelocitySetpoint(setpoint.left, setpoint.right);
    }

    /**
     * @return if the path is finished and within it's tolerance
     */
    public boolean isFinishedPath() {
        return (getDriveControlState() == DriveControlState.PATH_FOLLOWING_CONTROL && pathFollowingController.isDone())
                || getDriveControlState() != DriveControlState.PATH_FOLLOWING_CONTROL;
    }

    /**
     * Path Markers are an optional functionality that name the various
     * Waypoints in a Path with a String. This can make defining set locations
     * much easier.
     *
     * @return Set of Strings with Path Markers that the robot has crossed.
     */
    public synchronized Set<String> getPathMarkersCrossed() {
        if (getDriveControlState() != DriveControlState.PATH_FOLLOWING_CONTROL) {
            return null;
        } else {
            return pathFollowingController.getMarkersCrossed();
        }
    }

    /**
     * Configures talons for velocity speed control via closed loop control on the Talon SRX
     * This is used in auto and for other various control states that require velocity control
     */
    protected void configureTalonsForSpeedControl() {
        //TODO: this method one needs to be called once during init, regardless of drive control state, now that talon mode is passed with speed/percent/position/etc values
        //change this method to be an init method
        if (driveControlState != DriveControlState.PATH_FOLLOWING_CONTROL
                && driveControlState != DriveControlState.VELOCITY_HEADING_CONTROL
                && driveControlState != DriveControlState.VISION_FOLLOW) {
            leftMaster.configAllowableClosedloopError(kVelocityControlSlot, DriveConstants.kDriveVelocityAllowableError, DriveConstants.kCANBusConfigTimeoutMS);
            leftMaster.selectProfileSlot(kVelocityControlSlot,kVelocityControlSlot);
            rightMaster.selectProfileSlot(kVelocityControlSlot, kVelocityControlSlot);
            rightMaster.configAllowableClosedloopError(kVelocityControlSlot, DriveConstants.kDriveVelocityAllowableError, DriveConstants.kCANBusConfigTimeoutMS);
            setBrakeMode(true);
        }
    }

    /**
     * Zero's encoders
     */
    public void resetEncoders() {
        //Set the rotations to zero
        rightMaster.setSelectedSensorPosition(0, kVelocityControlSlot, DriveConstants.kCANBusConfigTimeoutMS);
        leftMaster.setSelectedSensorPosition(0, kVelocityControlSlot, DriveConstants.kCANBusConfigTimeoutMS);

        //Set the encoder position to zero (ticks)
        rightMaster.getSensorCollection().setQuadraturePosition(0, DriveConstants.kCANBusConfigTimeoutMS);
        leftMaster.getSensorCollection().setQuadraturePosition(0, DriveConstants.kCANBusConfigTimeoutMS);
    }

    /**
     * Reset's the gyro home point
     */
    public void zeroGyro() {
        if (navXGyro != null) {
            System.out.println("Reseting Gyro");
            try {
                navXGyro.reset();
            } catch  (Exception exc) {
                System.out.println("DANGER: Failed to reset Gyro" + exc.getMessage() + " ---- ");
                exc.printStackTrace();
            }
        } else {
            System.out.println("DANGER: NO GYRO!!!!");
        }
    }

    /**
     * @return gyro angle in degrees
     */
    public double getGyroAngleDegrees() {
        if (navXGyro != null)
        {
            return navXGyro.getAngle(); //NOTE: getAngle tracks all rotations from init, so it can go beyond 360 and -360
        } else {
            System.out.println("DANGER: NO GYRO!!!!");
            return 0;
        }
    }
    /**
     * @return gyro angle for multiple uses cartesian, radians, degrees, translation, rotation, interpolation, etc
     */

    public synchronized Rotation2d getGyroAngle() {
        return Rotation2d.fromDegrees(getGyroAngleDegrees());
    }

    /**
     * @return The gyro rate in degrees per second or angular velocity
     */

    public double getGyroRateDegrees() {
        if (navXGyro != null)
        {
            return navXGyro.getRate();
        } else {
            System.out.println("DANGER: NO GYRO!!!!");
            return 0;
        }
    }

    private double convertTicksToRotations(int ticks)
    {
        double rotations = ticks / (double) DriveConstants.kDriveEncoderTicksPerRot;
        return rotations;
    }

    public double getLeftDistanceInches() {
        //encoder spins opposite
        double wheelRotations = convertTicksToRotations(leftMaster.getSelectedSensorPosition(kVelocityControlSlot));
        return rotationsToInches(wheelRotations);
    }

    public double getRightDistanceInches() {
        //encoder spins opposite
        double wheelRotations = convertTicksToRotations(rightMaster.getSelectedSensorPosition(kVelocityControlSlot));
        return rotationsToInches(wheelRotations);
    }

    public double getLeftVelocityInchesPerSec() {
        //encoder spins opposite
        double wheelRotations = convertTicksToRotations(leftMaster.getSelectedSensorPosition(kVelocityControlSlot));
        return rpmToInchesPerSecond(wheelRotations);
    }

    public double getRightVelocityInchesPerSec() {
        //encoder spins opposite
        double wheelRotations = convertTicksToRotations(rightMaster.getSelectedSensorPosition(kVelocityControlSlot));
        return rpmToInchesPerSecond(wheelRotations);
    }

    public Loopable getLoopable() {
        return loopable;
    }

    public DriveControlState getDriveControlState() {
        return driveControlState;
    }

    public enum DriveControlState {
        OPEN_LOOP, VELOCITY_HEADING_CONTROL, PATH_FOLLOWING_CONTROL, VISION_FOLLOW;
    }
}

