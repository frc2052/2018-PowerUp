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
            System.out.println("length of list: " + actions.size());
            actions.add(new PrintAction("DONE WITH 1 Cube"));
            System.out.println("length of list: " + actions.size());
            actions.addAll(super.anotherCubeCenterLeftSwitch());
            System.out.println("length of list: " + actions.size());
            actions.addAll(super.anotherCubeCenterLeftSwitch());
            System.out.println("length of list: " + actions.size());

            runAction(new SeriesAction(actions));

        }else{
            System.out.println("HEADING TO R SWITCH");
            actions.addAll(super.centerRightSwitch());
            System.out.println("length of list 1: " + actions.size());
            actions.addAll(super.anotherCubeCenterRightSwitch());
            System.out.println("length of list 2: " + actions.size());
            actions.addAll(super.anotherCubeCenterRightSwitch());
            System.out.println("length of list 3: " + actions.size());

            runAction(new SeriesAction(actions));
        }
    }
}
