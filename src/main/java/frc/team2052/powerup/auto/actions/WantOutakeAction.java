package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.Pickup;

public class WantOutakeAction implements Action{
//Wants Outtake

    private boolean isDone = false;
    private double startTimeSec = 0;
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        //return Intake.getInstance().getWantOpenOutake() == true;
        return isDone;
    }

    @Override
    public void start() {
        System.out.println("__________________SHOOT_______________________________");
        startTimeSec = Timer.getFPGATimestamp();
    }

    @Override
    public void update() {
        if ((Timer.getFPGATimestamp() - startTimeSec) < 3) {
            Pickup.getInstance().outtake();
        } else {
            Pickup.getInstance().stopped();
            isDone = true;
        }
    }
}
