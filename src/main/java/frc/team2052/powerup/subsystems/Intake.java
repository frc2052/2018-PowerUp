package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

/**
 * Created by Kay on 1/19/2018.
 */
public class Intake implements Loopable {//implements Loopable

    //SINGLETON
    private static Intake instance = new Intake();
    public static Intake getInstance() { return instance; }

    private intakeState currentState = intakeState.OPEN_OFF;


    private Intake() {
        solenoid1In = new Solenoid(Constants.intakeSolenoidIn1);
        solenoid1Out = new Solenoid(Constants.intakeSolenoidOut1);
        solenoid2In = new Solenoid(Constants.intakeSolenoidIn2);
        solenoid2Out = new Solenoid(Constants.intakeSolenoidOut2);
        leftMotor = new TalonSRX(Constants.intakeLeftMotorId);
        rightMotor = new TalonSRX(Constants.intakeRightMotorId);
    }


    private Solenoid solenoid1In, solenoid1Out;
    private Solenoid solenoid2In, solenoid2Out;
    private TalonSRX leftMotor, rightMotor;


    private void setOpen(boolean open) {
        solenoid1In.set(open);
        solenoid1Out.set(!open);
        solenoid2In.set(open);
        solenoid2Out.set(!open);
    }

    private void setMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
        rightMotor.set(ControlMode.PercentOutput, -speedPercent); //TODO: figure out how to set the motor to be reversed in config
    }

    @Override
    public void onStart() {
//        setWantOpen(false);
    }

    @Override
    public void onStop() {
    }

    public void update () {
       intakeState state = currentState;
       switch (currentState) {
           case OPEN_OFF:
               if (wantClosed) { //Goes from open to closed
                   setOpen(false);
                   setMotorSpeed(0);
                   state = intakeState.CLOSED;
               }

                else if (wantOpenIntake) { //Stays open, motors start spinning to pick up cube
                   setOpen(true);
                   setMotorSpeed(1);
                   state = intakeState.OPEN_INTAKE;
               }

               else if (wantOpenOutake) { //Stays open, motors start spinning to ejecct cube
                   setOpen(true);
                   setMotorSpeed(1);
                   state = intakeState.OPEN_OUTAKE;
               }
               break;

           case CLOSED:
               if (wantOpenOff) { //Goes from closed to open, motors stay off
                   setOpen(true);
                   setMotorSpeed(0);
                   state = intakeState.OPEN_OFF;
               }
               else if (wantOpenIntake) { //Goes from closed to open, motors spin to pick up cube
                   setOpen(true);
                   setMotorSpeed(1);
                   state = intakeState.OPEN_INTAKE;
               }
               else if (wantOpenOutake) { //Goes from closed to open, motors spin to eject cube
                   setOpen(true);
                   setMotorSpeed(1); //TODO: Figure out speeds for intake and outtake
                   state = intakeState.OPEN_OUTAKE;
               }
               break;

           case OPEN_INTAKE: //Can't go straight from open intake to open outtake, would put too much stress on gearbox
               if (wantOpenOff) { //Motors completely stop, stays open
                   setOpen(true);
                   setMotorSpeed(0);
                   state = intakeState.OPEN_OFF;
               }
               else if (wantClosed) { //Motor completely stops, goes from opened to closed
                   setOpen(false);
                   setMotorSpeed(0);
                   state = intakeState.CLOSED;
               }
               break;

           case OPEN_OUTAKE:
               if (wantOpenOff) { //Motor completely stops, stays open
                   setOpen(true);
                   setMotorSpeed(0);
               }

               else if (wantClosed) { //Motor completely stops, goes from opened to closed
                   setOpen(false);
                   setMotorSpeed(0);
                   state = intakeState.CLOSED;
               }
       }
}

    public enum intakeState {
        OPEN_OFF,
        OPEN_INTAKE,
        OPEN_OUTAKE,
        CLOSED
    }

    private boolean wantOpenIntake=false;
    public boolean getWantOpenIntake() {
        return wantOpenIntake;
    }
    public void setWantOpenIntake(boolean flag) {
        wantOpenIntake = flag;
    }

    private boolean wantOpenOutake=false;
    public boolean getWantOpenOutake() {
        return wantOpenOutake;
    }
    public void setWantOpenOutake(boolean flag) {
        wantOpenOutake = flag;
    }

    private boolean wantOpenOff=false;
    public boolean getWantOpenOff() {
        return wantOpenOff;
    }
    public void setWantOpenOff(boolean flag) {
        wantOpenOff = flag;
    }

    private boolean wantClosed=false;
    public boolean getWantClosed() {
        return wantClosed;
    }
    public void setWantClosed(boolean flag) {
        wantClosed = flag;
    }
}
