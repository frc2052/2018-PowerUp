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


    private ElevatorPosEnum currentElevatorPos;

    public void setCurrentPos(ElevatorPosEnum posEnum) {
        currentElevatorPos = posEnum;

    }
    public ElevatorPosEnum getCurrentPos (){
        return currentElevatorPos;
    }
    public boolean getCarriageIsMoving (){
         boolean accel = elevatorTalon.getSelectedSensorVelocity(0) != 0;
         return accel;
    }


    @Override
    public void update(){
        int targetInches = getHeightInchesForPos(currentElevatorPos);
        setHeightInches(targetInches);

    }
    @Override
    public void onStart(){

    }
    @Override
    public void onStop(){

    }
    public int getHeightInchesForPos(ElevatorPosEnum posEnum){
        switch (posEnum){
            case PICKUP:
                return 0;
            case SWITCH_ONE:
                return 19;
            case SWITCH_TWO:
                return 25;
            case SCALE_ONE:
                return 52;
            case SCALE_TWO:
                return 64;
            case SCALE_THREE:
                return 76;
            case SCALE_FOUR:
                return 88;
        }
        return 0;
    }
    public enum ElevatorPosEnum {
        PICKUP,
        SWITCH_ONE,
        SWITCH_TWO,
        SCALE_ONE,
        SCALE_TWO,
        SCALE_THREE,
        SCALE_FOUR
    }
}