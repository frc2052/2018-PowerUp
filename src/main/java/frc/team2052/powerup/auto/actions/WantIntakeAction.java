package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.AmpGetter;
import frc.team2052.powerup.subsystems.Pickup;

public class WantIntakeAction implements Action {
    //Wants intake
    private AmpGetter amps;
    private boolean isDone = false;

    public WantIntakeAction() {
        amps = AmpGetter.getInstance();


    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        //return Intake.getInstance().getWantOpenIntake() == true;
        return isDone;
    }

    @Override
    public void start() {
        //Intake.getInstance().setWantOpenIntake();
        Pickup.getInstance().intake();
    }

    @Override
    public void update() {
        if (amps.getCurrentIntake1(0) >= 30 || amps.getCurrentIntake2(2) >= 30) {
            isDone = true;
        }

    }
}