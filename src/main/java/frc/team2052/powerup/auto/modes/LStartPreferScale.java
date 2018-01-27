package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;
import frc.team2052.powerup.auto.actions.WantOpenOutakeAction;

import java.util.Arrays;

public class LStartPreferScale extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(FieldConfig.isMyScaleLeft()) { //if left scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.LLScale), false),//pathing to the left scale
                    new WantOpenOutakeAction() //pushes cube out
                    )));

        }
        else if(FieldConfig.isMySwitchLeft()){ // if left switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.LLSwitch), false),//pathing to the left switch
                    new WantOpenOutakeAction()
                    )));

        }
         else {
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.LRScale), false),//pathing to the right scale
                    new WantOpenOutakeAction()
                    )));
        }
    }
}

