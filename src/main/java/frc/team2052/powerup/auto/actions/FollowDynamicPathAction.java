package frc.team2052.powerup.auto.actions;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Translation2d;
import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.RobotState;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 3/15/2017.
 */
public class FollowDynamicPathAction implements Action {
    private DriveTrain mDrive = DriveTrain.getInstance();

    private Path mPath;
    private PathMode mPathMode;
    private boolean mHasStarted;
    private double mLastStatusTime;
    private boolean mReversed;
    private static Translation2d mRecordedPos;
    private Translation2d mTargetPos;

    public FollowDynamicPathAction(PathMode mode) {
        this.mPathMode = mode;
        this.mReversed = true; //assume returning to starting position if not passing a target pos, so reverse
        this.mHasStarted = false;
    }


    public FollowDynamicPathAction(PathMode mode, boolean reversed, Translation2d targetPoint) {
        this.mPathMode = mode;
        mReversed = reversed;
        mHasStarted = false;
        mTargetPos = targetPoint;
        System.out.println("XXXXXXXXXXXXX  Vision Target: x = " + targetPoint.getX() + "  y = " + targetPoint.getY());
    }

    @Override
    public boolean isFinished() {
        if(mPathMode != PathMode.RECORDPOINT) {
            boolean done = mDrive.isFinishedPath() && mHasStarted;
            if (done) {
                System.out.println("Finished path");
            }
            return done;
        } else {
            return true;
        }
    }

    @Override
    public void update()
    {
        if(mPathMode != PathMode.RECORDPOINT) {
            mHasStarted = true;
            if (Timer.getFPGATimestamp() > mLastStatusTime + 2) //only print out the loop is alive every 2 seconds to avoid filling log
            {
                System.out.println("Still waiting for path to complete. Remaining: " + mPath.getRemainingLength());
                mLastStatusTime = Timer.getFPGATimestamp();
            }
        }
    }

    @Override
    public void done() {

        if (mPathMode != PathMode.RECORDPOINT) {
            mDrive.setOpenLoop(DriveSignal.NEUTRAL);
        }
    }

    @Override
    public void start() {
        if (mPathMode == PathMode.RECORDPOINT) {
            mRecordedPos = RobotState.getInstance().getLatestFieldToVehicle().getValue().getTranslation();
            System.out.println("XXXXXXXX --- VISION  Robot START with RECORD POINT: x: " + mRecordedPos.getX() + "  y: " + mRecordedPos.getY());
        } else {
            if (mPathMode == PathMode.RUNPATHTORECORDEDPOINT) {
                mTargetPos = mRecordedPos;
            }
            RigidTransform2d robotPos = RobotState.getInstance().getLatestFieldToVehicle().getValue();
            List<Path.Waypoint> reverseVisionPath = new ArrayList();
            reverseVisionPath.add(new Path.Waypoint(new Translation2d(robotPos.getTranslation().getX(), robotPos.getTranslation().getY()), Constants.kPathFollowingMaxVel + AutoModeSelector.getTrimMaxSpeed()));
            reverseVisionPath.add(new Path.Waypoint(mTargetPos, Constants.kPathFollowingMaxVel + AutoModeSelector.getTrimMaxSpeed()));
            System.out.println("XXXXXXXX --- VISION  Robot RUN FROM: x: " + robotPos.getTranslation().getX() + "  y: " + robotPos.getTranslation().getY());
            System.out.println("XXXXXXXX --- VISION  Robot RUN TO: x: " + mTargetPos.getX() + "  y: " + mTargetPos.getY());
            mPath = new Path(reverseVisionPath);
            mDrive.followPath(mPath, mReversed);
        }
    }

    public enum PathMode{
        RECORDPOINT,
        RUNPATHTORECORDEDPOINT,
        RUNPATHTOTARGET
    }
}
