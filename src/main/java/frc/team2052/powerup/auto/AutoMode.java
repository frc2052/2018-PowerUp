package frc.team2052.powerup.auto;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;
import java.util.List;

public abstract class AutoMode extends AutoModeBase {

    protected List<Action> leftSwitch(){
        List<Action> actions = Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CLSwitch), false), 6), //pathing to left switch
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new WantOutakeAction()//pushes cube out
                );
        return actions;
    }

    protected List<Action> rightSwitch(){
        List<Action> actions = Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CRSwitch), false), 6), //pathing to left switcha
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new IntakeAction(IntakeAction.IntakeStateEnum.TIMEDOUTTAKE, 1)//pushes cube out
        );
        return actions;
    }

    protected void anotherCubeLeftSwitch(){
        List<Action> actions = Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLSwitch), true), 6),
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("LowerElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP), //lowers pickup to position pointing out
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))),
                new VisionCubeAction(),
                new PrintAction("Finished Vision"),
                new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(50, -60)), 6),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CLSwitch.get(AutoPaths.CLSwitch.size() - 1).position)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new WantOutakeAction()//pushes cube out
        );
    }
}
