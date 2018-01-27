package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.ElevatorScaleTwoAction;
import frc.team2052.powerup.auto.actions.FollowPathAction;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;
import frc.team2052.powerup.auto.actions.WantOpenOutakeAction;

import java.util.Arrays;

public class LStartOnlyScale extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));

        if(FieldConfig.isMyScaleLeft()) { //if left scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.LLScale), false), //pathing to left scale
                    new ElevatorScaleTwoAction(), //Elevator raises to place on switch
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
        else {
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.LRScale), false),  //pathing to right scale
                    new ElevatorScaleTwoAction(), //Elevator raises to place on balanced scale
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
    }
}
