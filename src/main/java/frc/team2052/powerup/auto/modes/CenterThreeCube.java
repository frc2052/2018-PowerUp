package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;

public class CenterThreeCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE CENTER");

        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO L SWITCH");
            actions.addAll(super.centerRightSwitch());
            actions.addAll(super.anotherCubeCenterLeftSwitch());
            actions.addAll(super.anotherCubeCenterLeftSwitch());

            runAction(new SeriesAction(actions));

        }else{
            System.out.println("HEADING TO R SWITCH");
            actions.addAll(super.centerRightSwitch());
            actions.addAll(super.anotherCubeCenterRightSwitch());
            actions.addAll(super.anotherCubeCenterRightSwitch());

            runAction(new SeriesAction(actions));
        }
    }
}
