package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import frc.team2052.powerup.constants.ControllerConstants;

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

    public boolean getQuickTurn() {
        return joystick1.getRawButton(3);
    }

    public boolean getDropLeftRamp() {
        return joystick0.getRawButton(ControllerConstants.kJoystickDropLeftPin);
    }
    public boolean getDropRightRamp() {
        return joystick1.getRawButton(ControllerConstants.kJoystickDropRightPin);
    }
    public boolean getRaiseRightRamp() {
        return joystick1.getRawButton(ControllerConstants.kJoystickRaiseRightRamp);
    }
    public boolean getRaiseLeftRamp() {
        return joystick0.getRawButton(ControllerConstants.kJoystickRaiseLeftRamp);
    }
    public boolean getLowerRightRamp() {
        return joystick1.getRawButton(ControllerConstants.kJoystickLowerRightRamp);
    }
    public boolean getLowerLeftRamp() {
        return joystick0.getRawButton(ControllerConstants.kJoystickLowerLeftRamp);
    }
    public boolean getPullWinch() {
        return secondaryStick.getRawButton(ControllerConstants.kJoystickPullWinch);
    }
    public boolean getIntakeOpenOff() {
        return joystick1.getRawButton(ControllerConstants.kJoystickOpenOff);
    }
    public boolean getIntakeOpenIntake() {
        return joystick1.getRawButton(ControllerConstants.kJoystickOpenIntake);
    }
    public boolean getIntakeOpenOuttake() {
        return joystick1.getRawButton(ControllerConstants.kJoystickOpenOuttake);
    }
    public boolean getCloseClamp() {
        return secondaryStick.getRawButton(ControllerConstants.kJoystickCloseClamp);
    }
    public boolean getOpenClamp() {
        return secondaryStick.getRawButton(ControllerConstants.kJoystickOpenClamp);
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
