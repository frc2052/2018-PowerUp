package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.FieldConfig;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;

import java.util.Arrays;

public class RStartPreferSwitch extends AutoMode {

    @Override
    protected void init() throws AutoModeEndedException {


        //todo: add randomness checker
        if(FieldConfig.isMySwitchLeft() == false) { //if right switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.RRSwitch), false))
                    //pathing to the right switch
            ));
        }
        else if(FieldConfig.isMyScaleLeft() == false){ // if right scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.RRScale), false))
                    //pathing to the right scale
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
