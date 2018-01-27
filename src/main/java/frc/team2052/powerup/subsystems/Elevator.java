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
    public static Elevator getInstance(){
        return instance;
    }

    public double getHeightInches() {
        int encoderPos = elevatorTalon.getSelectedSensorPosition(0);
        double revolutions = encoderPos / (double)ElevatorConstants.kElevatorTicksPerRot;
        double inches = revolutions * ElevatorConstants.kInchesPerRotation;
        return inches;
        // return elevatormotr.encoder position #
    }

    private void setHeightInches(double tartetInches){
        double rotation = tartetInches / ElevatorConstants.kInchesPerRotation;
        int pos = (int)(rotation * ElevatorConstants.kElevatorTicksPerRot);
        //Sets the Carriage at a set height, see https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
        // in 3.1.2.1, recommended timeout is zero while in robot loop
//        elevatorTalon.setSelectedSensorPosition(pulses,0, 0);//todo: check error code
        elevatorTalon.set(ControlMode.Position, pos);
        return ;
    }

    private int goalElevatorInches;

    public void setTarget(ElevatorPresetEnum posEnum) {
        goalElevatorInches = getHeightInchesForPreset(posEnum);

    }
    public boolean getCarriageIsMoving (){
         boolean accel = elevatorTalon.getSelectedSensorVelocity(0) != 0;
         return accel;
    }

    //public void setHeightFromPreset()
    @Override
    public void update(){

    }
    @Override
    public void onStart(){

    }
    @Override
    public void onStop(){

    }
    public int getHeightInchesForPreset(ElevatorPresetEnum posEnum){
        switch (posEnum){
            case PICKUP:
                return 0;
            case SWITCH:
                return 25;
            case SCALE_ONE:
                return 64;
            case SCALE_TWO:
                return 76;
            case SCALE_THREE:
                return 88;
        }
        return 0;
    }

  /*  public int getHeightInchesJoystick(double joyValue)
    {
        if(joyValue > 0.65 )
        {
            joyValue = 0.65;
        }
        if(joyValue < -0.65)
        {
            joyValue = -0.65;
        }
        return joyValue;
    }*/
    public enum ElevatorPresetEnum {
        PICKUP,
        SWITCH, //on top of another cube
        SCALE_ONE, //balanced
        SCALE_TWO,//high
        SCALE_THREE,//high + cube
    }
}