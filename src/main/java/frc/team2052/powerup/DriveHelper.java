package frc.team2052.powerup;

import frc.team2052.powerup.constants.DriveConstants;
import frc.team2052.powerup.subsystems.drive.DriveSignal;

public class DriveHelper {
    double mQuickStopAccumulator;
    private DriveSignal mSignal = new DriveSignal(0, 0);

    /**
     * Helper for driving
     * the "turning" stick controls the curvature of the robot's path rather than
     * its rate of heading change. This helps make the robot more controllable at
     * high speeds. Also handles the robot's quick turn functionality - "quick turn"
     * overrides constant-curvature turning for turn-in-place maneuvers.
     */
    public DriveSignal drive(double throttle, double wheel, boolean isQuickTurn) {
        wheel = handleDeadband(wheel, DriveConstants.kWheelDeadband);
        throttle = handleDeadband(throttle, DriveConstants.kThrottleDeadband);

        double overPower;

        double angularPower;

        if (isQuickTurn) {
            if (Math.abs(throttle) < 0.2) {
                double alpha = 0.1;
                mQuickStopAccumulator = (1 - alpha) * mQuickStopAccumulator + alpha * Util.limit(wheel, 1.0) * 2;
            }
            overPower = 1.0;
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * wheel * DriveConstants.kTurnSensitivity - mQuickStopAccumulator;
            if (mQuickStopAccumulator > 1) {
                mQuickStopAccumulator -= 1;
            } else if (mQuickStopAccumulator < -1) {
                mQuickStopAccumulator += 1;
            } else {
                mQuickStopAccumulator = 0.0;
            }
        }

        double rightPwm = throttle - angularPower;
        double leftPwm = throttle + angularPower;
        if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }

        mSignal.rightMotor = rightPwm;
        mSignal.leftMotor = leftPwm;
        return mSignal;
    }

    public double handleDeadband(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}
