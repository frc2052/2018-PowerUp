package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Pickup;

public class LowerPickupAction implements Action{
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start() {
        Pickup.getInstance().Init();
    }

    @Override
    public void update() {

    }
}
