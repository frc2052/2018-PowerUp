package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import frc.team2052.powerup.Constants;

public class Controls {
    private static Controls instance = new Controls();
    public static Controls getInstance(){
        return instance;
    }
//Initiates joysticks
    private Joystick leftPrimaryJoystick = new Joystick(0);
    private Joystick rightPrimaryJoystick = new Joystick(1);
    private Joystick secondaryJoystick = new Joystick(2);


    private Controls() {
    }

//Tank drive for joysticks
    public double getTank() {
        return leftPrimaryJoystick.getY();
    }

    public double getTurn() {
        return -rightPrimaryJoystick.getX();
    }

    public boolean getElevatorAdjustmentUp(){return secondaryJoystick.getRawButton(6);}//press button 6 to raise elevator up 2 in

    public boolean getElevatorAdjustmentDown(){return secondaryJoystick.getRawButton(7);}//press button 7 to lower elevator 2 in

    public boolean getQuickTurn() {
        return rightPrimaryJoystick.getRawButton(3);
    }
//Ramp classes
    public boolean getDropLeftRamp() {return leftPrimaryJoystick.getRawButton(Constants.kJoystickDropLeftPin);
    }
    public boolean getDropRightRamp() {
        return rightPrimaryJoystick.getRawButton(Constants.kJoystickDropRightPin);
    }
    public boolean getRaiseRightRamp() {
        return rightPrimaryJoystick.getRawButton(Constants.kJoystickRaiseRightRamp);
    }
    public boolean getRaiseLeftRamp() {
        return rightPrimaryJoystick.getRawButton(Constants.kJoystickRaiseLeftRamp);
    }
    public boolean getLowerRightRamp() {
        return leftPrimaryJoystick.getRawButton(Constants.kJoystickLowerRightRamp);
    }
    public boolean getLowerLeftRamp() {
        return leftPrimaryJoystick.getRawButton(Constants.kJoystickLowerLeftRamp);
    }
    //Intake classes
    public boolean getIntakeUp() {return secondaryJoystick.getRawButton(Constants.kJoystickIntakeUp);}
    public boolean getPullWinch() {
        return secondaryJoystick.getRawButton(Constants.kJoystickPullWinch);
    }
    public boolean getIntakeOpenOff() {
        return rightPrimaryJoystick.getRawButton(Constants.kJoystickOpenOff);
    }
    public boolean getIntakeOpenIntake() {
        return rightPrimaryJoystick.getTrigger();
    }
    public boolean getIntakeOpenOuttake() {
        return secondaryJoystick.getTrigger();
    }
    public boolean getCloseClamp() {
        return secondaryJoystick.getRawButton(Constants.kJoystickCloseClamp);
    }
    public boolean getOpenClamp() {
        return secondaryJoystick.getRawButton(Constants.kJoystickOpenClamp);
    }

//buttons for different stages of elevator
    public boolean getElevatorPickup(){return secondaryJoystick.getRawButton(Constants.kElevatorPickupHeightButton);}
    public boolean getElevatorSwitch(){return  secondaryJoystick.getRawButton(Constants.kElevatorSwitchHeightButton); }
    public boolean getElevatorScale1(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_OneHeightButton); }
    public boolean getElevatorScale2(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_TwoHeightButton); }
    public boolean getElevatorScale3(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_ThreeHeightButton); }
   /* public Pickup.PickupState getIntakeState() {
        if (secondaryJoystick.getRawButton(2)) {
            return Pickup.PickupState.IN;
        } else if (secondaryJoystick.getRawButton(3)) {
            return Pickup.PickupState.OUT;
        } else {
            return Pickup.PickupState.STOP;
        }
    } */




    }
