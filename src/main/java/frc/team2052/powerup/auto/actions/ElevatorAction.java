package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.AmpGetter;
import frc.team2052.powerup.subsystems.Elevator;

/**
 * Created by KnightKrawler on 1/27/2018.
 */
public class ElevatorAction implements Action{
    private AmpGetter amps;
    private boolean isStuck = false;

    public ElevatorAction() {
        amps = AmpGetter.getInstance();


    }

    @Override
    public void done() {

    }

    public ElevatorAction(Elevator.ElevatorPresetEnum ElevatorState){
        this.ElevatorState = ElevatorState;
    }

    private Elevator.ElevatorPresetEnum ElevatorState;

    @Override
    public boolean isFinished() {
        return isStuck || Elevator.getInstance().getHeightInchesForPreset(ElevatorState)== Elevator.getInstance().getHeightInches();
    }

    @Override
    public void start() {
        Elevator.getInstance().setTarget(ElevatorState);
    }

    @Override
    public void update() {
        if(amps.getCurrentElevator(3) >= 40){
        isStuck = true;
        }

    }

}
