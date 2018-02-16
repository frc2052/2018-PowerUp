package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Pickup;

public class LowerPickupAction implements Action{
    private boolean isDone = false;

    @Override
    public void done() {
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }

    @Override
    public void start() {
        Pickup.getInstance().pickupPositionDown();
        isDone = true;
    }

    @Override
    public void update() {
    }
}
