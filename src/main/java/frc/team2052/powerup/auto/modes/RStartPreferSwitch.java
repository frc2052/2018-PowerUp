package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.Arrays;

public class RStartPreferSwitch extends AutoMode {

    @Override
    protected void init() throws AutoModeEndedException {

        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        if(FieldConfig.isMySwitchLeft() == false) { //if right switch is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.RRSwitch), false), //pathing to the right switch
                    new ElevatorSwitchOneAction(), //Elevator raises to place on balanced scale
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
        else if(FieldConfig.isMyScaleLeft() == false){ // if right scale is ours
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.RRScale), false), //pathing to the right scale
                    new ElevatorScaleTwoAction(), //Elevator raises to place on balanced scale
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
         else {
            runAction(new SeriesAction(Arrays.asList(
                    new FollowPathAction(new Path(AutoPaths.RLScale), false), //pathing to the left scale
                    new ElevatorScaleTwoAction(), //Elevator raises to place on balanced scale
                    new WantOpenOutakeAction() //pushes cube out
            )));
        }
    }
}
