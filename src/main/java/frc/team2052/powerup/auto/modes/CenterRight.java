package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.actions.*;

import java.util.Arrays;

public class CenterRight extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
    //if right switch is ours
        System.out.println("NO DATA: HEADING TO R SWITCH");
        runAction(new SeriesAction(Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CRSwitch), false), 6) //pathing to left switch
        )))));
    }
}
