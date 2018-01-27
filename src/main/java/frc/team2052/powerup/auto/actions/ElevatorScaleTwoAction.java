package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Elevator;

public class ElevatorScaleTwoAction implements Action {
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return Elevator.getInstance().getCurrentPos() == Elevator.ElevatorPosEnum.SCALE_TWO;
    }

    @Override
    public void start() {
        Elevator.getInstance().setCurrentPos(Elevator.ElevatorPosEnum.SCALE_TWO);
    }

    @Override
    public void update() {

    }
}
