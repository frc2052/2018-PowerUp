package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.PickupConstants;

public class Pickup {

    private static Pickup instance = new Pickup();
    public static Pickup getInstance() {return instance;}

    private Solenoid inOutSolonoid, upDownSolonoid;
    private Solenoid upDownSolonoidLIn, upDownSolonoidLOut;
    private Solenoid upDownSolonoidSIn, upDownSolonoidSOut;
    private TalonSRX leftMotor, rightMotor;

    private Pickup() {
        inOutSolonoid = new Solenoid(PickupConstants.inOutSolenoid);
        upDownSolonoidLIn = new Solenoid(PickupConstants.upDownLSolenoidIn);
        upDownSolonoidLOut = new Solenoid(PickupConstants.upDownSolenoid1Out);
        upDownSolonoidSIn = new Solenoid(PickupConstants.upDownSolenoid2In);
        upDownSolonoidSOut = new Solenoid(PickupConstants.upDownSolenoid2Out);
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
        setMotorSpeed(0.6);
    }

    public void outtake() {
        setMotorSpeed(-0.4);
    }

    public void stopped() {
        setMotorSpeed(0);
    }

    public void position1() {
        upDownSolonoidLIn.set(true);
        upDownSolonoidLOut.set(false);
        upDownSolonoidSIn.set(true);
        upDownSolonoidSOut.set(false);
    }

    public void position2() {
        upDownSolonoidLIn.set(true);
        upDownSolonoidLOut.set(false);
        upDownSolonoidSIn.set(false);
        upDownSolonoidSOut.set(true);
    }

    public void position3() {
        upDownSolonoidLIn.set(false);
        upDownSolonoidLOut.set(true);
        upDownSolonoidSIn.set(false);
        upDownSolonoidSOut.set(true);
    }
}
