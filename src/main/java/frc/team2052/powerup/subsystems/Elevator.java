package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import frc.team2052.powerup.constants.ElevatorConstants;

public class Elevator implements Loopable{

    private TalonSRX elevatorTalon;

    //SINGLETON
    private static Elevator instance = null;
    public static Elevator getInstance() {
        if (instance == null) { //if there is no elevator, dont crash
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
        elevatorTalon = new TalonSRX(ElevatorConstants.kElevatorMotorID);
        elevatorTalon.config_kP(0, ElevatorConstants.kElevatorVelocityKp, 10);
        elevatorTalon.config_kI(0, ElevatorConstants.kElevatorVelocityKi, 10);
        elevatorTalon.config_kD(0, ElevatorConstants.kElevatorVelocityKd, 10);
        elevatorTalon.config_kF(0, ElevatorConstants.kElevatorVelocityKf, 10);
        elevatorTalon.config_IntegralZone(0, ElevatorConstants.kElevatorVelocityIZone, 10);
        elevatorTalon.configMotionCruiseVelocity(430, 10);//todo: decide sensor units per sec
        elevatorTalon.setNeutralMode(NeutralMode.Brake);
    }
    //elevatorTalon.setSelectedSensorPosition(pos,0, 0);//resets the encoder todo: implement reseting the encoder

    /**
     * @return elevatormotor encoder position in inches
     */
    public double getHeightInches() {
        return (elevatorTalon.getSelectedSensorPosition(0) / (double)ElevatorConstants.kElevatorTicksPerRot) * ElevatorConstants.kInchesPerRotation;
    }

    /**
     *
     * @param targetInches Go to the set inches
     */
    private void setHeightInches(double targetInches){
        double rotation = targetInches / ElevatorConstants.kInchesPerRotation;
        int pos = (int)(rotation * ElevatorConstants.kElevatorTicksPerRot);
        //Sets the Carriage at a set height, see https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
        // in 3.1.2.1, recommended timeout is zero while in robot loop

        elevatorTalon.set(ControlMode.Position, pos);
    }

    private int goalElevatorInches; //the position the elevator is attempting to go to

    /**
     *
     * @param posEnum The setpoint that you would like to set as a target
     */
    public void setTarget(ElevatorPresetEnum posEnum) {//sets goal to the correct inches according to the preset
        goalElevatorInches = getHeightInchesForPreset(posEnum);
    }

    /**
     *
     * @return true if carriage is moving
     */
    public boolean getCarriageIsMoving (){//finds out if the elevator is moving
         boolean velocity = elevatorTalon.getSelectedSensorVelocity(0) != 0;
         return velocity;
    }

    private boolean lastCyclePressedState = false; //declares that the button isn't pressed at the start of the match

    /**
     *
     * @param isPressed
     */
    public void getElevatorAdjustmentUp(boolean isPressed) //Gets value from a button
    {
        if((isPressed != lastCyclePressedState)&& (getHeightInches()<= goalElevatorInches)) //if switching between pressed and not pressed && going up
        {
            goalElevatorInches += 1;
            if(goalElevatorInches > 85) //if greater than elevator can extend
            {
                goalElevatorInches = 85;
            }
            else if(goalElevatorInches < 0) {//if lower than the elevator can contract
                goalElevatorInches = 0;
            }
            else {
                setHeightInches(goalElevatorInches); //add one inch for each time the button state switches (press + release = two inches)
            }
        }
        lastCyclePressedState = isPressed; //logs what the state is at the end of this cycle to compare against in the next cycle
    }

    public void getElevatorAdjustmentDown(boolean isPressed)
    {
        if((isPressed != lastCyclePressedState)&& (getHeightInches()<= goalElevatorInches)) //if switching between pressed and not pressed && going up
        {
            goalElevatorInches -= 1;
            if(goalElevatorInches > 85) //if greater than elevator can extend
            {
                goalElevatorInches = 85;
            }
            else if(goalElevatorInches < 0) {//if less than elevator can extend
                goalElevatorInches = 0;
            }
            else {
                setHeightInches(goalElevatorInches); //gets rid of one inch for each time the button state switches (press and release gets rid of two inches)
            }
        }
        lastCyclePressedState = isPressed; //logs what the state is at the end of this cycle to compare against in the next cycle
    }

    //public void setHeightFromPreset()
    @Override
    public void update(){
        setHeightInches(goalElevatorInches);
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
                return 0;
            case SWITCH:
                return 25;
            case SCALE_BALANCED:
                return 64;
            case SCALE_HIGH:
                return 76;
            case SCALE_HIGH_STACKING:
                return 88;
        }
        return 0;
    }

    public enum ElevatorPresetEnum {
        PICKUP,
        SWITCH, //on top of another cube on switch
        SCALE_BALANCED, //balanced on scale
        SCALE_HIGH,//high scale(when scale is tipped toward oponents side)
        SCALE_HIGH_STACKING,//high scale + cube
    }
}