package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.RobotState;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CenterTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {


        System.out.println("RUNNING TWO CUBE CENTER");

        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO L SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new ParallelAction(Arrays.asList(
                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CLSwitch), false), 6),
                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                    new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                    new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                    new WantOutakeAction(),//pushes cube out
                    new ParallelAction(Arrays.asList(
                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLSwitch), true), 6),
                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("LowerElevator"),
                                    new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))))), //lowers pickup to position pointing out
                    new VisionCubeAction(),
                    new PrintAction("Finished Vision"),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(50, -60)), 6),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CLSwitch.get(AutoPaths.CLSwitch.size() - 1).position)), 6)
            )));
        }else{
            System.out.println("HEADING TO R SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CRSwitch), false), 6),
                    new WaitAction(1.5),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRSwitch), true), 6),
                    new VisionCubeAction(),
                    new PrintAction("Finished Vision"),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(50, 46)), 6),
                    new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CRSwitch.get(AutoPaths.CRSwitch.size() - 1).position)), 6)
            )));
        }
    }
}
