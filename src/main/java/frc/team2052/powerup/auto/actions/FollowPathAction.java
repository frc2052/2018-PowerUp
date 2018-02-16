package frc.team2052.powerup.auto.actions;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

/**
 * Created by Adam on 3/15/2017.
 */
public class FollowPathAction implements Action {
    private DriveTrain mDrive = DriveTrain.getInstance();

    private Path mPath;
    private boolean mReversed;
    private boolean mHasStarted;

    public FollowPathAction(Path path, boolean reversed) {
        mPath = path;
        mReversed = reversed;
        mHasStarted = false;
    }

    @Override
    public boolean isFinished() {
        boolean done = mDrive.isFinishedPath() && mHasStarted;
        if (done) {
            System.out.println("Finished path");
        }
        return done;
    }

    @Override
    public void update()
    {
        mHasStarted = true;
        try {
            System.out.println("GYRO  " + mDrive.getGyroAngleDegrees() + "  REMAINING: " + mPath.getRemainingLength() + "  LEFT: " + mDrive.getLeftDistanceInches() + "  RIGHT: " + mDrive.getRightDistanceInches());
        } catch (Exception exc)
        {
            System.out.println("FAILURE: " + exc.getMessage());
        }
    }

    @Override
    public void done() {
        mDrive.setOpenLoop(DriveSignal.NEUTRAL);
    }

    @Override
    public void start() {
        mDrive.followPath(mPath, mReversed);
    }
}
