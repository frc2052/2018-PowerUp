package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.AmpGetter;
import frc.team2052.powerup.subsystems.Elevator;

public class ElevatorAction implements Action{
    private boolean isStuck = false;

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
        if(AmpGetter.getCurrentElevator(3) >= 40){
            //if we get stuck, the elevator will draw too many amps
            //so lets simulate an emergency override button down then up event
            Elevator.getInstance().setEmergencyDown(true);
            Elevator.getInstance().setEmergencyDown(false);
            isStuck = true;
        }

    }

}
