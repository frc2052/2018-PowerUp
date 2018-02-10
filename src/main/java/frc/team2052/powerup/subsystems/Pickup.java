package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

public class Pickup {

    private static Pickup instance = new Pickup();
    public static Pickup getInstance() {return instance;}

    //setting solenoids and talons
    private Solenoid upDownSolenoidLIn, upDownSolenoidLOut;
    private Solenoid upDownSolenoidSIn, upDownSolenoidSOut;
    private TalonSRX leftMotor, rightMotor;

    private Pickup() { //getting solenoids and talons from constants and setting the right motor to be inverted
        upDownSolenoidLIn = new Solenoid(Constants.upDownLSolenoidIn);
        upDownSolenoidLOut = new Solenoid(Constants.upDownSolenoid1Out);
        upDownSolenoidSIn = new Solenoid(Constants.upDownSolenoid2In);
        upDownSolenoidSOut = new Solenoid(Constants.upDownSolenoid2Out);
        leftMotor = new TalonSRX(Constants.pickupLeftMotorId);
        rightMotor = new TalonSRX(Constants.pickupRightMotorId);
        rightMotor.setInverted(true);
    }

    private void setRightMotorSpeed(double speedPercent) { //setting the speed in which the motors spin at
        rightMotor.set(ControlMode.PercentOutput, speedPercent);
    }

    private void setLeftMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
    }

    public void intake(){
        setRightMotorSpeed(Constants.intakeInSpeedRight);
        setLeftMotorSpeed(Constants.intakeInSpeedLeft);
    } //activating intake and setting speed

    public void outtake() {
        setRightMotorSpeed(Constants.intakeOutSpeed);
        setLeftMotorSpeed(Constants.intakeOutSpeed);
    } //activating outtake and setting speed

    public void stopped() {
        setRightMotorSpeed(Constants.intakeStopSpeed);
        setLeftMotorSpeed(Constants.intakeStopSpeed);
    } //stop wheels

    public void pickupPositionDown() { //Flat
        upDownSolenoidLIn.set(true);
        upDownSolenoidLOut.set(false);
        upDownSolenoidSIn.set(true);
        upDownSolenoidSOut.set(false);
    }

    public void pickupPositionRaised() { //Angled
        upDownSolenoidLIn.set(true);
        upDownSolenoidLOut.set(false);
        upDownSolenoidSIn.set(false);
        upDownSolenoidSOut.set(true);
    }

    public void Init() { //All the way up
        upDownSolenoidLIn.set(false);
        upDownSolenoidLOut.set(true);
        upDownSolenoidSIn.set(false);
        upDownSolenoidSOut.set(true);
    }
}
