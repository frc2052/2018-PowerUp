package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.auto.AutoModeEndedException;
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

    private FollowPathAction action;
    private double mTimeOut;
    private double mStartTime;
    private boolean hitAWall;

    private double lastLeftTicks = 0.0;
    private double lastRightTicks = 0.0;

    private double deltaLeftEncoder;
    private double deltaRightEncoder;

    private double lastRobotMovement;


    public TimeOutOrHaltedDriveAction(FollowPathAction action, double timeOut) {
        mTimeOut = timeOut;
        this.action = action;
    }

    @Override
    public boolean isFinished() {
        boolean quitEarly = Timer.getFPGATimestamp() - mStartTime >= mTimeOut
                || encoderFailureDetected  //Robot hasn't moved in the last .5 seconds
                || hitAWall;
        if (quitEarly) {
            DriveTrain.getInstance().abortCurrentPath();
        }

        return action.isFinished()
                || quitEarly;
    }

    @Override
    public void update() {
        action.update();
        deltaLeftEncoder = DriveTrain.getInstance().getLeftRawTicks() - lastLeftTicks;
        deltaRightEncoder = DriveTrain.getInstance().getRightRawTicks() - lastRightTicks;

        System.out.println("============LEFT CHANGE: " + deltaLeftEncoder + "===========RIGHT CHANGE: " + deltaRightEncoder);
        if (deltaLeftEncoder >= -10 && deltaLeftEncoder <= 10){
            System.out.println("LEFT WHEEL DIDN'T MOVE, TIme is:" + Timer.getFPGATimestamp());
        }else{
            lastRobotMovement = Timer.getFPGATimestamp();
            lastLeftTicks = DriveTrain.getInstance().getLeftRawTicks();
        }

        if (deltaRightEncoder >= -10 && deltaRightEncoder <= 10){
            System.out.println("RIGHT WHEEL DIDN'T MOVE, TIme is:" + Timer.getFPGATimestamp());
        }else{
            lastRobotMovement = Timer.getFPGATimestamp();
            lastRightTicks = DriveTrain.getInstance().getRightRawTicks();
        }

        //if one wheel gets 5 rotations further than the other wheel, there's a problem
        if (Math.abs(DriveTrain.getInstance().getLeftRawTicks()) + Constants.kEncoderFailureTicks < Math.abs(DriveTrain.getInstance().getRightRawTicks())
                || Math.abs(DriveTrain.getInstance().getRightRawTicks()) + Constants.kEncoderFailureTicks < Math.abs(DriveTrain.getInstance().getLeftRawTicks())){
            encoderFailureDetected = true;

            System.out.println("ERROR: ENCODER IS NOT REPORTING");
        }

        if (Timer.getFPGATimestamp() - lastRobotMovement >= .5) {
            hitAWall = true;
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

    private void forceException() throws AutoModeEndedException{
        throw new AutoModeEndedException();
    }
}
