package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.RampConstants;

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

    private Ramp() {//todo rename to release pin for clearity
        rampPinLeftIn  = new Solenoid(RampConstants.kRampPinLeftInId);
        rampPinLeftOut = new Solenoid(RampConstants.kRampPinLeftOutId);
        rampPinRightIn = new Solenoid(RampConstants.kRampPinRightInId);
        rampPinRightOut = new Solenoid(RampConstants.kRampPinRightOutId);
        rightRampIn = new Solenoid(RampConstants.kRightRampInId);
        rightRampOut = new Solenoid(RampConstants.kRightRampOutId);
        leftRampIn = new Solenoid(RampConstants.kLeftRampInId);
        leftRampOut = new Solenoid(RampConstants.kLeftRampOutId);
    }
    public void openRampPinLeft() {
        //double time = DriverStation.getInstance().getMatchTime();
        //todo: use above code to check gametime
        rampPinLeftIn.set(true);
        rampPinLeftOut.set(!false);
    }
    public void openRampPinRight() {
        rampPinRightIn.set(true);
        rampPinRightOut.set(!false);
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



