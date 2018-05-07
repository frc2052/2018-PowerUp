package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.FieldConfig;
import frc.team2052.powerup.auto.actions.Action;
import frc.team2052.powerup.auto.actions.MoveArmAction;
import frc.team2052.powerup.auto.actions.SeriesAction;

import java.util.ArrayList;

public class RStartOnlySwitchTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE ONLY RIGHT SWITCH");

        if(!FieldConfig.isMySwitchLeft()) { //if right scale is ours

            actions.addAll(super.rightToRightSwitch());
            actions.addAll(super.anotherCubeRightSwitchToSwitch());
            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
            runAction(new SeriesAction(actions));

        }else {
            actions.addAll(super.autoLine());
            runAction(new SeriesAction(actions));
        }
    }
}
