package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import frc.team2052.powerup.constants.ElevatorConstants;

public class Elevator implements Loopable{

    private TalonSRX elevatorTalon;

    //SINGLETON
    private static Elevator instance = new Elevator();
    public static Elevator getInstance(){
        return instance;
    }

    //Constructor
    private Elevator() {
        elevatorTalon = new TalonSRX(ElevatorConstants.kElevatorMotorID);
        elevatorTalon.config_kP(0, ElevatorConstants.kElevatorVelocityKp, 10);//todo: what is slotldx
        elevatorTalon.config_kI(0, ElevatorConstants.kElevatorVelocityKi, 10);
        elevatorTalon.config_kD(0, ElevatorConstants.kElevatorVelocityKd, 10);
        elevatorTalon.config_kF(0, ElevatorConstants.kElevatorVelocityKf, 10);
        elevatorTalon.config_IntegralZone(0, ElevatorConstants.kElevatorVelocityIZone, 10);
        elevatorTalon.configMotionCruiseVelocity(430, 10);//todo: decide timeout seconds
        elevatorTalon.setNeutralMode(NeutralMode.Brake);
    }

    public double getHeightInches() {
        int encoderPos = elevatorTalon.getSelectedSensorPosition(0);
        double revolutions = encoderPos / (double)ElevatorConstants.kElevatorTicksPerRot;
        double inches = revolutions * ElevatorConstants.kInchesPerRotation;
        return inches;
        // return elevatormotr.encoder position #
    }

    private void setHeightInches(double targetInches){
        double rotation = targetInches / ElevatorConstants.kInchesPerRotation;
        int pos = (int)(rotation * ElevatorConstants.kElevatorTicksPerRot);
        //Sets the Carriage at a set height, see https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
        // in 3.1.2.1, recommended timeout is zero while in robot loop
//        elevatorTalon.setSelectedSensorPosition(pulses,0, 0);//todo: check error code
        elevatorTalon.set(ControlMode.Position, pos);
        return ;
    }

    private int goalElevatorInches;

    public void setTarget(ElevatorPresetEnum posEnum) {//sets goal to the correct inches according to the preset
        goalElevatorInches = getHeightInchesForPreset(posEnum);
    }

    public boolean getCarriageIsMoving (){//finds out if the elevator is moving
         boolean accel = elevatorTalon.getSelectedSensorVelocity(0) != 0;
         return accel;
    }


    private boolean lastCyclePressedState = false; //declares that the button isn't pressed at the start of the match
    public void getElevatorAdjustmentUp(boolean isPressed) //Gets value from a button
    {
        if((isPressed != lastCyclePressedState)&& (getHeightInches()<= goalElevatorInches)) //if switching between pressed and not pressed && going up
        {
            if(goalElevatorInches > 85) //if greater than elevator can extend
            {
                goalElevatorInches = 85;
            }
            else if(goalElevatorInches < 0) {//if lower than the elevator can contract
                goalElevatorInches = 0;
            }
            else {
                setHeightInches(goalElevatorInches += 1); //add one inch for each time the button state switches (press + release = two inches)
            }
        }
        lastCyclePressedState = isPressed; //logs what the state is at the end of this cycle to compare against in the next cycle
    }

    public void getElevatorAdjustmentDown(boolean isPressed)
    {

        if((isPressed != lastCyclePressedState)&& (getHeightInches()<= goalElevatorInches)) //if switching between pressed and not pressed && going up
        {
            if(goalElevatorInches > 85) //if greater than elevator can extend
            {
                goalElevatorInches = 85;
            }
            else if(goalElevatorInches < 0) {//if less than elevator can extend
                goalElevatorInches = 0;
            }
            else {
                setHeightInches(goalElevatorInches -= 1); //gets rid of one inch for each time the button state switches (press and release gets rid of two inches)
            }
    }
        lastCyclePressedState = isPressed; //logs what the state is at the end of this cycle to compare against in the next cycle

    }

    //public void setHeightFromPreset()
    @Override
    public void update(){
        int targetInches = goalElevatorInches;
        setHeightInches(targetInches);

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