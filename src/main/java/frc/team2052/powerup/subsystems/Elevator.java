package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import frc.team2052.powerup.Constants;

public class Elevator implements Loopable{

    private TalonSRX elevatorTalon;
    private boolean runningInOpenLoop = false;

    //SINGLETON
    private static Elevator instance = null;
    public static Elevator getInstance() {
        if (instance == null) {
            try {
                instance = new Elevator();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create Elevator: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }


    //Constructor
    private Elevator() {
        elevatorTalon = new TalonSRX(Constants.kElevatorMotorID);
        elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,10);
        elevatorTalon.setInverted(true);
        elevatorTalon.setSensorPhase(true);
        elevatorTalon.configClosedloopRamp(Constants.kElevatorRampSeconds, 10);
        elevatorTalon.configPeakOutputForward(Constants.kElevatorPeakUpPower, 10);
        elevatorTalon.configPeakOutputReverse(Constants.kElevatorPeakDownPower, 10);
        elevatorTalon.setNeutralMode(NeutralMode.Brake);
        elevatorTalon.config_kP(0, Constants.kElevatorVelocityKp, 10);
        elevatorTalon.config_kI(0, Constants.kElevatorVelocityKi, 10);
        elevatorTalon.config_kD(0, Constants.kElevatorVelocityKd, 10);
        elevatorTalon.config_kF(0, Constants.kElevatorVelocityKf, 10);
        elevatorTalon.config_IntegralZone(0, Constants.kElevatorVelocityIZone, 10);
    }

    public void zeroSensor(){
        elevatorTalon.setSelectedSensorPosition(0, 0, Constants.kCANBusConfigTimeoutMS);
    }

    public double getHeightInches() {
        int encoderPos = elevatorTalon.getSelectedSensorPosition(0);
        double revolutions = encoderPos / (double)Constants.kElevatorTicksPerRot;
        double inches = revolutions * Constants.kElevatorInchesPerRotation;
        return inches;
    }

    private int goalElevatorInches;

    public void setTarget(ElevatorPresetEnum posEnum) {
        //sets goal to the correct inches according to the preset
        int calcTarget = getHeightInchesForPreset(posEnum);
        setAndVerifyGoalInches(calcTarget);
    }

    private void setAndVerifyGoalInches(int newGoalInches){
        if (newGoalInches >  Constants.kElevatorMaxHeight) {
            goalElevatorInches = Constants.kElevatorMaxHeight;
        }
        else if (newGoalInches < Constants.kElevatorMinHeight) {
            goalElevatorInches = Constants.kElevatorMinHeight;
        }
        else {
            goalElevatorInches = newGoalInches;
        }
    }
    //Emergency manual control
    private boolean emergencyDownWasPressed = false; // variable makes it able to stop the motor only one time once it is let go
    public void setEmergencyHold(boolean isPressed) {
        if (isPressed == true) { // if true, it will go up and set the was pressed to true
            runningInOpenLoop = true;//switching to open loop
            elevatorTalon.set(ControlMode.PercentOutput, Constants.kElevatorEmergencyHoldPower);
            emergencyDownWasPressed = true;
        } else { //if false, it will stop once and set the was pressed to false
            if (emergencyDownWasPressed == true) {
                elevatorTalon.set(ControlMode.PercentOutput, 0);
                emergencyDownWasPressed = false; //Only stop the motor the moment it's let go.
            }
        }

    } // similar to the above emergency down code
    private boolean emergencyUpWasPresesed = false; //able to stop motor only once after pressed
    public void setEmergencyUp (boolean isPressed) {
        if (isPressed == true) {
            runningInOpenLoop = true;//switching to open loop
            elevatorTalon.set(ControlMode.PercentOutput,Constants.kElevatorEmergencyUpPower);
            emergencyUpWasPresesed = true;
        } else {
            if (emergencyUpWasPresesed == true) {
                elevatorTalon.set(ControlMode.PercentOutput, 0);
                emergencyUpWasPresesed = false; //Exactly the same as EmergencyDown, except for up.
            }
        }
    }


    public boolean getCarriageIsMoving (){//finds out if the elevator is moving
         boolean accel = elevatorTalon.getSelectedSensorVelocity(0) != 0;
         return accel;
    }


    private boolean lastUpPressedState = false; //declares that the button isn't pressed at the start of the match
    public void setElevatorAdjustmentUp(boolean isPressed) //if the button state has changed, it will add an extra inch
    {
        if (isPressed){//switched back to closed loop mode
            runningInOpenLoop = false;
        }
        if(isPressed != lastUpPressedState) //if switching between pressed and not pressed && going up
        {
            setAndVerifyGoalInches(goalElevatorInches + 1);
        }
        lastUpPressedState = isPressed; //logs what the state is at the end of this cycle to compare against in the next cycle
    }
    private boolean lastDownPressedState = false;

    public void setElevatorAdjustmentDown(boolean isPressed)//if the button state has changed, it will remove an inch
    {
        if (isPressed){//switched back to closed loop mode
            runningInOpenLoop = false;
        }
        if(isPressed != lastDownPressedState) //if switching between pressed and not pressed && going up
        {
            setAndVerifyGoalInches(goalElevatorInches - 1);
        }
        lastDownPressedState = isPressed; //logs what the state is at the end of this cycle to compare against in the next cycle
    }

    //public void setHeightFromPreset()
    @Override
    public void update(){
        if(!runningInOpenLoop) {//we are running in closed loop
            double rotation = goalElevatorInches / Constants.kElevatorInchesPerRotation;
            int pos = (int) (rotation * Constants.kElevatorTicksPerRot);
            //Sets the Carriage at a set height, see https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
            // in 3.1.2.1, recommended timeout is zero while in robot loop
            elevatorTalon.set(ControlMode.Position, pos);
        }
    }
    @Override
    public void onStart(){

    }
    @Override
    public void onStop(){

    }//returns height in inches
    public int getHeightInchesForPreset(ElevatorPresetEnum posEnum){
        switch (posEnum){
            case PICKUP:
                return Constants.kElevatorMinHeight;
            case SWITCH:
                return Constants.kElevatorSwitchHeight;
            case SCALE_BALANCED:
                return (int)(Constants.kElevatorMaxHeight * .6);
            case SCALE_HIGH:
                return (int)(Constants.kElevatorMaxHeight * .8);
            case SCALE_HIGH_STACKING:
                return Constants.kElevatorMaxHeight;
        }
        return 0;
    }

    public enum ElevatorPresetEnum {
        PICKUP(),
        SWITCH, //on top of another cube on switch
        SCALE_BALANCED, //balanced on scale
        SCALE_HIGH,//high scale(when scale is tipped toward opponents side)
        SCALE_HIGH_STACKING,//high scale + cube
    }

}