package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Pickup;

public class WantOpenOutakeAction implements Action{
//Sets intake open with motor spinning to pick up cube in auto mode. 
    
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        //return Intake.getInstance().getWantOpenOutake() == true;
        return true;
    }

    @Override
    public void start() {
        //Intake.getInstance().setWantOpenIntake();
        Pickup.getInstance().outtake();
    }

    @Override
    public void update() {

    }
}
