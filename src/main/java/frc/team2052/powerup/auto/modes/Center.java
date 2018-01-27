package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.ElevatorSwitchOneAction;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;
import frc.team2052.powerup.auto.actions.WantOpenOutakeAction;

import java.util.Arrays;

public class Center extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.CLSwitch), false), //pathing to left switch
                    new ElevatorSwitchOneAction(),//raises elevator to place cube on switch
                    new WantOpenOutakeAction()//pushes cube out
            )));
        }
        else { //if right switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.CRSwitch), false),//pathing to right switch
                    new ElevatorSwitchOneAction(),//raises elevator to place cube on switch
                    new WantOpenOutakeAction()//pushes cube out
            )));
        }
    }
}
