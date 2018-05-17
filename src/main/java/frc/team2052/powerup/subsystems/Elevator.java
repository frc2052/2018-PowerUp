package frc.team2052.powerup.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.first.team2052.lib.Loopable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.subsystems.Interfaces.ElevatorSubsystem;

public class Elevator implements Loopable,ElevatorSubsystem{

    private TalonSRX elevatorTalon;
    private boolean runningInOpenLoop = false;

    private int goalElevatorInches;

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
        /*
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
        */
        elevatorTalon = new TalonSRX(Constants.kElevatorMotorID);
        elevatorTalon.setNeutralMode(NeutralMode.Brake);
        elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        elevatorTalon.setInverted(true);
        elevatorTalon.setSensorPhase(true);

        /* Set relevant frame periods to be at least as fast as periodic rate */
        elevatorTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 10);
        elevatorTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);

        /* set the peak and nominal outputs */
        elevatorTalon.configNominalOutputForward(0, 10);
        elevatorTalon.configNominalOutputReverse(0, 10);
        elevatorTalon.configPeakOutputForward(Constants.kElevatorPeakUpPower, 10);
        elevatorTalon.configPeakOutputReverse(Constants.kElevatorPeakDownPower, 10);

        /* set closed loop gains in slot0 - see documentation */
        elevatorTalon.selectProfileSlot(0, 0);
        elevatorTalon.config_kF(0, 0.2, 10); //1843
        elevatorTalon.config_kP(0, .8, 10);
        elevatorTalon.config_kI(0, 0, 10);
        elevatorTalon.config_kD(0, 0, 10);

        /* set acceleration and vcruise velocity - see documentation */
        elevatorTalon.configMotionCruiseVelocity((int)(5550 * .9), 10);
        elevatorTalon.configMotionAcceleration((int)(5550 * .9 * 2), 10);
        /* zero the sensor */
        elevatorTalon.setSelectedSensorPosition(0, 0, 10);
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

    public void setTarget(ElevatorPresetEnum posEnum) {
        //sets goal to the correct inches according to the preset
        runningInOpenLoop = false;
        int calcTarget = getHeightInchesForPreset(posEnum);
        setAndVerifyGoalInches(calcTarget);
    }

    public void setCurrentPosAsTarget(){
        setAndVerifyGoalInches((int)getHeightInches());
    }


    private void setAndVerifyGoalInches(int newGoalInches){
        if (newGoalInches >  Constants.kElevatorMaxHeight) {
            goalElevatorInches = Constants.kElevatorMaxHeight;
        }
        else if (newGoalInches < Constants.kElevatorMinHeight) {
            System.out.println("INVALID ELEVATOR VALUE : " + newGoalInches);
            goalElevatorInches = Constants.kElevatorMinHeight;
        }
        else {
            goalElevatorInches = newGoalInches;

        }
        if (goalElevatorInches <= getHeightInches()) {
            elevatorTalon.configMotionCruiseVelocity((int)(5550 * .5), 10);
            elevatorTalon.configMotionAcceleration((int)(5550 * .5 * 2), 10);
        }else{
            elevatorTalon.configMotionCruiseVelocity((int)(5550 * .9), 10);
            elevatorTalon.configMotionAcceleration((int)(5550 * .9 * 2), 10);
        }
    }
    //Emergency manual control
    private boolean emergencyDownWasPressed = false; // variable makes it able to stop the motor only one time once it is let go

    public void setEmergencyDown(boolean isPressed) {
        if (isPressed == true) { // if true, it will go up and set the was pressed to true
            runningInOpenLoop = true;//switching to open loop
            elevatorTalon.set(ControlMode.PercentOutput, 0);
            emergencyDownWasPressed = true;
        } else { //if false, it will stop once and set the was pressed to false
            if (emergencyDownWasPressed == true) {
                elevatorTalon.set(ControlMode.PercentOutput, Constants.kElevatorEmergencyHoldPower);
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
                elevatorTalon.set(ControlMode.PercentOutput, Constants.kElevatorEmergencyHoldPower);
                emergencyUpWasPresesed = false; //Exactly the same as EmergencyDown, except for up.
            }
        }
    }


    public boolean getCarriageIsMoving (){//finds out if the elevator is moving
         boolean accel = elevatorTalon.getSelectedSensorVelocity(0) != 0
                 && AmpGetter.getCurrentElevator(Constants.kElevatorMotorID) > 30;
         return accel;
    }


    private boolean lastUpPressedState = false; //declares that the button isn't pressed at the start of the match
    public void setElevatorAdjustmentUp(boolean isPressed) //if the button state has changed, it will add an extra inch
    {
        if (isPressed && runningInOpenLoop){//switched back to closed loop mode
            setCurrentPosAsTarget(); //set the target position to the current position so we don't jerk around
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
        if (isPressed && runningInOpenLoop){//switched back to closed loop mode
            setCurrentPosAsTarget(); //set the target position to the current position so we don't jerk around
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

        if(getHeightInches() < 0){
            zeroSensor();
        }

        if(!runningInOpenLoop) {//we are running in closed loop
            double rotation = goalElevatorInches / Constants.kElevatorInchesPerRotation;
            int pos = (int) (rotation * Constants.kElevatorTicksPerRot);
            //Sets the Carriage at a set height, see https://github.com/CrossTheRoadElec/Phoenix-Documentation/blob/master/Talon%20SRX%20Victor%20SPX%20-%20Software%20Reference%20Manual.pdf
            // in 3.1.2.1, recommended timeout is zero while in robot loop
            elevatorTalon.set(ControlMode.MotionMagic, pos);
            SmartDashboard.putNumber("ElevatorTargetPos", goalElevatorInches);
            SmartDashboard.putNumber("ElevatorPos", getHeightInches());


//            if(!getCarriageIsMoving() && getHeightInches() < goalElevatorInches ){ //todo add amps as well
//                System.out.println("THE ELEVATOR IS AT THE TOP AND TRYING TO GO HIGHER");
//                setCurrentPosAsTarget();
//            }

//            if(!getCarriageIsMoving() && getHeightInches() > goalElevatorInches ){//todo same as above
//                System.out.println("THE ELEVATOR IS AT THE BOTTOM AND TRYING TO GO LOWER...ZEROING");
//                zeroSensor();
//                setCurrentPosAsTarget();
//            }
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