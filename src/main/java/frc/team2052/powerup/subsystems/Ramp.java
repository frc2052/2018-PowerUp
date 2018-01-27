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
        rampPinLeftIn  = new Solenoid(1,RampConstants.kRampPinLeftInId);
        rampPinLeftOut = new Solenoid(1,RampConstants.kRampPinLeftOutId);
        rampPinRightIn = new Solenoid(1,RampConstants.kRampPinRightInId);
        rampPinRightOut = new Solenoid(1,RampConstants.kRampPinRightOutId);
        rightRampIn = new Solenoid(1,RampConstants.kRightRampInId);
        rightRampOut = new Solenoid(1,RampConstants.kRightRampOutId);
        leftRampIn = new Solenoid(1,RampConstants.kLeftRampInId);
        leftRampOut = new Solenoid(1,RampConstants.kLeftRampOutId);
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



