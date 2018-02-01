package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Intake;

public class wantOpenIntakeAction implements Action{
//Sets intake to open with motors spinning to release cube in auto mode
    
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return Intake.getInstance().getWantOpenIntake() == true;
    }

    @Override
    public void start() {
        Intake.getInstance().setWantOpenIntake();
    }

    @Override
    public void update() {

    }
}
