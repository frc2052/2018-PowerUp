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
        System.out.println("FAKE ELEVATOR: Zeroing fake sensor");
    }

    @Override
    public double getHeightInches() {
        System.out.println("FAKE ELEVATOR: Returning 'Height'");
        return currentInches;
    }


    @Override
    public void setTarget(Elevator.ElevatorPresetEnum posEnum) {
        System.out.println("FAKE ELEVATOR: Setting Fake Target");
        currentInches = getHeightInchesForPreset(posEnum);
    }

    @Override
    public void setCurrentPosAsTarget() {
        System.out.println("FAKE ELEVATOR: Setting Current Pos As Fake Target");
    }

    @Override
    public void setEmergencyHold(boolean isPressed) {
        System.out.println("FAKE ELEVATOR: Setting the fake to emergency stop");
    }

    @Override
    public void setEmergencyUp(boolean isPressed) {
        System.out.println("FAKE ELEVATOR: Setting the fake to emergency up");
    }

    @Override
    public boolean getCarriageIsMoving() {
        System.out.println("FAKE ELEVATOR: Carriage isn't moving");
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
                System.out.println("FAKE ELEVATOR: Picking up nothing");
                return Constants.kElevatorMinHeight;
            case SWITCH:
                System.out.println("FAKE ELEVATOR: Moving nothing to switch height");
                return Constants.kElevatorSwitchHeight;
            case SCALE_BALANCED:
                System.out.println("FAKE ELEVATOR: Moving nothing to normal scale height");
                return (int)(Constants.kElevatorMaxHeight * .6);
            case SCALE_HIGH:
                System.out.println("FAKE ELEVATOR: Moving nothing to tall scale height");
                return (int)(Constants.kElevatorMaxHeight * .8);
            case SCALE_HIGH_STACKING:
                System.out.println("FAKE ELEVATOR: Moving nothing to ExtraTall scale height");
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
