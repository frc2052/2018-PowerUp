package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.Pickup;

public class PickupAction implements Action {

    private boolean isDone = false;
    private PickupStateEnum state;
    private double startTimeSec = 0;
    private double seconds;

    public PickupAction(PickupStateEnum state){
        this.state = state;
    }

    public PickupAction(PickupStateEnum state, double seconds){
        this.state = state;
        this.seconds = seconds;
    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        switch (state){
            case OFF:
            case INTAKE:
            case OUTTAKE:
                isDone = true;
                break;
        }
        return isDone;
    }

    @Override
    public void start() {
        switch (state){
            case OFF:
                Pickup.getInstance().stopped();
                break;
            case OUTTAKE:
                Pickup.getInstance().outtake();
                break;
            case INTAKE:
                Pickup.getInstance().intake();
                break;
            case TIMEDOUTTAKE:
            case TIMEDINTAKE:
                startTimeSec = Timer.getFPGATimestamp();
                break;
        }


    }

    @Override
    public void update() {
        switch (state) {
            case TIMEDOUTTAKE:
            case TIMEDINTAKE:
                if ((Timer.getFPGATimestamp() - startTimeSec) < 1.5) {
                    Pickup.getInstance().outtake();
                } else {
                    Pickup.getInstance().stopped();
                    isDone = true;
                }
                break;
        }

    }

    public enum PickupStateEnum {
        OFF,
        OUTTAKE,
        INTAKE,
        TIMEDOUTTAKE,
        TIMEDINTAKE
    }
}
