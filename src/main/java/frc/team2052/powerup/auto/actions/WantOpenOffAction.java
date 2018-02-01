package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Intake;

public class WantOpenOffAction implements Action {
//Sets intake to open with motors off in auto mode.
    
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return Intake.getInstance().getWantOpenOff() == true;
    }

    @Override
    public void start() {
        Intake.getInstance().setWantOpenOff();

    }

    @Override
    public void update() {

    }
}
