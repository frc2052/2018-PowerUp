package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

public class Ramp {

    private static Ramp instance = null;
    public static Ramp getInstance() { //if ramp doesn't work, still do other stuff
        if (instance == null) {
            try {
                instance = new Ramp();
            } catch (Exception exc) {
               System.out.println("DANGER: Failed to create Ramp: " + exc.getMessage());
               exc.printStackTrace();
            }
        }
        return instance;
    }

    //Ramp pins for each side and extending/collapsing ramp on each side
    private Servo rightRampServo;
    private Servo leftRampServo;
    private Solenoid rightRampIn;
    private Solenoid rightRampOut;
    private Solenoid leftRampIn;
    private Solenoid leftRampOut;
    private int leftRampRaiseLowerCount = 0;
    private int rightRampRaiseLowerCount = 0;
    private int leftDropRampCount = 0;
    private int rightDropRampCount = 0;

    private Ramp() {//todo rename to release pin for clarity
        rightRampServo = new Servo(Constants.kRampRightServoId);
        leftRampServo = new Servo(Constants.kRampLeftServoId);
        rightRampIn = new Solenoid(Constants.kRightRampInId);
        rightRampOut = new Solenoid(Constants.kRightRampOutId);
        leftRampIn = new Solenoid(Constants.kLeftRampInId);
        leftRampOut = new Solenoid(Constants.kLeftRampOutId);
    }

    private boolean dropLeftRampIsPressed = false;
    /**
     *
     * @param release if true release Left ramp
     */
    public void dropRampPinLeft(boolean release)  {
        double time = DriverStation.getInstance().getMatchTime();
        if (release && !dropLeftRampIsPressed) { //button was just pressed, because it wasn't pressed, and now it is
            leftDropRampCount++; //do this to track how many times the button goes up and down
        }
        dropLeftRampIsPressed = release; //this will keep track of the last known button state (up or down)
        if(time < 30 || leftDropRampCount > 4){
            if(release){
                leftRampServo.setAngle(Constants.kRampLeftServoReleaseAngle);
                System.out.println("DROPPING LEFT RAMP!!! GameTime: " + time);
            }else{
                leftRampServo.setAngle(Constants.kRampLeftServoClosedAngle);
            }
        } else {
            leftRampServo.setAngle(Constants.kRampLeftServoClosedAngle);
            //System.out.println("DON'T DROP LEFT RAMP! Time remaining = " + time + "  Buttom pressed " + rightDropRampCount + " times");
        }
    }

    private boolean dropRightRampIsPressed = false;
    /**
     *
     * @param release if true release Right ramp
     */
    public void dropRampPinRight(boolean release) {
        double time = DriverStation.getInstance().getMatchTime();
        if (release && !dropRightRampIsPressed) { //button was just pressed, because it wasn't pressed, and now it is
            rightDropRampCount++; //do this to track how many times the button goes up and down
        }
        dropRightRampIsPressed = release; //this will keep track of the last known button state (up or down)
        if(time < 30 || rightDropRampCount > 4){
            if(release){
                rightRampServo.setAngle(Constants.kRampRightServoReleaseAngle);
                System.out.println("DROPPING RIGHT RAMP!!! GameTime: " + time);
            }else{
                rightRampServo.setAngle(Constants.kRampRightServoClosedAngle);
            }
        } else {
            rightRampServo.setAngle(Constants.kRampRightServoClosedAngle);
            //System.out.println("DON'T DROP RIGHT RAMP! Time remaining = " + time + "  Buttom pressed " + rightDropRampCount + " times");
        }

    }

    public void raiseRightRamp() {
        double time = DriverStation.getInstance().getMatchTime();
        rightRampRaiseLowerCount++;
        if(time < 30 || rightRampRaiseLowerCount > 4){
            rightRampIn.set(false);
            rightRampOut.set(true);
        }
    }

    public void raiseLeftRamp() {
        double time = DriverStation.getInstance().getMatchTime();
        leftRampRaiseLowerCount++;
        if (time < 30 || leftRampRaiseLowerCount > 4) {
            leftRampIn.set(false);
            leftRampOut.set(true);
        }
    }

    public void lowerLeftRamp() {
        double time = DriverStation.getInstance().getMatchTime();
        leftRampRaiseLowerCount++;
        if (time < 30 || leftRampRaiseLowerCount > 4) {
            leftRampIn.set(true);
            leftRampOut.set(false);
        }
    }

    public void lowerRightRamp() {
        double time = DriverStation.getInstance().getMatchTime();
        rightRampRaiseLowerCount++;
        if(time < 30 || rightRampRaiseLowerCount > 4) {
            rightRampIn.set(true);
            rightRampOut.set(false);
        }
    }
}