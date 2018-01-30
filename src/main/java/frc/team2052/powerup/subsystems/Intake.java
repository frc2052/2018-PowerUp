package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.IntakeConstants;

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

    private IntakeState currentState = IntakeState.OPEN_OFF;
    private Solenoid solenoid1In, solenoid1Out;
    private Solenoid solenoid2In, solenoid2Out;
    private Solenoid solenoidLift1In, solenoidLift1Out;
    private Solenoid solenoidLift2In, solenoidLift2Out;

    private TalonSRX leftMotor, rightMotor;

    private Intake() {
        solenoid1In = new Solenoid(IntakeConstants.intakeSolenoidIn1);
        solenoid1Out = new Solenoid(IntakeConstants.intakeSolenoidOut1);
        solenoid2In = new Solenoid(IntakeConstants.intakeSolenoidIn2);
        solenoid2Out = new Solenoid(IntakeConstants.intakeSolenoidOut2);
        solenoidLift1In = new Solenoid(IntakeConstants.intakeSolenoidLiftIn1);
        solenoidLift1Out = new Solenoid(IntakeConstants.intakeSolenoidLiftOut1);
        solenoidLift2In = new Solenoid(IntakeConstants.intakeSolenoidLiftIn2);
        solenoidLift2Out = new Solenoid(IntakeConstants.intakeSolenoidLiftOut2);

        leftMotor = new TalonSRX(IntakeConstants.intakeLeftMotorId);
        rightMotor = new TalonSRX(IntakeConstants.intakeRightMotorId);
        rightMotor.setInverted(true);
    }

    private void setOpen(boolean open) {
        solenoid1In.set(open);
        solenoid1Out.set(!open);
        solenoid2In.set(open);
        solenoid2Out.set(!open);
    }
    public void setIntakeup(boolean up) {
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
    }

    @Override
    public void onStop() {
    }

    public void update () {
        IntakeState state = currentState;
        switch (state) {
            case OPEN_OFF:
                if (wantClosed) { //Goes from open to closed
                    setOpen(false);
                    setMotorSpeed(IntakeConstants.intakeStopSpeed);
                    state = IntakeState.CLOSED;
                    break;
                }
                else if (wantOpenIntake) { //Stays open, motors start spinning to pick up cube
                    setOpen(true);
                    setMotorSpeed(IntakeConstants.intakeInSpeed);
                    state = IntakeState.OPEN_INTAKE;
                }
                else if (wantOpenOutake) { //Stays open, motors start spinning to ejecct cube
                    setOpen(true);
                    setMotorSpeed(IntakeConstants.intakeOutSpeed);
                    state = IntakeState.OPEN_OUTAKE;
                }
                break;
            case CLOSED:
                if (wantOpenOff) { //Goes from closed to open, motors stay off
                    setOpen(true);
                    setMotorSpeed(IntakeConstants.intakeStopSpeed);
                    state = IntakeState.OPEN_OFF;
                }
                else if (wantOpenIntake) { //Goes from closed to open, motors spin to pick up cube
                    setOpen(true);
                    setMotorSpeed(IntakeConstants.intakeInSpeed);
                    state = IntakeState.OPEN_INTAKE;
                }
                else if (wantOpenOutake) { //Goes from closed to open, motors spin to eject cube
                    setOpen(true);
                    setMotorSpeed(IntakeConstants.intakeOutSpeed);
                    state = IntakeState.OPEN_OUTAKE;
                }
                break;
            case OPEN_INTAKE: //Can't go straight from open intake to open outtake, would put too much stress on gearbox
                if (wantOpenOff) { //Motors completely stop, stays open
                    setOpen(true);
                    setMotorSpeed(IntakeConstants.intakeStopSpeed);
                    state = IntakeState.OPEN_OFF;
                }
                else if (wantClosed) { //Motor completely stops, goes from opened to closed
                    setOpen(false);
                    setMotorSpeed(IntakeConstants.intakeStopSpeed);
                    state = IntakeState.CLOSED;
                }
                break;
            case OPEN_OUTAKE:
                if (wantOpenOff) { //Motor completely stops, stays open
                    setOpen(true);
                    setMotorSpeed(IntakeConstants.intakeStopSpeed);
                    state = IntakeState.OPEN_OFF;
                }
                else if (wantClosed) { //Motor completely stops, goes from opened to closed
                    setOpen(false);
                    setMotorSpeed(IntakeConstants.intakeStopSpeed);
                    state = IntakeState.CLOSED;
                }
        }
        currentState = state;
    }

    public enum IntakeState {
        OPEN_OFF,
        OPEN_INTAKE,
        OPEN_OUTAKE,
        CLOSED
    }

    private boolean wantOpenIntake=false;
    private boolean wantOpenOutake=false;
    private boolean wantOpenOff=false;
    private boolean wantClosed=false;

    public boolean getWantOpenIntake() {
        return (currentState == IntakeState.OPEN_INTAKE);
    }
    public void setWantOpenIntake() {
        wantOpenIntake=true;
        wantOpenOutake=false;
        wantOpenOff=false;
        wantClosed=false;
    }


    public boolean getWantOpenOutake() {
        return (currentState == IntakeState.OPEN_OUTAKE);
    }
    public void setWantOpenOutake() {
        wantOpenIntake=false;
        wantOpenOutake=true;
        wantOpenOff=false;
        wantClosed=false;
    }


    public boolean getWantOpenOff() {
        return (currentState == IntakeState.OPEN_OFF);
    }
    public void setWantOpenOff() {
        wantOpenIntake=false;
        wantOpenOutake=false;
        wantOpenOff=true;
        wantClosed=false;
    }

    public boolean getWantClosed() {
        return (currentState == IntakeState.CLOSED);
    }
    public void setWantClosed(){
        wantOpenIntake=false;
        wantOpenOutake=false;
        wantOpenOff=false;
        wantClosed=true;
    }
}
