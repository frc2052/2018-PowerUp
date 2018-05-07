package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.FieldConfig;
import frc.team2052.powerup.auto.actions.Action;
import frc.team2052.powerup.auto.actions.MoveArmAction;
import frc.team2052.powerup.auto.actions.SeriesAction;

import java.util.ArrayList;

public class LStartOnlyScaleTwoCube extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING LL SCALE ONLY");
        if(FieldConfig.isMyScaleLeft()) { //if left scale is ours

            actions.addAll(super.leftToLeftScale());
            actions.addAll(super.anotherCubeLeftScaleToScale());
            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
            runAction(new SeriesAction(actions));

        } else if(AutoModeSelector.getDisabledAuto() != AutoModeSelector.AutoDisableDefinition.RIGHTSCALE){

            actions.addAll(super.leftToRightScale());
            actions.addAll(super.anotherCubeLeftRightScale());
//            actions.addAll(Arrays.asList(
//                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
//                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLRScale), true), 2),
//                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
//            ));
            runAction(new SeriesAction(actions));

        } else {

//            actions.addAll(super.leftToLeftScale());
//            actions.addAll(super.anotherCubeLeftScaleToScale());
//            actions.add(new MoveArmAction(MoveArmAction.ArmPositionEnum.START));
//            runAction(new SeriesAction(actions));

            actions.addAll(super.autoLine());
            runAction(new SeriesAction(actions));
        }
    }
}
