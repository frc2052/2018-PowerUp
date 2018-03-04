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
    private static double startTheta;
    private static Translation2d startTranslation;

    private static List<Path.Waypoint> ReverseVisionPath;

    public TrackRobotPosAction(TrackRobotEnum state){
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
        RigidTransform2d robotPos;
        switch (state) {
            case START:
                robotPos = RobotState.getInstance().getLatestFieldToVehicle().getValue();
                startTheta = robotPos.getRotation().getDegrees();
                startTranslation = robotPos.getTranslation();
                System.out.println("XXXXXXXX --- VISION saving return point " + startTranslation.getX() + "  " + startTranslation.getY());
                break;
            case END:
                robotPos = RobotState.getInstance().getLatestFieldToVehicle().getValue();
                ReverseVisionPath = new ArrayList();
                ReverseVisionPath.add(new Path.Waypoint(new Translation2d(-robotPos.getTranslation().getX(), robotPos.getTranslation().getY()), Constants.kPathFollowingMaxVel));
                ReverseVisionPath.add(new Path.Waypoint(startTranslation, Constants.kPathFollowingMaxVel));
                System.out.println("XXXXXXXX --- VISION  Robot CUBE: x: " + robotPos.getTranslation().getX() + "  y: " + robotPos.getTranslation().getY());
                System.out.println("XXXXXXXX --- VISION  Robot START: x: " + startTranslation.getX() + "  y: " + startTranslation.getY());
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

    public static List<Path.Waypoint> getReverseVisionPathFromCurrentPos() {
        RigidTransform2d robotPos = RobotState.getInstance().getLatestFieldToVehicle().getValue();
        List<Path.Waypoint> reverseVisionPath = new ArrayList();
        reverseVisionPath.add(new Path.Waypoint(new Translation2d(-robotPos.getTranslation().getX(), robotPos.getTranslation().getY()), Constants.kPathFollowingMaxVel));
        reverseVisionPath.add(new Path.Waypoint(startTranslation, Constants.kPathFollowingMaxVel));
        System.out.println("XXXXXXXX --- VISION  Robot CUBE: x: " + robotPos.getTranslation().getX() + "  y: " + robotPos.getTranslation().getY());
        System.out.println("XXXXXXXX --- VISION  Robot START: x: " + startTranslation.getX() + "  y: " + startTranslation.getY());
        return reverseVisionPath;
    }

    public enum TrackRobotEnum{
        START,
        END,
        RESET
    }
}
