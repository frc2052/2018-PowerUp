package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.RobotState;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisionPathTest extends AutoMode {


    @Override
    protected  void init() throws AutoModeEndedException {


        System.out.println("RUNNING VISION PATH TEST");

        runAction(new SeriesAction(Arrays.asList(
                new TrackRobotPosAction(TrackRobotPosAction.TrackRobotEnum.START),
                new VisionCubeAction(),
                new TrackRobotPosAction(TrackRobotPosAction.TrackRobotEnum.END),
                new TimeoutAction(new FollowPathAction(new Path(TrackRobotPosAction.ReverseVisionPath), true), 6),
                new TrackRobotPosAction(TrackRobotPosAction.TrackRobotEnum.RESET)
        )));
    }
}
