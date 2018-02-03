package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.PickupConstants;

public class Pickup {

    private static Pickup instance = new Pickup();
    public static Pickup getInstance() {return instance;}

    private Solenoid inOutSolonoid, upDownSolonoid;
    private TalonSRX leftMotor, rightMotor;

    private Pickup() {
        inOutSolonoid = new Solenoid(PickupConstants.inOutSolenoid);
        upDownSolonoid = new Solenoid(PickupConstants.upDownSolenoid);
        leftMotor = new TalonSRX(PickupConstants.pickupLeftMotorId);
        rightMotor = new TalonSRX(PickupConstants.pickupRightMotorId);
        rightMotor.setInverted(true);
    }

    private void setMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
        rightMotor.set(ControlMode.PercentOutput, speedPercent);
    }

    public void open() {
        inOutSolonoid.set(true);
    }

    public void close(){
        inOutSolonoid.set(false);
    }

    public void intake(){
        setMotorSpeed(0.5);
    }

    public void outtake() {
        setMotorSpeed(0.5);
    }

    public void stopped() {
        setMotorSpeed(0);
    }

    public void up() {
        upDownSolonoid.set(true);
    }

    public void down() {
        upDownSolonoid.set(false);
    }
}
