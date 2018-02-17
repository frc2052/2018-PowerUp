package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class Center extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        double waitTime = AutoModeSelector.SelectedWaitTime;
        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            runAction(new SeriesAction(Arrays.asList(
                                    new ParallelAction(Arrays.asList(
                                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CLSwitch), false), 8), //pathing to left switch
                                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                                new LowerPickupAction())))), //lowers pickup to position pointing out
                                    new WantOutakeAction()//pushes cube out
            )));
        }
        else { //if right switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CRSwitch), false), 8), //pathing to left switch
                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                    new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                    new LowerPickupAction())))), //lowers pickup to position pointing out
                    new WantOutakeAction()//pushes cube out
            )));
        }
    }
}
