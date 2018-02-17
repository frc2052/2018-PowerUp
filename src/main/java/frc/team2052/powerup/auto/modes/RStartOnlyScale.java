package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class RStartOnlyScale extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(!FieldConfig.isMyScaleLeft()) { //if right scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RRScale), false), 10), //pathing to right scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH), //Elevator raises to place on balanced scale
                            new LowerPickupAction())))), //lowers pickup to position pointing out
                    new WantOutakeAction() //pushes cube out
            )));
        }
        else {
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RLScale), false), 10), //pathing to left scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH), //Elevator raises to balanced scale
                            new LowerPickupAction())))), //lowers pickup to position pointing out
                    new WantOutakeAction() //pushes cube out
            )));
        }
    }
}
