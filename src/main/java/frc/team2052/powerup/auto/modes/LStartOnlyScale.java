package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class LStartOnlyScale extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING LL SCALE ONLY");
        if(FieldConfig.isMyScaleLeft()) { //if left scale is ours

            actions.addAll(super.leftToLeftScale());
            actions.addAll(Arrays.asList(
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLScale), true), 2),
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            ));
            runAction(new SeriesAction(actions));

        } else if(AutoModeSelector.getDisabledAuto() != AutoModeSelector.AutoDisableDefinition.RIGHTSCALE){

            actions.addAll(super.leftToRightScale());
            actions.addAll(Arrays.asList(
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLRScale), true), 2),
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START)
            ));
            runAction(new SeriesAction(actions));

        } else {

            actions.addAll(super.autoLine());
            runAction(new SeriesAction(actions));
        }
    }
}
