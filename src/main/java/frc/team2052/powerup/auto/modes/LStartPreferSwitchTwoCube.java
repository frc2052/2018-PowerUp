package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class LStartPreferSwitchTwoCube extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING TWO CUBE PREFER RIGHT SWITCH");

        if(FieldConfig.isMySwitchLeft()) { //if right scale is ours
            System.out.println("HEADING TO L SWITCH");
            if(FieldConfig.isMyScaleLeft()) {

                actions.addAll(super.leftToLeftSwitch());
                actions.addAll(super.anotherCubeLeftSwitchToScale());
                actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
                runAction(new SeriesAction(actions));

            }else{

                actions.addAll(super.leftToLeftSwitch());
                actions.addAll(super.anotherCubeLeftSwitchToSwitch());
                actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
                runAction(new SeriesAction(actions));
            }
        }else if(FieldConfig.isMyScaleLeft()){

            actions.addAll(super.leftToLeftScale());
            actions.addAll(super.anotherCubeLeftScaleToScale());
            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
            runAction(new SeriesAction(actions));

        }else if(AutoModeSelector.getDisabledAuto() != AutoModeSelector.AutoDisableDefinition.RIGHTSCALE){

            actions.addAll(super.leftToRightScale());
            actions.addAll(Arrays.asList(
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRScale), true), 2)
            ));
            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
            runAction(new SeriesAction(actions));

        } else {

            actions.addAll(super.autoLine());
            runAction(new SeriesAction(actions));
        }
    }
}
