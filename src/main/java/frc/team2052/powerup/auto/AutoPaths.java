package frc.team2052.powerup.auto;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;

import java.util.ArrayList;
import java.util.List;

public class AutoPaths {

    public static List<Path.Waypoint> LLSwitch = new ArrayList();
    public static List<Path.Waypoint> LLScale = new ArrayList();
    public static List<Path.Waypoint> LRScale = new ArrayList();
    public static List<Path.Waypoint> CLSwitch = new ArrayList();
    public static List<Path.Waypoint> CRSwitch = new ArrayList();
    public static  List<Path.Waypoint> RLScale = new ArrayList();
    public static  List<Path.Waypoint> RRScale = new ArrayList();
    public static  List<Path.Waypoint> RRSwitch = new ArrayList();

    public void Init() {

        //start left go to left switch
        LLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        LLSwitch.add(new Path.Waypoint(new Translation2d(182, 0), 80));
        LLSwitch.add(new Path.Waypoint(new Translation2d(182, -44), 80));

        //start left go to left scale
        LLScale.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        LLScale.add(new Path.Waypoint(new Translation2d(318, 0), 80));
        LLScale.add(new Path.Waypoint(new Translation2d(318, -53), 80));

        //start left go to right scale
        LRScale.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        LRScale.add(new Path.Waypoint(new Translation2d(222, 0), 80));
        LRScale.add(new Path.Waypoint(new Translation2d(222, -242), 80));
        LRScale.add(new Path.Waypoint(new Translation2d(314, -242), 80));
        LRScale.add(new Path.Waypoint(new Translation2d(314, -189), 80));


        //start center go to left switch
        CLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        CLSwitch.add(new Path.Waypoint(new Translation2d(70, 0), 80));
        CLSwitch.add(new Path.Waypoint(new Translation2d(70, 54), 80));
        CLSwitch.add(new Path.Waypoint(new Translation2d(140, 54), 80));

        //start center go to right switch
        CRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        CRSwitch.add(new Path.Waypoint(new Translation2d(70, 0), 80));
        CRSwitch.add(new Path.Waypoint(new Translation2d(70, -54), 80));
        CRSwitch.add(new Path.Waypoint(new Translation2d(140, -54), 80));

        //start right go to left scale
        RLScale.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        RLScale.add(new Path.Waypoint(new Translation2d(222, 0), 80));
        RLScale.add(new Path.Waypoint(new Translation2d(222, 242), 80));
        RLScale.add(new Path.Waypoint(new Translation2d(314, 242), 80));
        RLScale.add(new Path.Waypoint(new Translation2d(314, 189), 80));

        //start right go to right scale
        RRScale.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        RRScale.add(new Path.Waypoint(new Translation2d(318, 0), 80));
        RRScale.add(new Path.Waypoint(new Translation2d(318, 30), 80));

        //start right go to right switch
        RRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        RRSwitch.add(new Path.Waypoint(new Translation2d(182, 0), 80));
        RRSwitch.add(new Path.Waypoint(new Translation2d(182, 44), 80));


    }
}
