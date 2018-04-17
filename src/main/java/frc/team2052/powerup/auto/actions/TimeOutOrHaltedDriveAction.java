package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

//Sets amount of time delay before auto mode deploys. 
public class TimeOutOrHaltedDriveAction implements Action {

    private static boolean encoderFailureDetected;
    public static boolean getEncoderFailureDetected()
    {
        return encoderFailureDetected;
    }

    public static void resetCriticalFailure() {
        encoderFailureDetected = false;
    }

    private Action action;
    private double mTimeOut;
    private double mStartTime;
    private boolean hitAWall;
    private boolean isDone;

    private double lastLeftTicks = 0.0;
    private double lastRightTicks = 0.0;

    private double deltaLeftEncoder;
    private double deltaRightEncoder;

    private double lastRobotMovement;


    public TimeOutOrHaltedDriveAction(FollowPathAction action, double timeOut) {
        mTimeOut = timeOut;
        this.action = action;
    }

    public TimeOutOrHaltedDriveAction(FollowDynamicPathAction action, double timeOut) {
        mTimeOut = timeOut;
        this.action = action;
    }

    @Override
    public boolean isFinished() {
        if (!this.isDone) {
            boolean overTime = Timer.getFPGATimestamp() - mStartTime >= mTimeOut;
            boolean quitEarly = overTime
                    || encoderFailureDetected  //Robot hasn't moved in the last .5 seconds
                    || hitAWall;
            if (quitEarly) {
                System.out.println("TimeOutOrHalted QUIT EARLY : Hit a wall = " + hitAWall + "  TIMEOUT: " + overTime);
                DriveTrain.getInstance().abortCurrentPath();
            }

            this.isDone = action.isFinished() || quitEarly;
        }
        return isDone;
    }

    @Override
    public void update() {
        if (!this.isDone) {
            action.update();
            deltaLeftEncoder = DriveTrain.getInstance().getLeftRawTicks() - lastLeftTicks;
            deltaRightEncoder = DriveTrain.getInstance().getRightRawTicks() - lastRightTicks;

            if (deltaLeftEncoder >= -10 && deltaLeftEncoder <= 10) {
                System.out.println("WARNING: LEFT WHEEL DIDN'T MOVE, TIme is:" + Timer.getFPGATimestamp());
            } else {
                lastRobotMovement = Timer.getFPGATimestamp();
                lastLeftTicks = DriveTrain.getInstance().getLeftRawTicks();
            }

            if (deltaRightEncoder >= -10 && deltaRightEncoder <= 10) {
                System.out.println("WARNING: RIGHT WHEEL DIDN'T MOVE, TIme is:" + Timer.getFPGATimestamp());
            } else {
                lastRobotMovement = Timer.getFPGATimestamp();
                lastRightTicks = DriveTrain.getInstance().getRightRawTicks();
            }

            //if one wheel gets 5 rotations further than the other wheel, there's a problem
            if (Math.abs(DriveTrain.getInstance().getLeftRawTicks()) + Constants.kEncoderFailureTicks < Math.abs(DriveTrain.getInstance().getRightRawTicks())
                    || Math.abs(DriveTrain.getInstance().getRightRawTicks()) + Constants.kEncoderFailureTicks < Math.abs(DriveTrain.getInstance().getLeftRawTicks())) {
                encoderFailureDetected = true;

                System.out.println("ERROR: ENCODER IS NOT REPORTING");
            }

            if (Timer.getFPGATimestamp() - lastRobotMovement >= .5) {
                hitAWall = true;
            }
        }
        else
        {
            System.out.println("TimeOutOrHaltedDriveAction : Update called but action is already done. " + Timer.getFPGATimestamp());
        }
    }

    @Override
    public void done() {
    }

    @Override
    public void start() {
        mStartTime = Timer.getFPGATimestamp();
        lastRobotMovement = mStartTime;
        action.start();
    }
}
