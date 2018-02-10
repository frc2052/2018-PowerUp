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
    private Solenoid rampLeftPinReleaseSol;
    private Solenoid rampPinLeftOut;
    private Solenoid rampRightPinReleaseSol;
    private Solenoid rampPinRightOut;
    private Solenoid rightRampInSol;
    private Solenoid rightRampOutSol;
    private Solenoid leftRampInSol;
    private Solenoid leftRampOutSol;

    private Ramp() {
        rampLeftPinReleaseSol = new Solenoid(Constants.kRampLeftPinReleaseId); //The pins are used to keep the ramps up while the robot is driving
        rampRightPinReleaseSol = new Solenoid(Constants.kRampRightPinReleaseId);
        rightRampInSol = new Solenoid(Constants.kRightRampInId);
        rightRampOutSol = new Solenoid(Constants.kRightRampOutId);
        leftRampInSol = new Solenoid(Constants.kLeftRampInId);
        leftRampOutSol = new Solenoid(Constants.kLeftRampOutId);
    }
    public void dropRampPinLeft() {
        double time = DriverStation.getInstance().getMatchTime();
        rampLeftPinReleaseSol.set(true);
    }
    public void dropRampPinRight() {
        rampRightPinReleaseSol.set(true);
    }
    public void raiseRightRamp() {
        rightRampInSol.set(true);
        rightRampOutSol.set(false);
    }
    public void raiseLeftRamp() {
        leftRampInSol.set(true);
        leftRampOutSol.set(false);
    }
    public void lowerLeftRamp() {
        leftRampInSol.set(false);
        leftRampOutSol.set(true);
    }
    public void lowerRightRamp() {
        rightRampInSol.set(false);
        rightRampOutSol.set(true);
    }




    }



