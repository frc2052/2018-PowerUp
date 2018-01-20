package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team2052.powerup.Constants;

public class Winch {

    private static Winch instance = new Winch();
    private TalonSRX climbing_motor = new TalonSRX(Constants.climbingMotorId);//Recognizing the motor and calling it climbing motor

    public static Winch getInstance() {
        return instance;
    } //When another class has winch.getinstance it returns an instance of this class.

    public void climb() {
        climbing_motor.set(ControlMode.PercentOutput, Constants.climbSpeed); //sets percent output to climbSpeed
    }

    public void stop() {
        climbing_motor.set(ControlMode.PercentOutput, 0); //sets percent output to 0, stopping the robot.
    }

    public boolean isClimbing() {
        return (climbing_motor.getMotorOutputPercent() != 0); //Is motor spinning? T/F
    }

}
