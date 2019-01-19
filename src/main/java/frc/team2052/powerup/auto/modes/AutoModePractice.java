package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class AutoModePractice extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
        runAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new FollowPathAction(new Path(AutoPaths.LLSwitch),false),
                        new SeriesAction(Arrays.asList(
                                new WaitForPathMarkerAction("RaiseElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH))
                        ))),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE,5),
                new FollowPathAction(new Path(AutoPaths.LLSwitch),true),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                new WaitForPathMarkerAction("RaiseElevator"),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_BALANCED),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE,10)
        )));
    }
}
