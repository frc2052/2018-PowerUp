package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.Arrays;

public class AutoLine extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
        System.out.println("RUNNING AUTO LINE");
        runAction(new SeriesAction(Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new FollowPathAction(new Path(AutoPaths.AutoLine), false))));
    }
}
