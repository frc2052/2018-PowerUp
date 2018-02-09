package frc.team2052.powerup.auto;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.constants.DriveConstants;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lancelot on 1/19/2018.
 */
public class AutoPaths {

    public static List<Path.Waypoint> LLSwitch = new ArrayList();
    public static List<Path.Waypoint> LLScale = new ArrayList();
    public static List<Path.Waypoint> LRScale = new ArrayList();
    public static List<Path.Waypoint> CLSwitch = new ArrayList();
    public static List<Path.Waypoint> CRSwitch = new ArrayList();
    public static List<Path.Waypoint> RLScale = new ArrayList();
    public static List<Path.Waypoint> RRScale = new ArrayList();
    public static List<Path.Waypoint> RRSwitch = new ArrayList();

    public static void Init() {

        //start left go to left switch
        LLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        LLSwitch.add(new Path.Waypoint(new Translation2d(150, 28), DriveConstants.kPathFollowingMaxVel));

        //start left go to left scale
        LLScale.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        LLScale.add(new Path.Waypoint(new Translation2d(305, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        LLScale.add(new Path.Waypoint(new Translation2d(305, 12), DriveConstants.kPathFollowingMaxVel));

        //start left go to right scale
        LRScale.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(195, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        LRScale.add(new Path.Waypoint(new Translation2d(195, 250), DriveConstants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(305, 250), DriveConstants.kPathFollowingMaxVel));
        LRScale.add(new Path.Waypoint(new Translation2d(305, 228), DriveConstants.kPathFollowingMaxVel));


        //start center go to left switch
        CLSwitch.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(30, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        CLSwitch.add(new Path.Waypoint(new Translation2d(80, -54), DriveConstants.kPathFollowingMaxVel));
        CLSwitch.add(new Path.Waypoint(new Translation2d(112, -54), DriveConstants.kPathFollowingMaxVel));

        //start center go to right switch
        CRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(30, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        CRSwitch.add(new Path.Waypoint(new Translation2d(80, 54), DriveConstants.kPathFollowingMaxVel));
        CRSwitch.add(new Path.Waypoint(new Translation2d(112, 54), DriveConstants.kPathFollowingMaxVel));

        //start right go to left scale
        RLScale.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(195, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        RLScale.add(new Path.Waypoint(new Translation2d(195, -250), DriveConstants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(305, -250), DriveConstants.kPathFollowingMaxVel));
        RLScale.add(new Path.Waypoint(new Translation2d(305, -228), DriveConstants.kPathFollowingMaxVel));

        //start right go to right scale
        RRScale.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        RRScale.add(new Path.Waypoint(new Translation2d(305, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        RRScale.add(new Path.Waypoint(new Translation2d(305, -12), DriveConstants.kPathFollowingMaxVel));

        //start right go to right switch
        RRSwitch.add(new Path.Waypoint(new Translation2d(0, 0), DriveConstants.kPathFollowingMaxVel));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150, 0), DriveConstants.kPathFollowingMaxVel, "RaiseElevator"));
        RRSwitch.add(new Path.Waypoint(new Translation2d(150, -28), DriveConstants.kPathFollowingMaxVel));


    }
}
