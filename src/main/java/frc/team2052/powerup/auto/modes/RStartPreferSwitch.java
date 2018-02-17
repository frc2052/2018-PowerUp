package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class RStartPreferSwitch extends AutoMode {

    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(!FieldConfig.isMySwitchLeft()) { //if right switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RRSwitch), false), 8), //pathing to the right switch
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new  ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH), //Elevator raises to place on balanced scale
                            new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))),
                    new WantOutakeAction() //pushes cube out
            )));
        }
        else if(!FieldConfig.isMyScaleLeft()){ // if right scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RRScale), false),10), //pathing to the right scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH), //Elevator raises to place on balanced scale
                            new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                    new WantOutakeAction(), //pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        }
         else {
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RLScale), false),10), //pathing to the left scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH), //Elevator raises to place on balanced scale
                            new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                    new WantOutakeAction(), //pushes cube out
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            )));
        }
    }
}
