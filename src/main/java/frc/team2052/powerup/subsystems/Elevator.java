package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Elevator {
/*    set.Height(height)
        motor = encoder value;

    get Height
        return motor.encoder * Multiplier;



  */
    private TalonSRX elevatorTalon = new TalonSRX();//todo: add constant
    public static final int ELEVATOR_SPEED = 1;
    public double height = (/*todo: get gear diameter*Math.PI*/ / /*todo: get gear ratio*/ );


    //SINGLETON
    private static Elevator instance = new Elevator();
    //Constructor
    private Elevator() {//todo: FIGURE OUT HOW TO CREATE NEW TALONS}

    //todo:SINGTON
    public int getHeightInches(); {
        return height;
//        return elevatormotr.encoder position *
    }
    }

    public void setHeight(int heightInches){
        elevatorTalon.set(ControlMode.PercentOutput,ELEVATOR_SPEED);
        //height = encoder / gear ratio
        //todo: math for elevator height
    }



}
