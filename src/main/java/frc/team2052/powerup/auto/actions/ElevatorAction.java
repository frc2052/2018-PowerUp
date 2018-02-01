package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Elevator;

public class ElevatorAction implements Action{
    @Override
    public void done() {

    }
    public ElevatorAction(Elevator.ElevatorPresetEnum ElevatorState){
        this.ElevatorState = ElevatorState;
    }

    private Elevator.ElevatorPresetEnum ElevatorState;

    @Override
    public boolean isFinished() {
        return Elevator.getInstance().getHeightInchesForPreset(ElevatorState)== Elevator.getInstance().getHeightInches();
    } //Checks to see if the elevator is finished, and if so, gets height

    @Override
    public void start() {
        Elevator.getInstance().setTarget(ElevatorState);
    }

    @Override
    public void update() {

    }
}
