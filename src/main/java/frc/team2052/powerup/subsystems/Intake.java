package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.IntakeConstants;

/**
 * Created by Kay on 1/19/2018.
 */
public class Intake implements Loopable {//implements Loopable

    //SINGLETON
    private static Intake instance = new Intake();
    public static Intake getInstance() { return instance; }

    private intakeState currentState = intakeState.DOWN_OPEN_OFF;
    private Solenoid solenoid1In, solenoid1Out;
    private Solenoid solenoid2In, solenoid2Out;
    private Solenoid solenoidLift1In, solenoidLift1Out;
    private Solenoid solenoidLift2In, solenoidLift2Out;
    private TalonSRX leftMotor, rightMotor;


    private Intake() {//todo find better names
        solenoid1In = new Solenoid(IntakeConstants.intakeSolenoidIn1);
        solenoid1Out = new Solenoid(IntakeConstants.intakeSolenoidOut1);
        solenoid2In = new Solenoid(IntakeConstants.intakeSolenoidIn2);
        solenoid2Out = new Solenoid(IntakeConstants.intakeSolenoidOut2);

        solenoidLift1In = new Solenoid(IntakeConstants.intakeSolenoidLift1In);
        solenoidLift1Out = new Solenoid(IntakeConstants.intakeSolenoidLift1Out);
        solenoidLift2In = new Solenoid(IntakeConstants.intakeSolenoidLift2In);
        solenoidLift2Out = new Solenoid(IntakeConstants.intakeSolenoidLift2Out);

        leftMotor = new TalonSRX(IntakeConstants.intakeLeftMotorId);
        rightMotor = new TalonSRX(IntakeConstants.intakeRightMotorId);
    }

    private void setOpen(boolean open) {
        solenoid1In.set(open);
        solenoid1Out.set(!open);
        solenoid2In.set(open);
        solenoid2Out.set(!open);
        solenoid1In.set(open);
        solenoid1Out.set(!open);
        solenoid2In.set(open);
        solenoid2Out.set(!open);solenoid1In.set(open);
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
        switch (state) {
            case DOWN_OPEN_OFF:
                if (wantClosed) { //Goes from open to closed
                    setOpen(false);
                    setMotorSpeed(0);
                    state = intakeState.CLOSED;
                    break;
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
        currentState = state;
    }

    public enum intakeState {
        DOWN_OPEN_OFF,
        DOWN_OPEN_INTAKE,
        DOWN_OPEN_OUTAKE,
        DOWN_CLOSED,
        UP_OPEN_OFF,
        UP_OPEN_INTAKE,
        UP_OPEN_OUTAKE,
        UP_CLOSED,




    }
    //todo revisit flags as ther is no reason to ask for a boolean and set the other flags to false
    private boolean wantDownOpenIntake=false;
    private boolean wantDownOpenOutake=false;
    private boolean wantDownOpenOff=false;
    private boolean wantDownClosed=false;
    private boolean wantUpOpenIntake=false;
    private boolean wantUpOpenOutake=false;
    private boolean wantUpOpenOff=false;
    private boolean wantUpClosed=false;

    public boolean getWantDownOpenIntake() {
        if (currentState == intakeState.DOWN_OPEN_INTAKE){
            return true;
        } else {
            return false;
        }
    }
    public void setWantDownOpenIntake() {
        wantDownOpenIntake=true;
        wantDownOpenOutake=false;
        wantDownOpenOff=false;
        wantDownClosed=false;
        wantUpOpenIntake=false;
        wantUpOpenOutake=false;
        wantUpOpenOff=false;
        wantUpClosed=false;
    }


    public boolean getWantDownOpenOutake() {
        if (currentState == intakeState.DOWN_OPEN_OUTAKE) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantDownOpenOutake() {
        wantDownOpenIntake=false;
        wantDownOpenOutake=true;
        wantDownOpenOff=false;
        wantDownClosed=false;
        wantUpOpenIntake=false;
        wantUpOpenOutake=false;
        wantUpOpenOff=false;
        wantUpClosed=false;
    }


    public boolean getWantDownOpenOff() {
        if (currentState == intakeState.DOWN_OPEN_OFF) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantDownOpenOff() {
        wantDownOpenIntake=false;
        wantDownOpenOutake=false;
        wantDownOpenOff=true;
        wantDownClosed=false;
        wantUpOpenIntake=false;
        wantUpOpenOutake=false;
        wantUpOpenOff=false;
        wantUpClosed=false;
    }


    public boolean getWantDownClosed() {
        if (currentState == intakeState.DOWN_CLOSED) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantDownClosed(){
        wantDownOpenIntake=false;
        wantDownOpenOutake=false;
        wantDownOpenOff=false;
        wantDownClosed=true;
        wantUpOpenIntake=false;
        wantUpOpenOutake=false;
        wantUpOpenOff=false;
        wantUpClosed=false;
    }
    public boolean getWantUpOpenIntake() {
        if (currentState == intakeState.UP_OPEN_INTAKE){
            return true;
        } else {
            return false;
        }
    }
    public void setWantUpOpenIntake() {
        wantDownOpenIntake=false;
        wantDownOpenOutake=false;
        wantDownOpenOff=false;
        wantDownClosed=false;
        wantUpOpenIntake=true;
        wantUpOpenOutake=false;
        wantUpOpenOff=false;
        wantUpClosed=false;
    }


    public boolean getWantUpOpenOutake() {
        if (currentState == intakeState.UP_OPEN_OUTAKE) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantUpOpenOutake() {
        wantDownOpenIntake=false;
        wantDownOpenOutake=false;
        wantDownOpenOff=false;
        wantDownClosed=false;
        wantUpOpenIntake=false;
        wantUpOpenOutake=true;
        wantUpOpenOff=false;
        wantUpClosed=false;
    }


    public boolean getWantUpOpenOff() {
        if (currentState == intakeState.UP_OPEN_OFF) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantUpOpenOff() {
        wantDownOpenIntake=false;
        wantDownOpenOutake=false;
        wantDownOpenOff=false;
        wantDownClosed=false;
        wantUpOpenIntake=false;
        wantUpOpenOutake=false;
        wantUpOpenOff=true;
        wantUpClosed=false;
    }


    public boolean getWantUpClosed() {
        if (currentState == intakeState.UP_CLOSED) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantUpClosed(){
        wantDownOpenIntake=false;
        wantDownOpenOutake=false;
        wantDownOpenOff=false;
        wantDownClosed=false;
        wantUpOpenIntake=false;
        wantUpOpenOutake=false;
        wantUpOpenOff=false;
        wantUpClosed=true;
    }
}

