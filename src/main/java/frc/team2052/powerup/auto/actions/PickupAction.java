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
    private double delayTime;

    public PickupAction(PickupStateEnum state){
        this.pickup = SubsystemFactory.getPickup();
        this.state = state;
        this.seconds = .7; //magic number for default seconds
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
                startTimeSec = Timer.getFPGATimestamp();
                delayTime = Timer.getFPGATimestamp();
                break;
            case TIMEDINTAKE:
                startTimeSec = Timer.getFPGATimestamp();
                break;
            case INTAKETILLCUBED:
                this.pickup.openIntake(true);
                break;
        }
    }

    @Override
    public void update() {
        System.out.println("INTAKE UPDATE LOOP");
        switch (state) {
            case TIMEDOUTTAKE:
                if(Timer.getFPGATimestamp() - delayTime > .5) {
                    if ((Timer.getFPGATimestamp() - startTimeSec) < seconds + .5) {
                        this.pickup.autoOuttake();
                    } else {
                        this.pickup.stopped();
                        isDone = true;
                    }
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
                    this.pickup.openIntake(false);
                    System.out.println("I AM DONE WITH INTAKING IN AUTO AHHHHHHH");
                    isDone = true;
                }else{
                    this.pickup.intake();
                    this.pickup.openIntake(true);
                    System.out.println("CURRENTLY INTAKING TILL CUBED");
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
