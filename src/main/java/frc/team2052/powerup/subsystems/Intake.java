package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.IntakeConstants;

public class Intake implements Loopable {//implements Loopable

    //SINGLETON
    private static Intake instance = new Intake();
    public static Intake getInstance() { return instance;}

    private intakeState currentState = intakeState.OPEN_OFF;
    private Solenoid solenoid1In;
    private TalonSRX leftMotor, rightMotor;

    private Intake() {
        solenoid1In = new Solenoid(IntakeConstants.intakeSolenoid);
        leftMotor = new TalonSRX(IntakeConstants.intakeLeftMotorId);
        rightMotor = new TalonSRX(IntakeConstants.intakeRightMotorId);
        rightMotor.setInverted(true);
    }

    private void setOpen(boolean open) {
        solenoid1In.set(open);
        }

    private void setMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
        rightMotor.set(ControlMode.PercentOutput, speedPercent); //TODO: figure out how to set the motor to be reversed in config
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
            case OPEN_OFF:
                if (wantClosedOff) { //Goes from open to closed
                    setOpen(false);
                    setMotorSpeed(0);
                    state = intakeState.CLOSED_OFF;
                } else if (wantOpenIntake) { //Stays open, motors start spinning to pick up cube
                    setOpen(true);
                    setMotorSpeed(1);
                    state = intakeState.OPEN_INTAKE;
                } else if (wantOutake) { //Stays open, motors start spinning to ejecct cube
                    setOpen(false);
                    setMotorSpeed(2);
                    state = intakeState.OUTTAKE;
                } else if (wantClosedIntake) {
                    setOpen(false);
                    setMotorSpeed(1);
                    state = intakeState.CLOSED_INTAKE;
                } break;
            case CLOSED_OFF:
                if (wantOpenOff) { //Goes from closed to open, motors stay off
                    setOpen(true);
                    setMotorSpeed(0);
                    state = intakeState.OPEN_OFF;
                } else if (wantOpenIntake) { //Goes from closed to open, motors spin to pick up cube
                    setOpen(true);
                    setMotorSpeed(1);
                    state = intakeState.OPEN_INTAKE;
                } else if (wantClosedIntake) {
                    setOpen(false);
                    setMotorSpeed(1);
                    state = intakeState.CLOSED_INTAKE;
                } else if (wantOutake) { //Goes from closed to open, motors spin to eject cube
                    setOpen(false);
                    setMotorSpeed(2); //TODO: Figure out speeds for intake and outtake
                    state = intakeState.OUTTAKE;
                } break;
            case OPEN_INTAKE: //Can't go straight from open intake to open outtake, would put too much stress on gearbox
                if (wantOpenOff) { //Motors completely stop, stays open
                    setOpen(true);
                    setMotorSpeed(0);
                    state = intakeState.OPEN_OFF;
                } else if (wantClosedOff) { //Motor completely stops, goes from opened to closed
                    setOpen(false);
                    setMotorSpeed(0);
                    state = intakeState.CLOSED_OFF;
                } else if (wantClosedIntake) {
                    setOpen(false);
                    setMotorSpeed(1);
                    state = intakeState.CLOSED_INTAKE;
                } break;
            case OUTTAKE:
                if (wantOpenOff) { //Motor completely stops, stays open
                    setOpen(true);
                    setMotorSpeed(0);
                } else if (wantClosedOff) { //Motor completely stops, goes from opened to closed
                    setOpen(false);
                    setMotorSpeed(0);
                    state = intakeState.CLOSED_OFF; }
        }
        currentState = state;
    }


    //todo revisit flags as ther is no reason to ask for a boolean and set the other flags to false
    private boolean wantOpenIntake=false;
    private boolean wantOutake =false;
    private boolean wantOpenOff=false;
    private boolean wantClosedOff =false;
    private boolean wantClosedIntake=false;

    public boolean getWantOpenIntake() {
        if (currentState == intakeState.OPEN_INTAKE){
            return true;
        } else {
            return false;
        }
    }

    public void setWantOpenIntake() {
        wantOpenIntake=true;
        wantOutake =false;
        wantOpenOff=false;
        wantClosedOff =false;
    }


    public boolean getWantOutake() {
        if (currentState == intakeState.OUTTAKE) {
            return true;
        } else {
            return false;
        }
    }

    public void setWantOutake() {
        wantOpenIntake=false;
        wantOutake =true;
        wantOpenOff=false;
        wantClosedOff =false;
    }

    public boolean getWantOpenOff() {
        if (currentState == intakeState.OPEN_OFF) {
            return true;
        } else {
            return false;
        }
    }

    public void setWantOpenOff() {
        wantOpenIntake=false;
        wantOutake =false;
        wantOpenOff=true;
        wantClosedOff =false;
    }

    public boolean getWantClosedOff() {
        if (currentState == intakeState.CLOSED_OFF) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantClosedOff(){
        wantOpenIntake=false;
        wantOutake =false;
        wantOpenOff=false;
        wantClosedOff =true;
    }

    public boolean getWantClosedIntake() {
        if (currentState == intakeState.CLOSED_INTAKE) {
            return true;
        } else {
            return false;
        }
    }

    public void setWantClosedOuttake(){
        wantOpenIntake=false;
        wantOutake =false;
        wantOpenOff=false;
        wantClosedOff =false;
        wantClosedIntake=true;
    }

    public enum intakeState {
        OPEN_OFF,
        OPEN_INTAKE,
        CLOSED_OFF,
        CLOSED_INTAKE,
        OUTTAKE
    }
}
