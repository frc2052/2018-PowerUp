package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;

public class Center extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
        ArrayList<Action> actions = new ArrayList<>();
        System.out.println("RUNNING CENTER");
        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO L SWITCH");
            actions.add(new WaitAction(AutoModeSelector.getWaitTime()));
            actions.addAll(super.centerLeftSwitch());
            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));

            runAction(new SeriesAction(actions));
        }
        else { //if right switch is ours
            System.out.println("HEADING TO R SWITCH");
            actions.add(new WaitAction(AutoModeSelector.getWaitTime()));
            actions.addAll(super.centerRightSwitch());
            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));

            runAction(new SeriesAction(actions));
        }
    }
}
