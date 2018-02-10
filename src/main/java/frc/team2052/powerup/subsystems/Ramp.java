package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

public class Ramp {

    private static Ramp instance = null;
    public static Ramp getInstance() {
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
    private Solenoid rampPinLeftIn;
    private Solenoid rampPinLeftOut;
    private Solenoid rampPinRightIn;
    private Solenoid rampPinRightOut;
    private Solenoid rightRampIn;
    private Solenoid rightRampOut;
    private Solenoid leftRampIn;
    private Solenoid leftRampOut;

    private Ramp() {//todo rename to release pin for clarity
        rampPinLeftIn  = new Solenoid(Constants.kRampPinLeftInId); //The pins are used to keep the ramps up while the robot is driving
        rampPinRightIn = new Solenoid(Constants.kRampPinRightInId);
        rightRampIn = new Solenoid(Constants.kRightRampInId);
        rightRampOut = new Solenoid(Constants.kRightRampOutId);
        leftRampIn = new Solenoid(Constants.kLeftRampInId);
        leftRampOut = new Solenoid(Constants.kLeftRampOutId);
    }
    public void dropRampPinLeft() {
        double time = DriverStation.getInstance().getMatchTime();
        rampPinLeftIn.set(true);
    }
    public void dropRampPinRight() {
        rampPinRightIn.set(true);
    }
    public void raiseRightRamp() {
        rightRampIn.set(true);
        rightRampOut.set(false);
    }
    public void raiseLeftRamp() {
        leftRampIn.set(true);
        leftRampOut.set(false);
    }
    public void lowerLeftRamp() {
        leftRampIn.set(false);
        leftRampOut.set(true);
    }
    public void lowerRightRamp() {
        rightRampIn.set(false);
        rightRampOut.set(true);
    }




    }



