package frc.team2052.powerup.auto;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.Constants;

import java.util.ArrayList;
import java.util.List;

public class AutoPaths {


    public static List<Path.Waypoint> LLSwitch;
    public static List<Path.Waypoint> LLScale;
    public static List<Path.Waypoint> LRScale;
    public static List<Path.Waypoint> CLSwitch;
    public static List<Path.Waypoint> CRSwitch;
    public static List<Path.Waypoint> RLScale;
    public static List<Path.Waypoint> RRScale;
    public static List<Path.Waypoint> RRSwitch;
    public static List<Path.Waypoint> TestRun;

    public static void Init() {
        LLSwitch = new ArrayList();
        LLScale = new ArrayList();
        LRScale = new ArrayList();
        CLSwitch = new ArrayList();
        CRSwitch = new ArrayList();
        RLScale = new ArrayList();
        RRScale = new ArrayList();
        RRSwitch = new ArrayList();
        TestRun = new ArrayList();

        TestRun.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        TestRun.add(new Path.Waypoint(new Translation2d(50, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));

        //start left go to left switch
        LLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150, 28), Constants.kPathFollowingMaxVel));

        //start left go to left scale
        LLScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(305, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LLScale.add(new Path.Waypoint(new Translation2d(305, 12), Constants.kPathFollowingMaxVel));

        //start left go to right scale
        LRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(195, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LRScale.add(new Path.Waypoint(new Translation2d(195, 250), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(305, 250), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(305, 228), Constants.kPathFollowingMaxVel));

        //start center go to left switch
        CLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        CLSwitch.add(new Path.Waypoint(new Translation2d(80, -54), Constants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(112, -54), Constants.kPathFollowingMaxVel));

        //start center go to right switch
        CRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        CRSwitch.add(new Path.Waypoint(new Translation2d(80, 54), Constants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(112, 54), Constants.kPathFollowingMaxVel));

        //start right go to left scale
        RLScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(195, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RLScale.add(new Path.Waypoint(new Translation2d(195, -250), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(305, -250), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(305, -228), Constants.kPathFollowingMaxVel));

        //start right go to right scale
        RRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(305, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RRScale.add(new Path.Waypoint(new Translation2d(305, -12), Constants.kPathFollowingMaxVel));

        //start right go to right switch
        RRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150, -28), Constants.kPathFollowingMaxVel));

    }
}
