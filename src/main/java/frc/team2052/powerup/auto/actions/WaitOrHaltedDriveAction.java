package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

//Sets amount of time delay before auto mode deploys. 
public class WaitOrHaltedDriveAction implements Action {

    private double mTimeToWait;
    private double mStartTime;

    private double lastLeftTicks = 0.0;
    private double lastrightTicks = 0.0;

    private double deltaLeftEncoder;
    private double deltaRightEncoder;

    private double lastRobotMovement;

    public WaitOrHaltedDriveAction(double timeToWait) {
        mTimeToWait = timeToWait;
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - mStartTime >= mTimeToWait || Timer.getFPGATimestamp() - lastRobotMovement > .5;
    }

    @Override
    public void update() {
        deltaLeftEncoder = DriveTrain.getInstance().getLeftRawTicks() - lastLeftTicks;
        deltaRightEncoder = DriveTrain.getInstance().getRightRawTicks() - lastrightTicks;

        if (deltaLeftEncoder >= -10 && deltaLeftEncoder <= 10 && deltaRightEncoder >= -10 && deltaRightEncoder <= 10){
            System.out.println("ROBOT DIDNT MOVE, TIme is:" + Timer.getFPGATimestamp());
        }else{
            lastRobotMovement = Timer.getFPGATimestamp();
        }

        lastLeftTicks = DriveTrain.getInstance().getLeftRawTicks();
        lastrightTicks = DriveTrain.getInstance().getRightRawTicks();
    }

    @Override
    public void done() {
    }

    @Override
    public void start() {
        mStartTime = Timer.getFPGATimestamp();
    }
}
