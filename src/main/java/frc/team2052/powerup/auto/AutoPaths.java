package frc.team2052.powerup.auto;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lancelot on 1/19/2018.
 */
public class AutoPaths {

    public static List<Path.Waypoint> Autoline = new ArrayList();
    public static List<Path.Waypoint> LLSwitch = new ArrayList();
    public static List<Path.Waypoint> LLScale = new ArrayList();
    public static List<Path.Waypoint> LRScale = new ArrayList();
    public static List<Path.Waypoint> CLSwitch = new ArrayList();
    public static List<Path.Waypoint> CRSwitch = new ArrayList();
    public static List<Path.Waypoint> RLScale = new ArrayList();
    public static List<Path.Waypoint> RRScale = new ArrayList();
    public static List<Path.Waypoint> RRSwitch = new ArrayList();

    public void Init() {

        //start left or right go past autoline
        Autoline.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        Autoline.add(new Path.Waypoint(new Translation2d(120 + AutoModeSelector.getTrimX(), 0 + AutoModeSelector.getTrimY()), 40));

        //start left go to left switch
        LLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        LLSwitch.add(new Path.Waypoint(new Translation2d(182, 0), 40, "RaiseElevator"));
        LLSwitch.add(new Path.Waypoint(new Translation2d(182 + AutoModeSelector.getTrimX(), -44 + AutoModeSelector.getTrimY()), 40));

        //start left go to left scale
        LLScale.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        LLScale.add(new Path.Waypoint(new Translation2d(318, 0), 40, "RaiseElevator"));
        LLScale.add(new Path.Waypoint(new Translation2d(318 + AutoModeSelector.getTrimX(), -53 + AutoModeSelector.getTrimY()), 40));

        //start left go to right scale
        LRScale.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        LRScale.add(new Path.Waypoint(new Translation2d(222, 0), 40, "RaiseElevator"));
        LRScale.add(new Path.Waypoint(new Translation2d(222, -242), 40));
        LRScale.add(new Path.Waypoint(new Translation2d(314, -242), 40));
        LRScale.add(new Path.Waypoint(new Translation2d(314 + AutoModeSelector.getTrimX(), -189 + AutoModeSelector.getTrimY()), 40));


        //start center go to left switch
        CLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        CLSwitch.add(new Path.Waypoint(new Translation2d(70, 0), 40, "RaiseElevator"));
        CLSwitch.add(new Path.Waypoint(new Translation2d(70, 54), 40));
        CLSwitch.add(new Path.Waypoint(new Translation2d(140 + AutoModeSelector.getTrimX(), 54 + AutoModeSelector.getTrimY()), 40));

        //start center go to right switch
        CRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        CRSwitch.add(new Path.Waypoint(new Translation2d(70, 0), 40, "RaiseElevator"));
        CRSwitch.add(new Path.Waypoint(new Translation2d(70, -54), 40));
        CRSwitch.add(new Path.Waypoint(new Translation2d(140 + AutoModeSelector.getTrimX(), -54 + AutoModeSelector.getTrimY()), 40));

        //start right go to left scale
        RLScale.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        RLScale.add(new Path.Waypoint(new Translation2d(222, 0), 40, "RaiseElevator"));
        RLScale.add(new Path.Waypoint(new Translation2d(222, 242), 40));
        RLScale.add(new Path.Waypoint(new Translation2d(314, 242), 40));
        RLScale.add(new Path.Waypoint(new Translation2d(314 + AutoModeSelector.getTrimX(), 189 + AutoModeSelector.getTrimY()), 40));

        //start right go to right scale
        RRScale.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        RRScale.add(new Path.Waypoint(new Translation2d(318, 0), 40, "RaiseElevator"));
        RRScale.add(new Path.Waypoint(new Translation2d(318 + AutoModeSelector.getTrimX(), 30 + AutoModeSelector.getTrimY()), 40));

        //start right go to right switch
        RRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        RRSwitch.add(new Path.Waypoint(new Translation2d(182, 0), 40, "RaiseElevator"));
        RRSwitch.add(new Path.Waypoint(new Translation2d(182 + AutoModeSelector.getTrimX(), 44 + AutoModeSelector.getTrimY()), 40));

    }
}
