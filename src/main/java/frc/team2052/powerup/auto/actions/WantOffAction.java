package frc.team2052.powerup.auto.actions;

public class WantOffAction implements Action {
//Sets intake off
    
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
    }

    @Override
    public void update() {

    }
}
