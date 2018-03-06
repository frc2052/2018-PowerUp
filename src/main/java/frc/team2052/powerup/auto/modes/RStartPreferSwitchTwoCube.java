package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RStartPreferSwitchTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE RIGHT SWITCH");

        if(!FieldConfig.isMySwitchLeft()) { //if right scale is ours

            System.out.println("HEADING TO R SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RRSwitch), false), 8),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRRSwitch), true), 8),
                    new VisionCubeAction(),
                    new PrintAction("Finished Vision"),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(220,  -50)), 6),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(200,  -50)), 6)
            )));
            if(!FieldConfig.isMyScaleLeft()) {

                System.out.println("HEADING TO R SCALE");
                System.out.println("SECOND CUBE WILL GO TO SWITCH");
                runAction(new SeriesAction(Arrays.asList(
                        new WaitAction(AutoModeSelector.getWaitTime()),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RRScale), false), 10),
                        new WaitAction(1.5),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRRScale), true), 6),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RScaleToRSwitchCube), false), 6),
                        new VisionCubeAction(),
                        new PrintAction("Finished Vision"),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(220, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() -20)), 6),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(200, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() -20)), 6)
                )));
            }else{
                System.out.println("SECOND CUBE WILL GO TO SCALE");
                runAction(new SeriesAction(Arrays.asList(
                        new WaitAction(AutoModeSelector.getWaitTime()),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RRScale), false), 10),
                        new WaitAction(1.5),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRRScale), true), 6),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RScaleToRSwitchCube), false), 6),
                        new VisionCubeAction(),
                        new PrintAction("Finished Vision"),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getX() - 30, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() + 30)), 6),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position)), 6)
                )));
            }
        }else{

        }
    }
}
