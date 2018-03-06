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

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE CENTER");

        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO L SWITCH");
            actions.add(new WaitAction(AutoModeSelector.getWaitTime()));
            actions.addAll(super.rightSwitch());
            actions.addAll(super.anotherCubeLeftSwitch());
            actions.addAll(super.anotherCubeLeftSwitch());

            runAction(new SeriesAction(actions));

        }else{
            System.out.println("HEADING TO R SWITCH");
            actions.add(new WaitAction(AutoModeSelector.getWaitTime()));
            actions.addAll(super.rightSwitch());
            actions.addAll(super.anotherCubeRightSwitch());
            actions.addAll(super.anotherCubeRightSwitch());

            runAction(new SeriesAction(actions));
        }
    }
}
