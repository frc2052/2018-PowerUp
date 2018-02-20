package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class RStartOnlyScale extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
        System.out.println("RUNNING RR SCALE ONLY");
        if(!FieldConfig.isMyScaleLeft()) { //if right scale is ours
            System.out.println("HEADING TO R SCALE");
            runAction(new SeriesAction(Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RRScale), false), 10), //pathing to right scale
                    new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Elevator raises to place on balanced scale
                        new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new WantOutakeAction(), //pushes cube out
                new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRScale), true), 2)
            )));
        } else if(AutoModeSelector.getDisabledAuto() != AutoModeSelector.AutoDisableDefinition.LEFTSCALE){
            System.out.println("HEADING TO L SCALE");
            runAction(new SeriesAction(Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RLScale), false), 10), //pathing to left scale
                    new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                        new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Elevator raises to balanced scale
                        new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new WantOutakeAction(), //pushes cube out
                new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        } else {
            System.out.println("HEADING TO AUTO LINE");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new FollowPathAction(new Path(AutoPaths.AutoLine), false))));
        }
    }
}
