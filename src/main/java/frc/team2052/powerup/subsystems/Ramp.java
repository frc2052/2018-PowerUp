package frc.team2052.powerup.subsystems;

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

    private Ramp() {//todo rename to release pin for clarity
        rightRampServo = new Servo(Constants.kRampRightServoId);
        leftRampServo = new Servo(Constants.kRampLeftServoId);
        rightRampIn = new Solenoid(Constants.kRightRampInId);
        rightRampOut = new Solenoid(Constants.kRightRampOutId);
        leftRampIn = new Solenoid(Constants.kLeftRampInId);
        leftRampOut = new Solenoid(Constants.kLeftRampOutId);
    }

    /**
     *
     * @param release if true release Left ramp
     */
    public void dropRampPinLeft(boolean release)  {
        if(release){
            leftRampServo.setAngle(Constants.kRampLeftServoReleaseAngle);
        }else{
            leftRampServo.setAngle(Constants.kRampLeftServoClosedAngle);
        }
    }

    /**
     *
     * @param release if true release Right ramp
     */
    public void dropRampPinRight(boolean release) {
        if(release){
            rightRampServo.setAngle(Constants.kRampRightServoReleaseAngle);
        }else{
            rightRampServo.setAngle(Constants.kRampRightServoClosedAngle);
        }
    }

    public void raiseRightRamp() {
            rightRampIn.set(false);
            rightRampOut.set(true);
        }

    public void raiseLeftRamp() {
            leftRampIn.set(false);
            leftRampOut.set(true);
        }

    public void lowerLeftRamp() {
            leftRampIn.set(true);
            leftRampOut.set(false);
        }

    public void lowerRightRamp() {
        rightRampIn.set(true);
        rightRampOut.set(false);

    }
}