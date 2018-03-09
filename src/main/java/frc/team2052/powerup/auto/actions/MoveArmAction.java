package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.SubsystemFactory;

public class MoveArmAction implements Action{
    private boolean isDone = false;
    private ArmPositionEnum armPosition;
    private double startTime;

    public MoveArmAction(ArmPositionEnum armPosition){
        this.armPosition = armPosition;
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
        switch (armPosition){
            case START:
                SubsystemFactory.getPickup().pickupPositionStartingConfig();
                isDone = true;
                break;
            case UP:
                SubsystemFactory.getPickup().pickupPositionRaised();
                isDone = true;
                break;
            case DOWN:
                SubsystemFactory.getPickup().pickupPositionDown();
                startTime = Timer.getFPGATimestamp();
                break;
        }

    }

    @Override
    public void update() {
        if (Timer.getFPGATimestamp() - startTime> 1){
            isDone = true;
        }
    }

    public enum ArmPositionEnum {
        START,
        UP,
        DOWN;
    }
}
