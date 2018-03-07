package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;
import frc.team2052.powerup.subsystems.SubsystemFactory;

public class PickupAction implements Action {

    private boolean isDone = false;
    private PickupStateEnum state;
    private double startTimeSec = 0;
    private double seconds;
    private PickupSubsystem pickup = null;

    public PickupAction(PickupStateEnum state){
        this.pickup = SubsystemFactory.getPickup();
        this.state = state;
        this.seconds = 1; //magic number for default seconds
    }
    
    /**
     * 
     * @param state 
     * @param waitTimeOverRide Only for Intake/Outtake
     */
    public PickupAction(PickupStateEnum state, double waitTimeOverRide){
        this.pickup = SubsystemFactory.getPickup();
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
                this.pickup.stopped();
                break;
            case RESETCUBEPICKUPTIMEOUT:
                this.pickup.ResetCubePickupTimeoutSeconds(seconds);
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
                if ((Timer.getFPGATimestamp() - startTimeSec) < seconds) {
                    this.pickup.outtake();
                } else {
                    this.pickup.stopped();
                    isDone = true;
                }
                break;
            case TIMEDINTAKE:
                if ((Timer.getFPGATimestamp() - startTimeSec) < seconds) {
                    this.pickup.intake();
                } else {
                    this.pickup.stopped();
                    isDone = true;
                }
                break;
            case INTAKETILLCUBED:
                if (this.pickup.isCubePickedUp()) {
                    this.pickup.stopped();
                    isDone = true;
                }else{
                    this.pickup.intake();
                }
                break;
            case RESETCUBEPICKUPTIMEOUT:
                isDone = true;
                break;
        }
    }

    public enum PickupStateEnum {
        OFF,
        INTAKETILLCUBED,
        TIMEDOUTTAKE,
        TIMEDINTAKE,
        RESETCUBEPICKUPTIMEOUT
    }
}
