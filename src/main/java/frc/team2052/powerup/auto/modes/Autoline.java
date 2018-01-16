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
 * Created by KnightKrawler on 1/15/2018.
 */
public class Autoline extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {

        List<Path.Waypoint> forwardPath = new ArrayList();
        forwardPath.add(new Path.Waypoint(new Translation2d(0, 0), 80));
        forwardPath.add(new Path.Waypoint(new Translation2d(120, 0), 80));

        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(forwardPath), false))
        ));
    }
}
