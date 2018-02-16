package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Pickup;

public class WantOffAction implements Action {
//Sets intake off
    
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start() {
        Pickup.getInstance().stopped();
    }

    @Override
    public void update() {

    }
}
