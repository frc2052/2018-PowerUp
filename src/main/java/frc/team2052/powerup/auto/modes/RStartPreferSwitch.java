package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.ArrayList;
import java.util.Arrays;

public class RStartPreferSwitch extends AutoMode {

    @Override
    protected void init() throws AutoModeEndedException {

        ArrayList<Action> actions = new ArrayList<>();

        System.out.println("RUNNING RR SWITCH");
        if(!FieldConfig.isMySwitchLeft()) { //if right switch is ours

            actions.addAll(super.rightToRightSwitch());
            runAction(new SeriesAction(actions));

        } else if(!FieldConfig.isMyScaleLeft()){ // if right scale is ours

            actions.addAll(super.rightToRightScale());
            actions.addAll(Arrays.asList(
                    new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                    new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRScale), true), 2)
            ));
            runAction(new SeriesAction(actions));

        } else if(AutoModeSelector.getDisabledAuto() != AutoModeSelector.AutoDisableDefinition.LEFTSCALE){

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
