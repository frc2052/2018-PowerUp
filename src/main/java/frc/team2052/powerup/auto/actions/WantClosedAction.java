package frc.team2052.powerup.auto.actions;

//Sets Intake to closed in auto mode

public class WantClosedAction implements Action {
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {

        //return Intake.getInstance().getWantClosed() == true;
        return true;
    }

    @Override
    public void start() {
       // Intake.getInstance().setWantClosed();
       // Pickup.getInstance().close();
    }

    @Override
    public void update() {

    }
}
