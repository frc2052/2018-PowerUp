package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class Center extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.CLSwitch), false), //pathing to left switch
                    new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),//raises elevator to place cube on switch
                    new WantOpenOutakeAction()//pushes cube out
            )));
        }
        else { //if right switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.CRSwitch), false),//pathing to right switch
                    new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),//raises elevator to place cube on switch
                    new WantOpenOutakeAction()//pushes cube out
            )));
        }
    }
}
