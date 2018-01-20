package frc.team2052.powerup.subsystems.drive;

import com.first.team2052.lib.vec.Rotation2d;

/**
 * Created by KnightKrawler on 1/19/2018.
 */
public class VelocityHeadingSetpoint {
    private final double leftSpeed_; //todo: why are there underscores
    private final double rightSpeed_;
    private final Rotation2d headingSetpoint_;

    // Constructor for straight line motion
    public VelocityHeadingSetpoint(double leftSpeed, double rightSpeed, Rotation2d headingSetpoint) {
        leftSpeed_ = leftSpeed;
        rightSpeed_ = rightSpeed;
        headingSetpoint_ = headingSetpoint;
    }

    public double getLeftSpeed() {
        return leftSpeed_;
    }

    public double getRightSpeed() {
        return rightSpeed_;
    }

    public Rotation2d getHeading() {
        return headingSetpoint_;
    }
}
