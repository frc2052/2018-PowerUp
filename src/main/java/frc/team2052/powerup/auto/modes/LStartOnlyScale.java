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

public class LStartOnlyScale extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        //todo: add radomness checker
        if() { //if left scale is ours
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(AutoPaths.LLScale), false))
        //pathing to left scale
            ));
        }
        else {
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.LRScale), false))
            //pathing to right scale
            ));
        }
    }
}
