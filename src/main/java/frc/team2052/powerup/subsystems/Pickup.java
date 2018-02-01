package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.PickupConstants;

public class Pickup {

    private static Pickup instance = new Pickup();
    public static Pickup getInstance() { return instance;}

    private Solenoid intakesolenoid;
    private TalonSRX leftMotor, rightMotor;

    private Pickup() {
        intakesolenoid = new Solenoid(PickupConstants.intakeSolenoid);
        leftMotor = new TalonSRX(PickupConstants.intakeLeftMotorId);
        rightMotor = new TalonSRX(PickupConstants.intakeRightMotorId);
        rightMotor.setInverted(true);
    }

    private void setMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
        rightMotor.set(ControlMode.PercentOutput, speedPercent); //TODO: figure out how to set the motor to be reversed in config
    }

    public void pickupOpen(boolean open) {
        intakesolenoid.set(open);
    }

    public void pickupClosed(boolean close){
        intakesolenoid.set(close);
    }

    public void pickupIntake(){
        setMotorSpeed(1);
    }

    public void pickupOuttake() {
        setMotorSpeed(2);
    }

    public void pickupStopped() {
        setMotorSpeed(0);
    }

    public void pickupUp() {

    }

    public void pickupDown() {

    }


}
