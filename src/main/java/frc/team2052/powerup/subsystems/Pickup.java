package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

public class Pickup {

    private static Pickup instance = new Pickup();
    public static Pickup getInstance() {return instance;}

    //setting solenoids and talons
    private Solenoid armLongSolenoidIn, armLongSolenoidOut;
    private Solenoid armShortSolenoidIn, armShortSolenoidOut;
    private TalonSRX leftMotor, rightMotor;

    private Pickup() { //getting solenoids and talons from constants and setting the right motor to be inverted
        armLongSolenoidIn = new Solenoid(Constants.armLongSolenoidIn);
        armLongSolenoidOut = new Solenoid(Constants.armLongSolenoidOut);
        armShortSolenoidIn = new Solenoid(Constants.armShortSolenoidIn);
        armShortSolenoidOut = new Solenoid(Constants.armShortSolenoidOut);
        leftMotor = new TalonSRX(Constants.pickupLeftMotorId);
        rightMotor = new TalonSRX(Constants.pickupRightMotorId);
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);
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
        armLongSolenoidIn.set(true);
        armLongSolenoidOut.set(false);
        armShortSolenoidIn.set(true);
        armShortSolenoidOut.set(false);
    }

    public void pickupPositionRaised() { //Angled
        armLongSolenoidIn.set(false);
        armLongSolenoidOut.set(true);
        armShortSolenoidIn.set(false);
        armShortSolenoidOut.set(false);
    }

    public void pickupPositionStartingConfig() { //All the way up
        armLongSolenoidIn.set(false);
        armLongSolenoidOut.set(true);
        armShortSolenoidIn.set(false);
        armShortSolenoidOut.set(true);
    }
}
