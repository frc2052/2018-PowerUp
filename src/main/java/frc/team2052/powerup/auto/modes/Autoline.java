package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by KnightKrawler on 1/15/2018.
 */
public class Autoline extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {
        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));

        List<Path.Waypoint> forwardPath = new ArrayList();
        forwardPath.add(new Path.Waypoint(new Translation2d(0, 0), 100)); //todo: remove the not needed points
        forwardPath.add(new Path.Waypoint(new Translation2d(120, 0), 100));
        forwardPath.add(new Path.Waypoint(new Translation2d(120, -50), 100));
        forwardPath.add(new Path.Waypoint(new Translation2d(180, -50), 100));
        //drive past autoline either in left or right start position

        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(forwardPath), false))
                //pathing past autoline
        ));
    }
}
