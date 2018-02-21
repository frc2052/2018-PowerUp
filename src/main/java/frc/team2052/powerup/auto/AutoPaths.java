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
    public static List<Path.Waypoint> ReverseLRScale;
    public static List<Path.Waypoint> ReverseRLScale;


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
        ReverseLRScale = new ArrayList();
        ReverseRLScale = new ArrayList();

        AutoLine.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        AutoLine.add(new Path.Waypoint(new Translation2d(132, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));

        //start left go to left switch
        LLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LLSwitch.add(new Path.Waypoint(new Translation2d(120, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), 0), Constants.kPathFollowingMaxVel));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), 23 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        //start left go to left scale
        LLScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(75, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LLScale.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(241, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(264 + AutoModeSelector.getTrimX(), 22 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        //start left go to right scale
        LRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(200, 0), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(225, 25), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LRScale.add(new Path.Waypoint(new Translation2d(225, 160), Constants.kPathFollowingMaxVel/2));
        LRScale.add(new Path.Waypoint(new Translation2d(238, 195), Constants.kPathFollowingMaxVel/2));
        LRScale.add(new Path.Waypoint(new Translation2d(284 + AutoModeSelector.getTrimX(), 195 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));

        //start center go to left switch
        CLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        CLSwitch.add(new Path.Waypoint(new Translation2d(80, -60 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(100 + AutoModeSelector.getTrimX(), -60 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        //start center go to right switch
        CRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        CRSwitch.add(new Path.Waypoint(new Translation2d(80, 46 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(100 + AutoModeSelector.getTrimX(), 46 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        //start right go to left scale
        RLScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(200, 0), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(225, -25), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RLScale.add(new Path.Waypoint(new Translation2d(225, -160 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));
        RLScale.add(new Path.Waypoint(new Translation2d(238, -185), Constants.kPathFollowingMaxVel/2));
        RLScale.add(new Path.Waypoint(new Translation2d(271 + AutoModeSelector.getTrimX(), -185 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));

        //start right go to right scale
        RRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(75, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(150, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RRScale.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(241, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(266 + AutoModeSelector.getTrimX(), -20 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        //start right go to right switch
        RRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRSwitch.add(new Path.Waypoint(new Translation2d(120, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), 0), Constants.kPathFollowingMaxVel));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), -24 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        for(int i = RRScale.size() -1; i >= RRScale.size()-2; i--){
            ReverseRScale.add(RRScale.get(i));
        }

        for(int i = LRScale.size() -1; i >= LRScale.size()-2; i--){
            ReverseLRScale.add(LRScale.get(i));
        }

        for(int i = RLScale.size() -1; i >= RLScale.size()-2; i--){
            ReverseRLScale.add(RLScale.get(i));
        }

        for(int i = LLScale.size() -1; i >= LLScale.size()-2; i--){
            ReverseLScale.add(LLScale.get(i));
        }

    }
}
