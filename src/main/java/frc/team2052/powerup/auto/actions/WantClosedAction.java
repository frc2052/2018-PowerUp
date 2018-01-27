package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Intake;

public class WantClosedAction implements Action {
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return Intake.getInstance().getWantClosed() == true;
    }

    @Override
    public void start() {
        Intake.getInstance().setWantClosed();

    }

    @Override
    public void update() {

    }
}
