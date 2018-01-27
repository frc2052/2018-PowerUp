package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Elevator;

public class ElevatorSwitchOneAction implements Action{
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return Elevator.getInstance().getCurrentPos() == Elevator.ElevatorPosEnum.SWITCH_ONE;
    }

    @Override
    public void start() {
        Elevator.getInstance().setCurrentPos(Elevator.ElevatorPosEnum.SWITCH_ONE);
    }

    @Override
    public void update() {

    }
}
