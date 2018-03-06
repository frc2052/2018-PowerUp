package frc.team2052.powerup.subsystems.Fakes;

import com.first.team2052.lib.Loopable;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.subsystems.Elevator;
import frc.team2052.powerup.subsystems.Interfaces.ElevatorSubsystem;

public class FakeElevator implements ElevatorSubsystem, Loopable {
    private static FakeElevator instance = new FakeElevator();
    public static FakeElevator getInstance(){
        return instance;
    }
    private double currentInches = 0;
    @Override
    public void zeroSensor() {

    }

    @Override
    public double getHeightInches() {
        return currentInches;
    }


    @Override
    public void setTarget(Elevator.ElevatorPresetEnum posEnum) {
        currentInches = getHeightInchesForPreset(posEnum);
    }

    @Override
    public void setCurrentPosAsTarget() {

    }

    @Override
    public void setEmergencyHold(boolean isPressed) {

    }

    @Override
    public void setEmergencyUp(boolean isPressed) {

    }

    @Override
    public boolean getCarriageIsMoving() {
        return false;
    }

    @Override
    public void setElevatorAdjustmentUp(boolean isPressed) {

    }

    @Override
    public void setElevatorAdjustmentDown(boolean isPressed) {

    }

    @Override
    public int getHeightInchesForPreset(Elevator.ElevatorPresetEnum posEnum){
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

    @Override
    public void update() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
