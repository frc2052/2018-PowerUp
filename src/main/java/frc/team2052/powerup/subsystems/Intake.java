package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

public class Intake implements Loopable {//implements Loopable

    //SINGLETON
    private static Intake instance = null;
    public static Intake getInstance()
    {
        if (instance == null)
        {
            try {
                instance = new Intake();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create Intake: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private intakeState currentState = intakeState.OPEN_OFF;
    private Solenoid solenoid1In, solenoid1Out;
    private Solenoid solenoid2In, solenoid2Out;
    private Solenoid solenoidLift1In, solenoidLift1Out;
    private Solenoid solenoidLift2In, solenoidLift2Out;

    private TalonSRX leftMotor, rightMotor;

    private Intake() {
        solenoid1In = new Solenoid(Constants.intakeSolenoid);
        solenoidLift1In = new Solenoid(Constants.intakeSolenoidLiftIn1);
        solenoidLift1Out = new Solenoid(Constants.intakeSolenoidLiftOut1);
        solenoidLift2In = new Solenoid(Constants.intakeSolenoidLiftIn2);
        solenoidLift2Out = new Solenoid(Constants.intakeSolenoidLiftOut2);

        leftMotor = new TalonSRX(Constants.intakeLeftMotorId);
        rightMotor = new TalonSRX(Constants.intakeRightMotorId);
        rightMotor.setInverted(true);
    }

    private void setOpen(boolean open) {
        solenoid1In.set(open);
        solenoid1Out.set(!open);
        solenoid2In.set(open);
        solenoid2Out.set(!open);
    }
    public void setIntakeup(boolean up) { //true sets it up, false sets it down
        solenoidLift1In.set(up);
        solenoidLift1Out.set(!up);
        solenoidLift2In.set(up);
        solenoidLift2Out.set(!up);
    }
    private void setMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
        rightMotor.set(ControlMode.PercentOutput, speedPercent);
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
                if (wantClosed) { //Goes from open to closed
                    setOpen(false);
                    setMotorSpeed(Constants.intakeStopSpeed);
                    state = intakeState.CLOSED;
                    break;
                }

                else if (wantOpenIntake) { //Stays open, motors start spinning to pick up cube
                    setOpen(true);
                    setMotorSpeed(Constants.intakeInSpeed);
                    state = intakeState.OPEN_INTAKE;
                }

                else if (wantOpenOutake) { //Stays open, motors start spinning to eject cube
                    setOpen(true);
                    setMotorSpeed(Constants.intakeOutSpeed);
                    state = intakeState.OPEN_OUTAKE;
                }
               break;
            case CLOSED:
                if (wantOpenOff) { //Goes from closed to open, motors stay off
                    setOpen(true);
                    setMotorSpeed(Constants.intakeOutSpeed);
                    state = intakeState.OPEN_OFF;
                }
                else if (wantOpenIntake) { //Goes from closed to open, motors spin to pick up cube
                    setOpen(true);
                    setMotorSpeed(Constants.intakeInSpeed);
                    state = intakeState.OPEN_INTAKE;
                }
                else if (wantOpenOutake) { //Goes from closed to open, motors spin to eject cube
                    setOpen(true);
                    setMotorSpeed(Constants.intakeOutSpeed);
                    state = intakeState.OPEN_OUTAKE;
                }
                break;
            case OPEN_INTAKE: //Can't go straight from open intake to open outtake, would put too much stress on gearbox
                if (wantOpenOff) { //Motors completely stop, stays open
                    setOpen(true);
                    setMotorSpeed(Constants.intakeStopSpeed);
                    state = intakeState.OPEN_OFF;
                }
                else if (wantClosed) { //Motor completely stops, goes from opened to closed
                    setOpen(false);
                    setMotorSpeed(Constants.intakeStopSpeed);
                    state = intakeState.CLOSED;
                }
                break;
            case OPEN_OUTAKE:
                if (wantOpenOff) { //Motor completely stops, stays open
                    setOpen(true);
                    setMotorSpeed(Constants.intakeStopSpeed);
                }

                else if (wantClosed) { //Motor completely stops, goes from opened to closed
                    setOpen(false);
                    setMotorSpeed(Constants.intakeStopSpeed);
                    state = intakeState.CLOSED;
                }
        }
        currentState = state; //linking a variable to the state machine
    }

    public enum intakeState {
        OPEN_OFF,
        OPEN_INTAKE,
        OPEN_OUTAKE,
        CLOSED
    }
    //todo revisit flags as ther is no reason to ask for a boolean and set the other flags to false
    private boolean wantOpenIntake=false;
    private boolean wantOpenOutake=false;
    private boolean wantOpenOff=false;
    private boolean wantClosed=false;

    public boolean getWantOpenIntake() { //getting current state to be able to open intake
        if (currentState == intakeState.OPEN_INTAKE){
            return true;
        } else {
            return false;
        }
    }
    public void setWantOpenIntake() { //setting current state to open intake
        wantOpenIntake=true;
        wantOpenOutake=false;
        wantOpenOff=false;
        wantClosed=false;
    }


    public boolean getWantOpenOutake() { //getting current state to be able to open outtake
        if (currentState == intakeState.OPEN_OUTAKE) {
            return true;
        } else {
            return false;
        }
    }
    public void setWantOpenOutake() { //setting current state to open outtake
        wantOpenIntake=false;
        wantOpenOutake=true;
        wantOpenOff=false;
        wantClosed=false;
    }


    public boolean getWantOpenOff() {
        if (currentState == intakeState.OPEN_OFF) { //getting current state to be able to open and turn wheels off
            return true;
        } else {
            return false;
        }
    }
    public void setWantOpenOff() { //setting current state to open and wheels off
        wantOpenIntake=false;
        wantOpenOutake=false;
        wantOpenOff=true;
        wantClosed=false;
    }


    public boolean getWantClosed() {
        if (currentState == intakeState.CLOSED) { //getting current state to be able to close
            return true;
        } else {
            return false;
        }
    }
    public void setWantClosed(){ //setting current state to closed
        wantOpenIntake=false;
        wantOpenOutake=false;
        wantOpenOff=false;
        wantClosed=true;
    }
}
