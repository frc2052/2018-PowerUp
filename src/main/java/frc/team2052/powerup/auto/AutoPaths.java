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
    public static List<Path.Waypoint> ReverseLRSwitch;
    public static List<Path.Waypoint> LScaleToLSwitchCube;
    public static List<Path.Waypoint> Reverse2LSwitch;
    public static List<Path.Waypoint> Reverse2RSwitch;
    public static List<Path.Waypoint> LCube2LScale;
    public static List<Path.Waypoint> RCube2RScale;
    public static List<Path.Waypoint> CrossingLScaleToLSwitchCube;
    public static List<Path.Waypoint> CrossingRScaleToRSwitchCube;


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
        ReverseLRSwitch = new ArrayList();
        Reverse2LSwitch = new ArrayList();
        Reverse2RSwitch = new ArrayList();
        LCube2LScale = new ArrayList();
        RCube2RScale = new ArrayList();
        CrossingLScaleToLSwitchCube = new ArrayList();
        CrossingRScaleToRSwitchCube = new ArrayList();

        int maxSpeedTrim = AutoModeSelector.getTrimMaxSpeed();

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
        LLScale.add(new Path.Waypoint(new Translation2d(125, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LLScale.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(241, 0), Constants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(260 + AutoModeSelector.getTrimX(), 14 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

        //start left go to right scale
        LRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(200, 0), Constants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(225, 25), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        LRScale.add(new Path.Waypoint(new Translation2d(225, 190 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));
        LRScale.add(new Path.Waypoint(new Translation2d(225, 204), Constants.kPathFollowingMaxVel/2));
        LRScale.add(new Path.Waypoint(new Translation2d(240, 215), Constants.kPathFollowingMaxVel/2));
        LRScale.add(new Path.Waypoint(new Translation2d(265 + AutoModeSelector.getTrimX(), 204 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));

        //start center go to left switch
        CLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel + maxSpeedTrim));
        CLSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel + maxSpeedTrim, "RaiseElevator"));
        CLSwitch.add(new Path.Waypoint(new Translation2d(80, -60 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));
        CLSwitch.add(new Path.Waypoint(new Translation2d(100 + AutoModeSelector.getTrimX(), -60 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        //start center go to right switch
        CRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel + maxSpeedTrim));
        CRSwitch.add(new Path.Waypoint(new Translation2d(30, 0), Constants.kPathFollowingMaxVel + maxSpeedTrim, "RaiseElevator"));
        CRSwitch.add(new Path.Waypoint(new Translation2d(80, 46 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));
        CRSwitch.add(new Path.Waypoint(new Translation2d(100 + AutoModeSelector.getTrimX(), 46 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        //start right go to left scale
        RLScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(200, 0), Constants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(225, -25), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RLScale.add(new Path.Waypoint(new Translation2d(225, -190 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));
        RLScale.add(new Path.Waypoint(new Translation2d(225, -204 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));
        RLScale.add(new Path.Waypoint(new Translation2d(240, -215), Constants.kPathFollowingMaxVel/2));
        RLScale.add(new Path.Waypoint(new Translation2d(265 + AutoModeSelector.getTrimX(), -204 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel/2));

        //start right go to right scale
        RRScale.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(75, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(125, 0), Constants.kPathFollowingMaxVel, "RaiseElevator"));
        RRScale.add(new Path.Waypoint(new Translation2d(225, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(241, 0), Constants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(265 + AutoModeSelector.getTrimX(), -14 + AutoModeSelector.getTrimY()), Constants.kPathFollowingMaxVel));

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

        for(int i = LLScale.size() -1; i >= LLScale.size()-2; i--){
            ReverseLScale.add(LLScale.get(i));
        }

        ReverseRSwitch.add(CRSwitch.get(AutoPaths.CRSwitch.size()-1));
        ReverseRSwitch.add(new Path.Waypoint(new Translation2d(CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getX() - 20, CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getY()), Constants.kPathFollowingMaxVel + maxSpeedTrim, "LowerElevator"));
        ReverseRSwitch.add(new Path.Waypoint(new Translation2d(CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getX() - 30, CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        ReverseLSwitch.add(CLSwitch.get(AutoPaths.CLSwitch.size()-1));
        ReverseLSwitch.add(new Path.Waypoint(new Translation2d(CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getX() - 20, CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getY()), Constants.kPathFollowingMaxVel + maxSpeedTrim, "LowerElevator"));
        ReverseLSwitch.add(new Path.Waypoint(new Translation2d(CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getX() - 30, CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        ReverseRRScale.add(RRScale.get(AutoPaths.RRScale.size()-1));
        ReverseRRScale.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 6, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() + 5), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseRRScale.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 12, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() + 10), Constants.kPathFollowingMaxVel));

        RScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(258, -20), Constants.kPathFollowingMaxVel/2));
        RScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(255, -40), Constants.kPathFollowingMaxVel/2));
        RScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(251, -41), Constants.kPathFollowingMaxVel/2));

        ReverseRRSwitch.add(RRSwitch.get(AutoPaths.RRSwitch.size()-1));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX(), RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX() + 60, RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX() + 70, RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() - 25), Constants.kPathFollowingMaxVel));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX() + 75, RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel));
        ReverseRRSwitch.add(new Path.Waypoint(new Translation2d(RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getX() + 80, RRSwitch.get(AutoPaths.RRSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel));

        ReverseLLScale.add(LLScale.get(AutoPaths.LLScale.size()-1));
        ReverseLLScale.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 6, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() - 5), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseLLScale.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 12, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() - 10), Constants.kPathFollowingMaxVel));

        LScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(258, 20), Constants.kPathFollowingMaxVel/2));
        LScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(255, 40), Constants.kPathFollowingMaxVel/2));
        LScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(251, 41), Constants.kPathFollowingMaxVel/2));

        ReverseLLSwitch.add(LLSwitch.get(AutoPaths.LLSwitch.size()-1));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX(), LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel, "LowerElevator"));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX() + 60, LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX() + 70, LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() + 25), Constants.kPathFollowingMaxVel));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX() + 75, LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel));
        ReverseLLSwitch.add(new Path.Waypoint(new Translation2d(LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getX() + 80, LLSwitch.get(AutoPaths.LLSwitch.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel));

        ReverseLRScale.add(LRScale.get(AutoPaths.LRScale.size()-1));
        ReverseLRScale.add(new Path.Waypoint(new Translation2d(LRScale.get(AutoPaths.LRScale.size()-1).position.getX() - 10, LRScale.get(AutoPaths.LRScale.size()-1).position.getY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        Reverse2LSwitch.add(CLSwitch.get(AutoPaths.CLSwitch.size()-1));
        Reverse2LSwitch.add(new Path.Waypoint(new Translation2d(CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getX() - 20, CLSwitch.get(AutoPaths.CLSwitch.size()-1).position.getY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        ReverseRLScale.add(RLScale.get(AutoPaths.RLScale.size()-1));
        ReverseRLScale.add(new Path.Waypoint(new Translation2d(RLScale.get(AutoPaths.RLScale.size()-1).position.getX() - 10, RLScale.get(AutoPaths.RLScale.size()-1).position.getY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        Reverse2RSwitch.add(CRSwitch.get(AutoPaths.CRSwitch.size()-1));
        Reverse2RSwitch.add(new Path.Waypoint(new Translation2d(CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getX() - 20, CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getY()), Constants.kPathFollowingMaxVel + maxSpeedTrim));

        LCube2LScale.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 25, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() + 20), Constants.kPathFollowingMaxVel));
        LCube2LScale.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 20, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() + 5 ), Constants.kPathFollowingMaxVel));
        LCube2LScale.add(new Path.Waypoint(new Translation2d(LLScale.get(AutoPaths.LLScale.size()-1).position.getX() - 20, LLScale.get(AutoPaths.LLScale.size()-1).position.getY() - 5), Constants.kPathFollowingMaxVel));

        RCube2RScale.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 25, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() - 20), Constants.kPathFollowingMaxVel));
        RCube2RScale.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 20, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() - 5 ), Constants.kPathFollowingMaxVel));
        RCube2RScale.add(new Path.Waypoint(new Translation2d(RRScale.get(AutoPaths.RRScale.size()-1).position.getX() - 20, RRScale.get(AutoPaths.RRScale.size()-1).position.getY() + 5), Constants.kPathFollowingMaxVel));

        CrossingLScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(RLScale.get(AutoPaths.RLScale.size()-1).position.getX() - 2, RLScale.get(AutoPaths.RLScale.size()-1).position.getY() + 6), Constants.kPathFollowingMaxVel/2));
        CrossingLScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(RLScale.get(AutoPaths.RLScale.size()-1).position.getX() - 5, RLScale.get(AutoPaths.RLScale.size()-1).position.getY() + 21), Constants.kPathFollowingMaxVel/2));
        CrossingLScaleToLSwitchCube.add(new Path.Waypoint(new Translation2d(RLScale.get(AutoPaths.RLScale.size()-1).position.getX() - 9, RLScale.get(AutoPaths.RLScale.size()-1).position.getY() + 22), Constants.kPathFollowingMaxVel/2));

        CrossingRScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(LRScale.get(AutoPaths.LRScale.size()-1).position.getX() - 2, LRScale.get(AutoPaths.LRScale.size()-1).position.getY() - 6), Constants.kPathFollowingMaxVel/2));
        CrossingRScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(LRScale.get(AutoPaths.LRScale.size()-1).position.getX() - 5, LRScale.get(AutoPaths.LRScale.size()-1).position.getY() - 21), Constants.kPathFollowingMaxVel/2));
        CrossingRScaleToRSwitchCube.add(new Path.Waypoint(new Translation2d(LRScale.get(AutoPaths.LRScale.size()-1).position.getX() - 9, LRScale.get(AutoPaths.LRScale.size()-1).position.getY() - 22), Constants.kPathFollowingMaxVel/2));

    }
}
