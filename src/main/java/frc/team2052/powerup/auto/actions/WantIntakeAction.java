package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Pickup;

public class WantIntakeAction implements Action{
//Wants intake
    
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        //return Intake.getInstance().getWantOpenIntake() == true;
        return true;
    }

    @Override
    public void start() {
        //Intake.getInstance().setWantOpenIntake();
        Pickup.getInstance().intake();
    }

    @Override
    public void update() {

    }
}
