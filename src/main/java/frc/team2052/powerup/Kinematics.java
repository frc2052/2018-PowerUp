package frc.team2052.powerup;

import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Rotation2d;

/**
 * Created by KnightKrawler on 1/19/2018.
 */
public class Kinematics {
    private static final double kEpsilon = 1E-9;

    /**
     * Forward kinematics using only encoders, rotation is implicit (less
     * accurate than below, but useful for predicting motion)
     * Forward kinematics computers raw motor configuration 
     * Reverse kinematics used to achieve desired configuration (error correction)
     */
    public static RigidTransform2d.Delta forwardKinematics(double left_wheel_delta, double right_wheel_delta) {
        double linear_velocity = (left_wheel_delta + right_wheel_delta) / 2; //Average additive change in distance at a given time
        double delta_v = (right_wheel_delta - left_wheel_delta) / 2; //Average change in distance between wheels at a given time
        double delta_rotation = delta_v * 2 * Constants.kTrackScrubFactor / Constants.kTrackEffectiveDiameter;
        //calculates change in rotation over time based on drive constants and average velocity difference between wheels
        return new RigidTransform2d.Delta(linear_velocity, 0, delta_rotation);
        //Returns coordinates for linear velocity and the rotational velocity calculated from this value (no gyro data)
    }

    /**
     * Forward kinematics using encoders and explicitly measured rotation (ex.
     * from gyro)
     */
    public static RigidTransform2d.Delta forwardKinematics(double left_wheel_delta, double right_wheel_delta,
                                                           double delta_rotation_rads) 
    {
        return new RigidTransform2d.Delta((left_wheel_delta + right_wheel_delta) / 2, 0, delta_rotation_rads);
    }
    //returns average total velocity and gyro data at given time

    /** Append the result of forward kinematics to a previous pose. */
    public static RigidTransform2d integrateForwardKinematics(RigidTransform2d current_pose, double left_wheel_delta,
                                                              double right_wheel_delta, Rotation2d current_heading) {
        RigidTransform2d.Delta with_gyro = forwardKinematics(left_wheel_delta, right_wheel_delta,
                current_pose.getRotation().inverse().rotateBy(current_heading).getRadians());
        return current_pose.transformBy(RigidTransform2d.fromVelocity(with_gyro));
    }
    
    //Creates final (unchangeable) velocity values that will be used at a set time
    public static class DriveVelocity {
        public final double left;
        public final double right;
        
        //Records the set left and right velocity values
        public DriveVelocity(double left, double right) {
            this.left = left;
            this.right = right;
        }
    }
    
    //Main error correcting code-changes polar coordinates if they are less than kEpsilon constant 10e-9
    public static DriveVelocity inverseKinematics(RigidTransform2d.Delta velocity) {
        //If the change in angle is really small ignore it
        if (Math.abs(velocity.dtheta) < kEpsilon) {
            return new DriveVelocity(velocity.dx, velocity.dx);
        }
        //Calculate the turning speed based off the change in angle and the scrub factor
        double delta_v = Constants.kTrackEffectiveDiameter * velocity.dtheta / (2 * Constants.kTrackScrubFactor);
        return new DriveVelocity(velocity.dx - delta_v, velocity.dx + delta_v);
    }
}
