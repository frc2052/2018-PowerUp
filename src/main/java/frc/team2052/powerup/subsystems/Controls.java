package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import frc.team2052.powerup.Constants;

/**
 * Created by KnightKrawler on 1/15/2018.
 */
public class Controls {
    private static Controls instance = new Controls();
    public static Controls getInstance(){
        return instance;
    }

    private Joystick joystick0 = new Joystick(0);
    private Joystick joystick1 = new Joystick(1);
    private Joystick secondaryStick = new Joystick(2);


    private Controls() {
    }



    public double getTank() {
        double tank = -joystick0.getY();
        if (!joystick1.getTrigger()) {
            tank *= -1;
        }
        return tank;
    }

    public double getTurn() {
        return joystick1.getX();
    }





    public boolean getDropLeftRamp() {
        return secondaryStick.getRawButton(Constants.kJoystickDropLeftPin);
    }
    public boolean getDropRightRamp() {
        return secondaryStick.getRawButton(Constants.kJoystickDropRightPin);
    }
    public boolean getRaiseRightRamp() {
        return secondaryStick.getRawButton(Constants.kJoystickRaiseRightRamp);
    }
    public boolean getRaiseLeftRamp() {
        return secondaryStick.getRawButton(Constants.kJoystickRaiseLeftRamp);
    }
    public boolean getLowerRightRamp() {
        return secondaryStick.getRawButton(Constants.kJoystickLowerRightRamp);
    }
    public boolean getLowerLeftRamp() {
        return secondaryStick.getRawButton(Constants.kJoystickLowerLeftRamp);
    }
    public boolean getPullWinch() {
        return secondaryStick.getRawButton(Constants.kJoystickPullWinch);
    }
    public boolean getOpenOff() {
        return secondaryStick.getRawButton(Constants.kJoystickOpenOff);
    }
    public boolean getOpenIntake() {
        return secondaryStick.getRawButton(Constants.kJoystickOpenIntake);
    }
    public boolean getOpenOuttake() {
        return secondaryStick.getRawButton(Constants.kJoystickOpenOuttake);
    }
    public boolean getClosed() {
        return secondaryStick.getRawButton(Constants.kJoystickClosed);
    }
    public boolean getCloseClamp() {
        return secondaryStick.getRawButton(Constants.kJoystickCloseClamp);
    }
    public boolean getOpenClamp() {
        return secondaryStick.getRawButton(Constants.kJoystickOpenClamp);
    }



   /* public Pickup.PickupState getIntakeState() {
        if (secondaryStick.getRawButton(2)) {
            return Pickup.PickupState.IN;
        } else if (secondaryStick.getRawButton(3)) {
            return Pickup.PickupState.OUT;
        } else {
            return Pickup.PickupState.STOP;
        }
    } */




    }
