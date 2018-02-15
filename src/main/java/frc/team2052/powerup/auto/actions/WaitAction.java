package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;

//Sets amount of time delay before auto mode deploys. 
public class WaitAction implements Action {

    private double mTimeToWait;
    private double mStartTime;

    public WaitAction(double timeToWait) {
        mTimeToWait = timeToWait;
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - mStartTime >= mTimeToWait;
    }

    @Override
    public void update() {
    }

    @Override
    public void done() {
    }

    @Override
    public void start() {
        mStartTime = Timer.getFPGATimestamp();
    }
}
