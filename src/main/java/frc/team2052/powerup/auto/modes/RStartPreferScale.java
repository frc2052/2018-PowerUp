package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;

import java.util.Arrays;

public class RStartPreferScale extends AutoMode {

    @Override
    protected void init() throws AutoModeEndedException {

        //todo: add radomness checker
        if() { //if right scale is ours
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(AutoPaths.RRScale), false))
                //pathing to the right scale
        ));
    }
        else if(){ // if right switch is ours
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(AutoPaths.RRSwitch), false))
                //pathing to the right switch
        ));
    }
         else {
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(AutoPaths.RLScale), false))
                //pathing to the left scale
        ));
    }
}
}
