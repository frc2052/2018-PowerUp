package frc.team2052.powerup;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;


import java.util.ArrayList;
import java.util.List;
 
public class Util {
    //Finds value for deadzone (area with controller will not send information to optimize sensitivity)
    public static double checkForDeadzone(double value, double deadZone) {
        if (Math.abs(value) < deadZone)
            return 0;
        return value;
    }
     
    /** Get the difference in angle between two angles. 
   *From https://github.com/Team254/TrajectoryLib/blob/master/src/com/team254/lib/util/ChezyMath.java
   *
   * @param from The first angle
   * @param to The second angle
   * @return The change in angle from the first argument necessary to line up
   * with the second. Always between -Pi and Pi
   */
    public static double getDifferenceInAngleRadians(double from, double to) {
        return boundAngleNegPiToPiRadians(to - from);
    }

    public static double boundAngleNegPiToPiRadians(double angle) {
        while (angle >= Math.PI) {
            angle -= 2.0 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2.0 * Math.PI;
        }
        return angle;
    }

    public static double curve(double base, double power) {
        if (base == 0.0)
            return base;
        if (power == 0.0)
            return base;
        if (base > 0.0)
            return power * (Math.pow(base, 2));
        if (base < 0.0)
            return -(power * (Math.pow(base, 2)));
        return 0.0;
    }

    public static List<Path.Waypoint> inverseY(List<Path.Waypoint> waypoints) {
        List<Path.Waypoint> inversedWaypoints = new ArrayList<>();

        for (Path.Waypoint waypoint : waypoints) {
            if(waypoint.marker.isPresent()) {
                inversedWaypoints.add(new Path.Waypoint(new Translation2d(waypoint.position.getX(), -waypoint.position.getY()), waypoint.speed, waypoint.marker.get()));
            } else {
                inversedWaypoints.add(new Path.Waypoint(new Translation2d(waypoint.position.getX(), -waypoint.position.getY()), waypoint.speed));
            }
        }

        return inversedWaypoints;
    }

    public static double toRadians(double angle) {
        return angle * Math.PI / 180;
    }

    public static double limit(double value, double limit) {
        if (Math.abs(value) > limit) {
            if (value < 0) {
                return -limit;
            } else {
                return limit;
            }
        }
        return value;
    }

    public static double limitMin(double value, double minimum) {
        if (Math.abs(value) < minimum) {
            if (value < 0) {
                return -minimum;
            } else {
                return minimum;
            }
        }
        return value;
    }

    public static double boundAngle0to2PiRadians(double angle) {
        // Naive algorithm
        while (angle >= 2.0 * Math.PI) {
            angle -= 2.0 * Math.PI;
        }
        while (angle < 0.0) {
            angle += 2.0 * Math.PI;
        }
        return angle;
    }
}
