package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;

public class Pickup implements PickupSubsystem {

    private static Pickup instance = new Pickup();
    public static Pickup getInstance() {return instance;}

    //setting solenoids and talons
    private Solenoid armLongSolenoidIn, armLongSolenoidOut;
    private Solenoid armShortSolenoidIn, armShortSolenoidOut;
    private TalonSRX leftMotor, rightMotor;

    private boolean firstCheckComplete = false;
    private double startTime = 0;
    private double pickupTimeoutSeconds = 4;
    private double timeForAmps;
    private boolean firstTimeTouchedCube = false;

    public void ResetCubePickupTimeoutSeconds(double newTimeout) {
        pickupTimeoutSeconds = newTimeout;
        firstCheckComplete = false;
        firstTimeTouchedCube = false;
    }

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

    public void shoot() {
        setRightMotorSpeed(Constants.intakeShootSpeed);
        setLeftMotorSpeed(Constants.intakeShootSpeed);
    } //activating shoot and setting speed

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

    @Override
    public boolean isCubePickedUp() {
        if (!firstCheckComplete) {
            System.out.println("FAKE PICKUP: First check for cube!");
            firstCheckComplete = true;
            startTime = Timer.getFPGATimestamp();
        }
        //see if we have exceeded the timeout
        boolean timedOut = Timer.getFPGATimestamp() - pickupTimeoutSeconds > startTime;

        if(timedOut){
            System.out.println("I HAVE TIMED OUT");
        }
        try {
            boolean ampsExceeded= AmpGetter.getCurrentIntake1(0) >= 20 || AmpGetter.getCurrentIntake2(2) >= 20;
            if (ampsExceeded)
            {
                if (!firstTimeTouchedCube){
                    firstTimeTouchedCube = true;
                    timeForAmps = Timer.getFPGATimestamp();
                    System.out.println("I touched a cube!");
                }else{
                    if(Timer.getFPGATimestamp() - timeForAmps > 1) {
                        System.out.println("Pickup: I have the cube!");
                        return true;
                    }
                }
                return false;
            } else {
                firstTimeTouchedCube = false;
                return timedOut;
            }
        } catch (Exception e) {
            System.out.println("ERROR: Pickup Failed to check amps, Defaulting to timeout");
            return timedOut;
        }
    }
}
