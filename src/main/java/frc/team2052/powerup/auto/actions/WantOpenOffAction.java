package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Intake;
import frc.team2052.powerup.subsystems.Pickup;

public class WantOpenOffAction implements Action {
//Sets intake to open with motors off in auto mode.
    
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        //return Intake.getInstance().getWantOpenOff() ;
        return true;
    }

    @Override
    public void start() {
        //Intake.getInstance().setWantOpenOff();
        Pickup.getInstance().open();
    }

    @Override
    public void update() {

    }
}
