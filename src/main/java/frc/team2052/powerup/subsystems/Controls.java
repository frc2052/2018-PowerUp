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
        double val = -leftPrimaryJoystick.getY();
        if (val < .15 && val > -.15)
        {
            val = 0;
        }
        return val;
    }

    public double getTurn() {
        double val = -rightPrimaryJoystick.getX();
        if (val < .1 && val > -.1)
        {
            val = 0;
        }
        return val;
    }

    public boolean getQuickTurn() {
        return rightPrimaryJoystick.getRawButton(3);
    }
//Ramp classes
    public boolean getDropLeftRamp() {return rightPrimaryJoystick.getRawButton(Constants.kJoystickDropLeftPin);}
    public boolean getDropRightRamp() {
        return rightPrimaryJoystick.getRawButton(Constants.kJoystickDropRightPin);
    }
    public boolean getRaiseRightRamp() {return rightPrimaryJoystick.getRawButton(Constants.kJoystickRaiseRightRamp);}
    public boolean getRaiseLeftRamp() {return rightPrimaryJoystick.getRawButton(Constants.kJoystickRaiseLeftRamp);}
    public boolean getLowerRightRamp() {return leftPrimaryJoystick.getRawButton(Constants.kJoystickLowerRightRamp);}
    public boolean getLowerLeftRamp() {return leftPrimaryJoystick.getRawButton(Constants.kJoystickLowerLeftRamp);}

    //Intake classes
    public boolean getIntakeUp() {return secondaryJoystick.getRawButton(Constants.kJoystickIntakeUp);}
    public boolean getIntake () {return secondaryJoystick.getTrigger();}
    public boolean getOuttake () {return leftPrimaryJoystick.getTrigger();}
    public boolean getStartConfig () {return secondaryJoystick.getRawButton(Constants.kJoystickIntakeStartConfig);}

    //buttons for different stages of elevator
    public boolean getElevatorPickup(){return secondaryJoystick.getRawButton(Constants.kElevatorPickupHeightButton);}
    public boolean getElevatorSwitch(){return  secondaryJoystick.getRawButton(Constants.kElevatorSwitchHeightButton); }
    public boolean getElevatorScale1(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_OneHeightButton); }
    public boolean getElevatorScale2(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_TwoHeightButton); }
    public boolean getElevatorScale3(){return  secondaryJoystick.getRawButton(Constants.kElevatorScale_ThreeHeightButton); }
    public boolean getElevatorAdjustmentUp(){ return secondaryJoystick.getRawButton(Constants.kElevatorScale_TrimUpButton); }
    public boolean getElevatorAdjustmentDown(){ return secondaryJoystick.getRawButton(Constants.kElevatorScale_TrimDownButton); }

    public boolean getElevatorEmergencyUp() { return secondaryJoystick.getRawButton(Constants.kManualElevator_RaiseButton);}
    public boolean getElevatorEmergencyDown() {return secondaryJoystick.getRawButton(Constants.kManualElevator_LowerButton);}


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
