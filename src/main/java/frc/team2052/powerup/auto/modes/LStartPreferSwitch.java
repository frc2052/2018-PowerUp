package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class LStartPreferSwitch extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("RUNNING LL SWITCH");
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new FollowPathAction(new Path(AutoPaths.LLSwitch), false), //pathing to the left switch
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new  ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH))))), //Elevator raises to place on switch
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
        else if(FieldConfig.isMyScaleLeft()){ // if left Scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new FollowPathAction(new Path(AutoPaths.LLScale), false), //pathing to the left scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_BALANCED))))), //Elevator raises to place on balanced scale
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
         else {
            runAction(new SeriesAction(Arrays.asList(
                    new ParallelAction(Arrays.asList(
                        new FollowPathAction(new Path(AutoPaths.LRScale), false), //pathing to the right scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                            new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_BALANCED))))), //Elevator raises to place on balanced scale
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
    }
}

