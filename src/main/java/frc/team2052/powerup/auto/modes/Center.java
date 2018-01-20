package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lancelot on 1/19/2018.
 */
public class Center extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        //todo: add radomness checker
        if() { //if left switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.CLSwitch), false))
            //pathing to left switch
            ));
        }
        else { //if right switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.CRSwitch), false))
            //pathing to right switch
            ));
        }
    }
}
