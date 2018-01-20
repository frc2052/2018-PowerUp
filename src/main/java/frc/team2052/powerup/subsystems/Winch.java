package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team2052.powerup.Constants;

public class Winch {

    private static Winch instance = new Winch();
    private TalonSRX climbing_motor = new TalonSRX(Constants.climbingMotorId);

    public static Winch getInstance() {
        return instance;
    }

    /*
     * climb method. sets motor output to climbSpeed (set in Constants).
     */
    public void climb() {
        climbing_motor.set(ControlMode.PercentOutput, Constants.climbSpeed);
    }

    /*
     * stop method. sets motor output to 0.
     */
    public void stop() {
        climbing_motor.set(ControlMode.PercentOutput, 0);
    }

    /*
     * checks if motor is spinning.
     */
    public boolean isClimbing() {
        return (climbing_motor.getMotorOutputPercent() != 0);
    }

}
