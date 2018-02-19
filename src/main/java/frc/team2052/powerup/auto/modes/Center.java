package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class Center extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
        System.out.println("RUNNING CENTER");
        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO L SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new ParallelAction(Arrays.asList(
                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CLSwitch), false), 6), //pathing to left switch
                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                    new WantOutakeAction(),//pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        }
        else { //if right switch is ours
            System.out.println("HEADING TO R SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new WaitAction(AutoModeSelector.getWaitTime()),
                    new ParallelAction(Arrays.asList(
                            new TimeoutAction(new FollowPathAction(new Path(AutoPaths.CRSwitch), false), 6), //pathing to left switch
                            new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                    new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                    new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                    new WantOutakeAction(),//pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        }
    }
}
