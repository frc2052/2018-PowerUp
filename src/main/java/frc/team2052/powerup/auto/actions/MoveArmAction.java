package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Pickup;
import frc.team2052.powerup.subsystems.SubsystemFactory;

public class MoveArmAction implements Action{
    private boolean isDone = false;
    private ArmPositionEnum armPosition;

    public MoveArmAction(ArmPositionEnum armPosition){
        this.armPosition = armPosition;
    }

    @Override
    public void done() {
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }

    @Override
    public void start() {
        switch (armPosition){
            case START:
                SubsystemFactory.getPickup().pickupPositionStartingConfig();
                break;
            case UP:
                SubsystemFactory.getPickup().pickupPositionRaised();
                break;
            case DOWN:
                SubsystemFactory.getPickup().pickupPositionDown();
                break;
        }
        isDone = true;
    }

    @Override
    public void update() {
    }

    public enum ArmPositionEnum {
        START,
        UP,
        DOWN;
    }
}
