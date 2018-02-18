package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.AmpGetter;
import frc.team2052.powerup.subsystems.Pickup;

public class WantIntakeAction implements Action {
    //Wants intake
    private boolean isDone = false;

    public WantIntakeAction() {

    }

    @Override
    public void done() {
        Pickup.getInstance().stopped();
    }

    @Override
    public boolean isFinished() {
        //return Intake.getInstance().getWantOpenIntake() == true;
        return isDone;
    }

    @Override
    public void start() {
        Pickup.getInstance().intake();
    }

    @Override
    public void update() {
        if (AmpGetter.getCurrentIntake1(0) >= 30 || AmpGetter.getCurrentIntake2(2) >= 30) {
            isDone = true;
        }
    }
}