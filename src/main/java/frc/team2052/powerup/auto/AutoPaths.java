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
    public static List<Path.Waypoint> RLSwitch;
    public static List<Path.Waypoint> LRSwitch;
    public static List<Path.Waypoint> RLScale;
    public static List<Path.Waypoint> RRScale;
    public static List<Path.Waypoint> RRSwitch;
    public static List<Path.Waypoint> AutoLine;
    public static List<Path.Waypoint> ReverseRScale;
    public static List<Path.Waypoint> ReverseLScale;
    public static List<Path.Waypoint> ReverseLRScale;
    public static List<Path.Waypoint> ReverseRLScale;
    public static List<Path.Waypoint> ReverseRSwitch;
    public static List<Path.Waypoint> ReverseLSwitch;
    public static List<Path.Waypoint> ReverseRRScale;
    public static List<Path.Waypoint> ReverseRRSwitch;
    public static List<Path.Waypoint> RScaleToRSwitchCube;
    public static List<Path.Waypoint> ReverseLLScale;
    public static List<Path.Waypoint> ReverseLLSwitch;
    public static List<Path.Waypoint> LScaleToLSwitchCube;

    public static void Init() {
        LLSwitch = new ArrayList();
        LLScale = new ArrayList();
        LRScale = new ArrayList();
        CLSwitch = new ArrayList();
        CRSwitch = new ArrayList();
        RLSwitch = new ArrayList();
        LRSwitch = new ArrayList();
        RLScale = new ArrayList();
        RRScale = new ArrayList();
        RRSwitch = new ArrayList();
        AutoLine = new ArrayList();
        ReverseLScale = new ArrayList();
        ReverseRScale = new ArrayList();
        ReverseLRScale = new ArrayList();
        ReverseRLScale = new ArrayList();
        ReverseRSwitch = new ArrayList();
        ReverseLSwitch = new ArrayList();
        ReverseRRScale = new ArrayList();
        RScaleToRSwitchCube = new ArrayList();
        ReverseRRSwitch = new ArrayList();
        ReverseLLScale = new ArrayList();
        LScaleToLSwitchCube = new ArrayList();
        ReverseLLSwitch = new ArrayList();

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

        RLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RLSwitch.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        RLSwitch.add(new Path.Waypoint(new Translation2d(225, -240), Constants.kPathFollowingMaxVel,"RaiseElevator"));
        RLSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), -240), Constants.kPathFollowingMaxVel));
        RLSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), -230 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        LRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LRSwitch.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        LRSwitch.add(new Path.Waypoint(new Translation2d(225, 240), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LRSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), 240), Constants.kPathFollowingMaxVel));
        LRSwitch.add(new Path.Waypoint(new Translation2d(150 + AutoModeSelector.getTrimX(), 230 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

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

        ReverseRSwitch.add(CRSwitch.get(AutoPaths.CRSwitch.size()-1));
        ReverseRSwitch.add(new Path.Waypoint(new Translation2d(CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getX() - 20, CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getY()), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseRSwitch.add(new Path.Waypoint(new Translation2d(CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getX() - 40, CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel));

        ReverseLSwitch.add(CLSwitch.get(AutoPaths.CLSwitch.size()-1));
        ReverseLSwitch.add(new Path.Waypoint(new Translation2d(CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getX() - 20, CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getY()), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseLSwitch.add(new Path.Waypoint(new Translation2d(CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getX() - 40, CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel));

        ReverseRRScale.add(RRScale.get(AutoPaths.RRScale.size()-1));
        ReverseRRScale.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 25, RRScale.get(AutoPaths.RRScale.size()-1).position.getY()+15), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseRRScale.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 30, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() + 30), Constants.kPathFollowingMaxVel));

        RScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 30, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() + 30), Constants.kPathFollowingMaxVel));
        RScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 45, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() - 10), Constants.kPathFollowingMaxVel));
        RScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 60, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel));

        ReverseRRSwitch.add(RRSwitch.get(AutoPaths.RRSwitch.size()-1));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX(), RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX() + 50, RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX() + 70, RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() - 10), Constants.kPathFollowingMaxVel));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX() + 80, RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() - 10), Constants.kPathFollowingMaxVel));

        ReverseLLScale.add(LLScale.get(AutoPaths.LLScale.size()-1));
        ReverseLLScale.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 25, LLScale.get(AutoPaths.LLScale.size()-1).position.getY()-15), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseLLScale.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 30, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() - 30), Constants.kPathFollowingMaxVel));

        LScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 30, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() - 30), Constants.kPathFollowingMaxVel));
        LScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 45, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() + 10), Constants.kPathFollowingMaxVel));
        LScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 60, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel));

        ReverseLLSwitch.add(LLSwitch.get(AutoPaths.LLSwitch.size()-1));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX(), LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX() + 50, LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX() + 70, LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() + 10), Constants.kPathFollowingMaxVel));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX() + 80, LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() + 10), Constants.kPathFollowingMaxVel));





    }
}
