package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RStartPreferSwitchTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE RIGHT SWITCH");

        if(!FieldConfig.isMySwitchLeft()) { //if right scale is ours

            if(!FieldConfig.isMyScaleLeft()) {

                actions.addAll(super.rightToRightSwitch());
                actions.addAll(super.anotherCubeRightSwitchToScale());
                actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
                runAction(new SeriesAction(actions));

            }else{

                actions.addAll(super.rightToRightSwitch());
                actions.addAll(super.anotherCubeRightSwitchToSwitch());
                actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
                runAction(new SeriesAction(actions));

            }
        }else if (!FieldConfig.isMyScaleLeft()){

            actions.addAll(super.rightToRightScale());
            actions.addAll(super.anotherCubeRightScaleToScale());
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
