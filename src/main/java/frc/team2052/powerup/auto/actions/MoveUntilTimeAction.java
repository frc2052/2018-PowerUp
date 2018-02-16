package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class MoveUntilTimeAction implements Action {

    private DriveSignal driveSignal;
    private double mTimeToWait;
    private double mStartTime;
    private DriveTrain mDrive = DriveTrain.getInstance();

    public MoveUntilTimeAction (double timeToWait, DriveSignal driveSignal) {
        mTimeToWait = timeToWait;
        this.driveSignal = driveSignal;
    }

    @Override
    public void done() {
        mDrive.setOpenLoop(DriveSignal.NEUTRAL);
    }

    @Override
    public boolean isFinished() {
        return mTimeToWait + mStartTime <= Timer.getFPGATimestamp();
    }

    @Override
    public void start()  {
        mStartTime = Timer.getFPGATimestamp();
    }

    @Override
    public void update() {
        mDrive.setOpenLoop(driveSignal);
    }
}
