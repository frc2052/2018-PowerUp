package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.ArrayList;
import java.util.Arrays;

public class LstartRSwitch extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING LEFT START RIGHT SWITCH");
        if(!FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO R SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new ParallelAction(Arrays.asList(
                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LRSwitch), false), 15), //pathing to the right switch
                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                    new  ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH), //Elevator raises to place on balanced scale
                                    new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))),
                    new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE), //pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        } else { //if right switch is ours
            System.out.println("HEADING TO L SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new ParallelAction(Arrays.asList(
                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LLSwitch), false), 8), //pathing to the right switch
                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                    new  ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH), //Elevator raises to place on balanced scale
                                    new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))),
                    new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE), //pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        }
    }
}
