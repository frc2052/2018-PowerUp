package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.AmpGetter;
import frc.team2052.powerup.subsystems.Elevator;
import frc.team2052.powerup.subsystems.SubsystemFactory;

public class ElevatorAction implements Action{
    private boolean isStuck = false;

    @Override
    public void done() {
    }

    public ElevatorAction(Elevator.ElevatorPresetEnum ElevatorState){
//        //Add the if statement to avoid breaking lights while testing in caddy shack
//        if (ElevatorState == Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING
//                || ElevatorState == Elevator.ElevatorPresetEnum.SCALE_HIGH
//                || ElevatorState == Elevator.ElevatorPresetEnum.SCALE_BALANCED) {
//            this.ElevatorState = Elevator.ElevatorPresetEnum.SWITCH;
//        } else {
            this.ElevatorState = ElevatorState;
//        }
    }

    private Elevator.ElevatorPresetEnum ElevatorState;

    @Override
    public boolean isFinished() {
        int target = SubsystemFactory.getElevator().getHeightInchesForPreset(ElevatorState);
        double current = SubsystemFactory.getElevator().getHeightInches();

        return isStuck || (target - 2 < current && target + 2 > current);
    }

    @Override
    public void start() {
        SubsystemFactory.getElevator().setTarget(ElevatorState);
    }

    @Override
    public void update() {
//        if(AmpGetter.getCurrentElevator(3) >= 40){
//            //if we get stuck, the elevator will draw too many amps
//            //so lets simulate an emergency override button down then up event
//            Elevator.getInstance().setEmergencyDown(true);
//            Elevator.getInstance().setEmergencyDown(false);
//            isStuck = true;
//        }

    }

}
