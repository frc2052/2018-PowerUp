package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class LStartOnlyScale extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));

        if(FieldConfig.isMyScaleLeft()) { //if left scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LLScale), false), 10), //pathing to left scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH), //Elevator raises to place on switch
                            new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                    new WantOutakeAction(),//pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        }
        else {
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LRScale), false), 10),  //pathing to right scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH), //Elevator raises to place on balanced scale
                            new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                    new WantOutakeAction(),//pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        }
    }
}
