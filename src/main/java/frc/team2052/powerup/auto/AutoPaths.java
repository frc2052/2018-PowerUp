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
    public static List<Path.Waypoint> AutoLine;
    public static List<Path.Waypoint> ReverseRScale;
    public static List<Path.Waypoint> ReverseLScale;


    public static void Init() {
        LLSwitch = new ArrayList();
        LLScale = new ArrayList();
        LRScale = new ArrayList();
        CLSwitch = new ArrayList();
        CRSwitch = new ArrayList();
        RLScale = new ArrayList();
        RRScale = new ArrayList();
        RRSwitch = new ArrayList();
        AutoLine = new ArrayList();
        ReverseLScale = new ArrayList();
        ReverseRScale = new ArrayList();

        AutoLine.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        AutoLine.add(new Path.Waypoint(new Translation2d(132, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));

        //start left go to left switch
        LLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LLSwitch.add(new Path.Waypoint(new Translation2d(120, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150, 28), Constants.kPathFollowingMaxVel));

        //start left go to left scale
        LLScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(75, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LLScale.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(241, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(266, 20), Constants.kPathFollowingMaxVel));

        //start left go to right scale
        LRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(195, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LRScale.add(new Path.Waypoint(new Translation2d(195, 250), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(225, 250), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(241, 250), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(266, 230), Constants.kPathFollowingMaxVel));

        //start center go to left switch
        CLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        CLSwitch.add(new Path.Waypoint(new Translation2d(80, -75), Constants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(108, -75), Constants.kPathFollowingMaxVel));

        //start center go to right switch
        CRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        CRSwitch.add(new Path.Waypoint(new Translation2d(80, 54), Constants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(108, 54), Constants.kPathFollowingMaxVel));

        //start right go to left scale
        RLScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(195, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RLScale.add(new Path.Waypoint(new Translation2d(195, -250), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(225, -250), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(241, -250), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(266, -230), Constants.kPathFollowingMaxVel));

        //start right go to right scale
        RRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(75, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RRScale.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(241, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(266, -20), Constants.kPathFollowingMaxVel));

        //start right go to right switch
        RRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRSwitch.add(new Path.Waypoint(new Translation2d(120, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150, -28), Constants.kPathFollowingMaxVel));

        for(int i = RRScale.size() -1; i >= RRScale.size()-2; i--){
            ReverseRScale.add(RRScale.get(i));
        }

        for(int i = LLScale.size() -1; i >= LLScale.size()-2; i--){
            ReverseLScale.add(LLScale.get(i));
        }

    }
}
