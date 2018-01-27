package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;
import frc.team2052.powerup.auto.actions.WantOpenOutakeAction;

import java.util.Arrays;

public class RStartPreferScale extends AutoMode {

    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(FieldConfig.isMyScaleLeft() == false) { //if right scale is ours
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(AutoPaths.RRScale), false),//pathing to the right scale
                new WantOpenOutakeAction() //pushes cube out
                )));

    }
        else if(FieldConfig.isMySwitchLeft() == false){ // if right switch is ours
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(AutoPaths.RRSwitch), false),//pathing to the right switch
                new WantOpenOutakeAction()
                )));

    }
         else {
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(AutoPaths.RLScale), false),//pathing to the left scale
                new WantOpenOutakeAction()
                )));
    }
    }
}
