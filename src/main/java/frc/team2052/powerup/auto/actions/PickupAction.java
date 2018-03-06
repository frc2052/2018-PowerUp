package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.subsystems.Pickup;

public class PickupAction implements Action {

    private boolean isDone = false;
    private PickupStateEnum state;
    private double startTimeSec = 0;
    private double seconds;

    public PickupAction(PickupStateEnum state){
        this.state = state;
        this.seconds = 1; //magic number for default seconds
    }
    
    /**
     * 
     * @param state 
     * @param waitTimeOverRide Only for Intake/Outtake
     */
    public PickupAction(PickupStateEnum state, double waitTimeOverRide){
        this.state = state;
        this.seconds = waitTimeOverRide;
    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        switch (state){
            case OFF:
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
            case INTAKETILLCUBED:

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
                if ((Timer.getFPGATimestamp() - startTimeSec) < seconds) {
                    Pickup.getInstance().outtake();
                } else {
                    Pickup.getInstance().stopped();
                    isDone = true;
                }
                break;
            case INTAKETILLCUBED:
                break;
        }

    }

    public enum PickupStateEnum {
        OFF,
        INTAKETILLCUBED,
        TIMEDOUTTAKE,
        TIMEDINTAKE
    }
}
