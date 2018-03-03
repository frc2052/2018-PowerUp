package frc.team2052.powerup.auto.actions;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.RobotState;

import java.util.ArrayList;
import java.util.List;

public class TrackRobotPosAction implements Action{

    private boolean isFinished;
    private TrackRobotEnum state;
    private double startTheta;
    private Translation2d startTranslation;

    private RigidTransform2d robotPos;

    public static List<Path.Waypoint> ReverseVisionPath;

    public TrackRobotPosAction(TrackRobotEnum state){
        ReverseVisionPath = new ArrayList();
        this.state = state;
    }
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void start() {
        switch (state) {
            case START:
                robotPos = RobotState.getInstance().getLatestFieldToVehicle().getValue();
                startTheta = robotPos.getRotation().getDegrees();
                startTranslation = robotPos.getTranslation();
                break;
            case END:
                robotPos = RobotState.getInstance().getLatestFieldToVehicle().getValue();
                ReverseVisionPath.add(new Path.Waypoint(robotPos.getTranslation(), Constants.kPathFollowingMaxVel));
                ReverseVisionPath.add(new Path.Waypoint(startTranslation, Constants.kPathFollowingMaxVel));
                break;
            case RESET:
                ReverseVisionPath.clear();
                break;
        }
        isFinished = true;
    }

    @Override
    public void update() {

    }

    public enum TrackRobotEnum{
        START,
        END,
        RESET
    }
}
