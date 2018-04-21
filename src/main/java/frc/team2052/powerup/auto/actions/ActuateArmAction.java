package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;
import frc.team2052.powerup.subsystems.SubsystemFactory;

public class ActuateArmAction implements Action {

    private ArmState wantState;
    private PickupSubsystem pickup = null;
    private boolean isDone = false;

    public ActuateArmAction(ArmState armState){
        this.pickup = SubsystemFactory.getPickup();
        wantState = armState;
    }
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return isDone;
    }

    @Override
    public void start() {
        switch (wantState){
            case OPEN:
                pickup.openIntake(true);
                break;
            case CLOSED:
                pickup.openIntake(false);
                break;
        }
        isDone = true;
    }

    @Override
    public void update() {

    }

    public  enum ArmState{
        OPEN,
        CLOSED
    }
}
