package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RStartPreferScaleTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE RIGHT SCALE");

        if(!FieldConfig.isMyScaleLeft()) { //if right scale is ours
            System.out.println("HEADING TO R SCALE");
            if(!FieldConfig.isMySwitchLeft()) {

                actions.addAll(super.rightToRightScale());
                actions.addAll(super.anotherCubeRightScaleToSwitch());
                actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
                runAction(new SeriesAction(actions));

            }else{

                actions.addAll(super.rightToRightScale());
                actions.addAll(super.anotherCubeRightScaleToScale());
                actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
                runAction(new SeriesAction(actions));

            }
        }else if (!FieldConfig.isMySwitchLeft()){

            actions.addAll(super.rightToRightSwitch());
            actions.addAll(super.anotherCubeRightSwitchToSwitch());
            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
            runAction(new SeriesAction(actions));

        }else if(AutoModeSelector.getDisabledAuto() != AutoModeSelector.AutoDisableDefinition.LEFTSCALE){

            actions.addAll(super.rightToLeftScale());
            actions.addAll(Arrays.asList(
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLScale), true), 2)
            ));
            runAction(new SeriesAction(actions));

        } else {

            actions.addAll(super.autoLine());
            runAction(new SeriesAction(actions));
        }
    }
}
