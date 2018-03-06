package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.Arrays;

public class LStartPreferSwitchTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {


        System.out.println("RUNNING TWO CUBE PREFER RIGHT SWITCH");

        if(FieldConfig.isMySwitchLeft()) { //if right scale is ours
            System.out.println("HEADING TO L SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LLSwitch), false), 8),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLSwitch), true), 8),
                    new VisionCubeAction(),
                    new PrintAction("Finished Vision"),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(220,  50)), 6),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(200,  50)), 6)
            )));
            if(FieldConfig.isMyScaleLeft()) {

            }else{
                System.out.println("HEADING TO L SCALE");
                System.out.println("SECOND CUBE WILL GO TO SWITCH");
                runAction(new SeriesAction(Arrays.asList(
                        new WaitAction(AutoModeSelector.getWaitTime()),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LLScale), false), 10),
                        new WaitAction(1.5),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLScale), true), 6),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LScaleToLSwitchCube), false), 6),
                        new VisionCubeAction(),
                        new PrintAction("Finished Vision"),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(220, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() + 20)), 6),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(200, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() + 20)), 6)
                )));
                System.out.println("SECOND CUBE WILL GO TO SCALE");
                runAction(new SeriesAction(Arrays.asList(
                        new WaitAction(AutoModeSelector.getWaitTime()),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LLScale), false), 10),
                        new WaitAction(1.5),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLScale), true), 6),
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LScaleToLSwitchCube), false), 6),
                        new VisionCubeAction(),
                        new PrintAction("Finished Vision"),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 30, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() - 30)), 6),
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position)), 6)
                )));
            }
        }else{

        }
    }
}
