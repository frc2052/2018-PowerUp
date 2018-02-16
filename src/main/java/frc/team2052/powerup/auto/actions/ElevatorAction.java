package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.AmpGetter;
import frc.team2052.powerup.subsystems.Elevator;

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
        int target = Elevator.getInstance().getHeightInchesForPreset(ElevatorState);
        double current = Elevator.getInstance().getHeightInches();
        System.out.println("ELEVATOR DELTA: " + (target - current) + "+++++++++++++++++");

        return isStuck || (target - 1 < current && target + 1 > current);
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
