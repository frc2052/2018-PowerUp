package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import frc.team2052.powerup.constants.RampConstants;

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
    private Solenoid rampPinRightIn;
    private Solenoid rightRampIn;
    private Solenoid rightRampOut;
    private Solenoid leftRampIn;
    private Solenoid leftRampOut;

    private Ramp() {//todo rename to release pin for clearity
        rampPinLeftIn  = new Solenoid(1,RampConstants.kRampPinLeftInId);
        rampPinRightIn = new Solenoid(1,RampConstants.kRampPinRightInId);
        rightRampIn = new Solenoid(1,RampConstants.kRightRampInId);
        rightRampOut = new Solenoid(1,RampConstants.kRightRampOutId);
        leftRampIn = new Solenoid(1,RampConstants.kLeftRampInId);
        leftRampOut = new Solenoid(1,RampConstants.kLeftRampOutId);
    }
    public void openRampPinLeft() {
        //double time = DriverStation.getInstance().getMatchTime();
        //todo: use above code to check gametime
        rampPinLeftIn.set(true);
    }
    public void openRampPinRight() {
        rampPinRightIn.set(true);
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



