package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
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
    private Solenoid openIntakeSolenoid;
    private TalonSRX leftMotor, rightMotor;
//for amperage checker
    private boolean firstCheckComplete = false;
    private double startTime = 0;
    private double pickupTimeoutSeconds = 4;
    private double startAmpTime;
    private boolean firstTimeTouchedCube = false;
//for color sensor
    private static DigitalInput colorSensor;

    private boolean raisedPickup = false;

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
        openIntakeSolenoid = new Solenoid(Constants.kRightRampOutId); //todo: test solenoid delete
        leftMotor = new TalonSRX(Constants.pickupLeftMotorId);
        rightMotor = new TalonSRX(Constants.pickupRightMotorId);
        leftMotor.setInverted(false);
        rightMotor.setInverted(false);

        if (colorSensor == null) {
            try {
                colorSensor = new DigitalInput(Constants.kColorSensorId);
                System.out.println("Created color sensor");
            }catch (Exception exe){
                System.out.println("Failed to create the color sensor on channel " + Constants.kColorSensorId);
            }
        }
    }

    private void setRightMotorSpeed(double speedPercent) { //setting the speed in which the motors spin at
        rightMotor.set(ControlMode.PercentOutput, speedPercent);
    }

    private void setLeftMotorSpeed(double speedPercent) {
        leftMotor.set(ControlMode.PercentOutput, speedPercent);
    }

    public void intake(){
        if(AmpGetter.getCurrentIntake1(0) >= 30 || AmpGetter.getCurrentIntake2(2) >= 30){ //todo CHANNALS 1 AND 14 on practice bot
            if(Timer.getFPGATimestamp() - startAmpTime > .5){
                setRightMotorSpeed(Constants.intakeInSpeedOverride);
                setLeftMotorSpeed(Constants.intakeInSpeedOverride);
            }
        }else{
            setRightMotorSpeed(Constants.intakeInSpeedRight);
            setLeftMotorSpeed(Constants.intakeInSpeedLeft);
        }
    } //activating intake and setting speed

    public void resetAmpTimer(){
        startAmpTime = 0;
    }

    public void outtake() {
        setRightMotorSpeed(Constants.intakeOutSpeed);
        setLeftMotorSpeed(Constants.intakeOutSpeed);
    } //activating outtake and setting speed

    public void autoOuttake() {
        setRightMotorSpeed(Constants.intakeFastOutSpeed);
        setLeftMotorSpeed(Constants.intakeFastOutSpeed);
    } //activating fast outtake and setting speed

    public void shoot() {
        setRightMotorSpeed(Constants.intakeShootSpeed);
        setLeftMotorSpeed(Constants.intakeShootSpeed);
    } //activating shoot and setting speed

    public void openIntake(boolean open) {
        if (open){
            openIntakeSolenoid.set(true);
        }else{
            openIntakeSolenoid.set(false);
        }

    }

    public void stopped() {
        setRightMotorSpeed(Constants.intakeStopSpeed);
        setLeftMotorSpeed(Constants.intakeStopSpeed);
    } //stop wheels

    public void pickupPositionDown() { //Flat
        armLongSolenoidIn.set(true);
        armLongSolenoidOut.set(false);
        armShortSolenoidIn.set(true);
        armShortSolenoidOut.set(false);
        raisedPickup = false;
    }

    public void pickupPositionRaised() { //Angled
        armLongSolenoidIn.set(false);
        armLongSolenoidOut.set(true);
        armShortSolenoidIn.set(false);
        armShortSolenoidOut.set(false);
        raisedPickup = true;
    }

    public boolean isPickupRaised(){
        return raisedPickup;
    }

    public void pickupPositionStartingConfig() { //All the way up
        armLongSolenoidIn.set(false);
        armLongSolenoidOut.set(true);
        armShortSolenoidIn.set(false);
        armShortSolenoidOut.set(true);
        raisedPickup = false;
    }

    @Override
    public boolean isCubePickedUp() {
        if (!firstCheckComplete) {
            System.out.println("PICKUP: First check for cube!");
            firstCheckComplete = true;
            startTime = Timer.getFPGATimestamp();
        }
        //just in case the toggle on the front of the test robot stops working, or isn't connected
        //only return true if it has been more than 2 seconds since first check for cube
        boolean failover = Timer.getFPGATimestamp() - pickupTimeoutSeconds > startTime;
        if (colorSensor != null) {
            try {
                System.out.println("Color Sensor sees" + colorSensor.get());
                return colorSensor.get();
            } catch (Exception e) {
                System.out.println("ERROR: exception in color sensor " + failover + ": " + e.getMessage());
//                e.printStackTrace();
                return failover;
            }
        } else {
            System.out.println("Color sensor failed - was NULL " + failover);
            return failover;
        }
    }
    /*
    @Override
    public boolean isCubePickedUp() {
        if (!firstCheckComplete) {
            System.out.println("REAL PICKUP: First check for cube!");
            firstCheckComplete = true;
            startTime = Timer.getFPGATimestamp();
        }
        //see if we have exceeded the timeout
        boolean timedOut = Timer.getFPGATimestamp() - pickupTimeoutSeconds > startTime;

        if(timedOut){
            System.out.println("I HAVE TIMED OUT");
        }
        try {
            boolean ampsExceeded= AmpGetter.getCurrentIntake1(0) >= 30 || AmpGetter.getCurrentIntake2(2) >= 30;
            if (ampsExceeded)
            {
                if (!firstTimeTouchedCube){
                    firstTimeTouchedCube = true;
                    timeForAmps = Timer.getFPGATimestamp();
                    System.out.println("I touched a cube!");
                }else{
                    if(Timer.getFPGATimestamp() - timeForAmps > .5) {
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
    */
}
