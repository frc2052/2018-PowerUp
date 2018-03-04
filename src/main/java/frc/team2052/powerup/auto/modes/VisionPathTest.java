package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.RobotState;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisionPathTest extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {


        System.out.println("RUNNING VISION PATH TEST");
        System.out.println("HEADING TO R SWITCH");
        runAction(new SeriesAction(Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CRSwitch), false), 6),
                new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RECORDPOINT),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRSwitch), true), 6),
                new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RECORDPOINT),
                new VisionCubeAction(),
                new PrintAction("Finished Vision"),
                new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(50,46)), 6),

                // new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTORECORDEDPOINT), 6),
//                new TurnInPlaceAction(-RobotState.getInstance().getLatestFieldToVehicle().getValue().getRotation().getDegrees()),
//                new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CRSwitch.get(AutoPaths.CRSwitch.size()-1).position.getX() - 12, AutoPaths.CRSwitch.get(AutoPaths.CRSwitch.size() -1).position.getY())), 6),
                new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CRSwitch.get(AutoPaths.CRSwitch.size()-1).position)), 6)
        )));
    }
}
