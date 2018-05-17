package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import frc.team2052.powerup.Constants;

public class Controls {
    private static Controls instance = new Controls();
    public static Controls getInstance(){
        return instance;
    }
//Initiates joysticks
    private Joystick tankPrimaryJoystick = new Joystick(0);
    private Joystick turnPrimaryJoystick = new Joystick(1);
    private Joystick secondaryJoystick = new Joystick(2);


    private Controls() {
    }

//Tank drive for joysticks
    public double getTank() {
        double val = -tankPrimaryJoystick.getY();
        if (val < .15 && val > -.15)
        {
            val = 0;
        }
        return val;
    }

    public double getTurn() {
        double val = -turnPrimaryJoystick.getX();
        if (val < .1 && val > -.1)
        {
            val = 0;
        }else{
            if (val > .1){
                val = val - .1;
            }else if (val < -.1){
                val = val + .1;
            }

            val = (val / .9) * .7;
        }
        return val;
        //return val * .7; // old code
    }

    public boolean getQuickTurn() {
        return turnPrimaryJoystick.getRawButton(3);
    }
//Ramp classes
    public boolean getDropLeftRamp() {return tankPrimaryJoystick.getRawButton(Constants.kJoystickDropLeftPin);}
    public boolean getRaiseLeftRamp() {return tankPrimaryJoystick.getRawButton(Constants.kJoystickRaiseLeftRamp);}
    public boolean getLowerLeftRamp() {return tankPrimaryJoystick.getRawButton(Constants.kJoystickLowerLeftRamp);}

    public boolean getDropRightRamp() {
        return turnPrimaryJoystick.getRawButton(Constants.kJoystickDropRightPin);
    }
    public boolean getRaiseRightRamp() {return turnPrimaryJoystick.getRawButton(Constants.kJoystickRaiseRightRamp);}
    public boolean getLowerRightRamp() {return turnPrimaryJoystick.getRawButton(Constants.kJoystickLowerRightRamp);}

    //Intake classes
    public boolean getIntakeUp() {return secondaryJoystick.getRawButton(Constants.kJoystickIntakeUp);}
    public boolean getIntake () {return secondaryJoystick.getTrigger();}
    public boolean getOuttake () {return tankPrimaryJoystick.getTrigger();}
    public boolean getShoot(){return tankPrimaryJoystick.getRawButton(Constants.kIntakeShootSpeed);}
    public boolean getStartConfig () {return secondaryJoystick.getRawButton(Constants.kJoystickIntakeStartConfig);}
    public boolean getIntakePrimary(){return turnPrimaryJoystick.getTrigger();}
    public boolean getVisionTrack () {return tankPrimaryJoystick.getRawButton(Constants.kVisionTrackTeleop);}
    public boolean getAutotest () {
        if (secondaryJoystick.getX() < -.5){
            return true;
        }else {
            return false;
        }
    }

    //buttons for different stages of elevator
    public boolean getElevatorPickup(){return secondaryJoystick.getRawButton(Constants.kElevatorPickupHeightButton);}
    public boolean getElevatorSwitch(){return  secondaryJoystick.getRawButton(Constants.kElevatorSwitchHeightButton); }
    public boolean getElevatorScale1(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_OneHeightButton); }
    public boolean getElevatorScale2(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_TwoHeightButton); }
    public boolean getElevatorScale3(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_ThreeHeightButton); }

    public boolean getElevatorAdjustmentUp(){ return secondaryJoystick.getRawButton(Constants.kElevatorScale_TrimUpButton);}
    public boolean getElevatorAdjustmentDown(){ return secondaryJoystick.getRawButton(Constants.kElevatorScale_TrimDownButton); }

    public boolean getElevatorEmergencyUp() { return secondaryJoystick.getRawButton(Constants.kManualElevator_RaiseButton)
            || secondaryJoystick.getY() < -.5;}
    public boolean getElevatorEmergencyDown() {return secondaryJoystick.getRawButton(Constants.kManualElevator_HoldButton);}


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
