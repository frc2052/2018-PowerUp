package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;

public class CenterTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE CENTER");

        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO L SWITCH");
            actions.addAll(super.centerLeftSwitch());
            actions.add(new PrintAction("DONE WITH 1 Cube"));
            actions.addAll(super.anotherCubeCenterLeftSwitch());
            actions.add(new PickupAction(PickupAction.PickupStateEnum.RESETCUBEPICKUPTIMEOUT, 4));

            runAction(new SeriesAction(actions));

        }else{
            System.out.println("HEADING TO R SWITCH");
            actions.addAll(super.centerRightSwitch());
            actions.addAll(super.anotherCubeCenterRightSwitch());
            actions.add(new PickupAction(PickupAction.PickupStateEnum.RESETCUBEPICKUPTIMEOUT, 4));

            runAction(new SeriesAction(actions));
        }
    }
}
