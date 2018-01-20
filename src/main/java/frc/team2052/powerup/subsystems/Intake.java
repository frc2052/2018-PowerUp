package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

/**
 * Created by Kay on 1/19/2018.
 */
public class Intake {//implements Loopable

    //SINGLETON
    private static Intake instance = new Intake();
    public static Intake getInstance() { return instance; }
    private Intake() {
        solenoid1Inid = new Solenoid(Constants.intakeSolenoidIn1);
        solenoid1Outid = new Solenoid(Constants.intakeSolenoidOut1);
        solenoid2Inid = new Solenoid(Constants.intakeSolenoidIn2);
        solenoid2Outid = new Solenoid(Constants.intakeSolenoidOut2);
        leftMotor = new TalonSRX(Constants.intakeLeftMotorId);
        rightMotor = new TalonSRX(Constants.intakeRightMotorId);
    }
    //SINGLETON

    private Solenoid solenoid1Inid, solenoid1Outid;
    private Solenoid solenoid2Inid, solenoid2Outid;
    private TalonSRX leftMotor, rightMotor;


    private void setOpenIntake(boolean open) {
        solenoid1Inid.set(open);
        solenoid1Outid.set(!open);
        solenoid2Inid.set(open);
        solenoid2Outid.set(!open);

    }
    private void setMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
        rightMotor.set(ControlMode.PercentOutput, -speedPercent); //TODO: figure out how to set the motor to be reversed in config
    }





}
