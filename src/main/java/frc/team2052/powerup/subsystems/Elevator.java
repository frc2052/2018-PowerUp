package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team2052.powerup.constants.DriveConstants;
import frc.team2052.powerup.constants.ElevatorConstants;

public class Elevator {
/*    set.Height(height)
        motor = encoder value;

    get Height
        return motor.encoder * Multiplier;



  */

    private TalonSRX elevatorTalon = new TalonSRX(1);//todo: add constant
    public static final int ELEVATOR_SPEED_UP = 1;
    public static final int ELEVATOR_SPEED_DWN = -1;
    public static final int ELEVATOR_SPEED_STOP = 0;
    public double carriageHeight = 1;//todo: get gear diameter*Math.PI *  gear ratio * encoder # );

    //SINGLETON
    private static Elevator instance = new Elevator();

    //Constructor
    private Elevator() { //todo: FIGURE OUT HOW TO CREATE NEW TALONS

    }

    //todo:SINGTON
    public double getHeightInches() {
        int encoderPos = elevatorTalon.getSelectedSensorPosition(0);
        double revolutions = encoderPos / (double)DriveConstants.kDriveEncoderTicksPerRot;
        double inches = revolutions * ElevatorConstants.kInchesPerRotation;
        return inches;
        // return elevatormotr.encoder position #
    }

    public void setHeightInches(double tartetInches){
        double rotation = tartetInches / ElevatorConstants.kInchesPerRotation;
        int pulses = (int)(rotation * DriveConstants.kDriveEncoderTicksPerRot);
        //Sets the Carriage at a set height, see https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
        // in 3.1.2.1, recommended timeout is zero while in robot loop
        elevatorTalon.setSelectedSensorPosition(pulses,0, 0);//todo: check error code
        return ;
    }
    //Method for bringing carriage to Switch height
    public void setHeightSwitch(double switchHeight) {

        //if carriage height
        if (switchHeight > carriageHeight) {
            elevatorTalon.set(ControlMode.PercentOutput, ELEVATOR_SPEED_UP);
        }
         else if (switchHeight < carriageHeight) {
             elevatorTalon.set(ControlMode.PercentOutput, ELEVATOR_SPEED_DWN);
         }
    }
    //Method for bringing carriage to Scale height
    public void setHeightScale(double scaleHeight) {

        if (scaleHeight > carriageHeight) {
            elevatorTalon.set(ControlMode.PercentOutput, ELEVATOR_SPEED_UP);
        }
        else if (scaleHeight < carriageHeight) {
            elevatorTalon.set(ControlMode.PercentOutput, ELEVATOR_SPEED_DWN);
        }



         //todo: add cube manipulator code for elevator
            //height = encoder / gear ratio
            //todo: math for elevator height


    }

}