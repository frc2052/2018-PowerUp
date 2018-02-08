package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.PickupConstants;

public class Pickup {

    private static Pickup instance = new Pickup();
    public static Pickup getInstance() {return instance;}

    //setting solenoids and talons
    private Solenoid inOutSolonoid, upDownSolonoid;
    private Solenoid upDownSolonoidLIn, upDownSolonoidLOut;
    private Solenoid upDownSolonoidSIn, upDownSolonoidSOut;
    private TalonSRX leftMotor, rightMotor;

    private Pickup() { //getting solenoids and talons from constants and setting the right motor to be inverted
        inOutSolonoid = new Solenoid(PickupConstants.inOutSolenoid);
        upDownSolonoidLIn = new Solenoid(PickupConstants.upDownLSolenoidIn);
        upDownSolonoidLOut = new Solenoid(PickupConstants.upDownSolenoid1Out);
        upDownSolonoidSIn = new Solenoid(PickupConstants.upDownSolenoid2In);
        upDownSolonoidSOut = new Solenoid(PickupConstants.upDownSolenoid2Out);
        leftMotor = new TalonSRX(PickupConstants.pickupLeftMotorId);
        rightMotor = new TalonSRX(PickupConstants.pickupRightMotorId);
        rightMotor.setInverted(true);
    }

    private void setMotorSpeed(double speedPercent) { //setting the speed in which the motors spin at
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
        rightMotor.set(ControlMode.PercentOutput, speedPercent);
    }

    public void open() {
        inOutSolonoid.set(true);
    } //opening pickup

    public void close(){
        inOutSolonoid.set(false);
    } //closing pickup

    public void intake(){
        setMotorSpeed(0.6);
    } //activating intake and setting speed

    public void outtake() {
        setMotorSpeed(-0.4);
    } //activating outtake and setting speed

    public void stopped() {
        setMotorSpeed(0);
    } //stop wheels

    public void position1() { //pickup rotates all the way up
        upDownSolonoidLIn.set(true);
        upDownSolonoidLOut.set(false);
        upDownSolonoidSIn.set(true);
        upDownSolonoidSOut.set(false);
    }

    public void position2() { //pickup rotates at an angle to stack
        upDownSolonoidLIn.set(true);
        upDownSolonoidLOut.set(false);
        upDownSolonoidSIn.set(false);
        upDownSolonoidSOut.set(true);
    }

    public void position3() { //pickup goes flat to intake
        upDownSolonoidLIn.set(false);
        upDownSolonoidLOut.set(true);
        upDownSolonoidSIn.set(false);
        upDownSolonoidSOut.set(true);
    }
}
