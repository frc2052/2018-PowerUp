package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.Constants;

public class Ramp {

    private static Ramp instance = new Ramp();
    public static Ramp getInstance() {
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

    private Ramp() {
        rampPinLeftIn  = new Solenoid(Constants.kRampPinLeftInId);
        rampPinLeftOut = new Solenoid(Constants.kRampPinLeftOutId);
        rampPinRightIn = new Solenoid(Constants.kRampPinRightInId);
        rampPinRightOut = new Solenoid(Constants.kRampPinRightOutId);
        rightRampIn = new Solenoid(Constants.kRightRampInId);
        rightRampOut = new Solenoid(Constants.kRightRampOutId);
        leftRampIn = new Solenoid(Constants.kLeftRampInId);
        leftRampOut = new Solenoid(Constants.kLeftRampOutId);
    }
    public void openRampPinLeft(boolean rampPinLeftPressed) {
        rampPinLeftIn.set(rampPinLeftPressed);
        rampPinLeftOut.set(!rampPinLeftPressed);
    }
    public void openRampPinRight(boolean rampPinRightPressed) {
        rampPinRightIn.set(rampPinRightPressed);
        rampPinRightOut.set(!rampPinRightPressed);
    }
    public void openRightRamp(boolean rightRampPressed) {
        rightRampIn.set(rightRampPressed);
        rightRampOut.set(!rightRampPressed);
    }
    public void openLeftRamp(boolean leftRampPressed) {
        leftRampIn.set(leftRampPressed);
        leftRampOut.set(!leftRampPressed);
    }




    }



