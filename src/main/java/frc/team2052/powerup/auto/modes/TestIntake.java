package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.actions.MoveArmAction;
import frc.team2052.powerup.auto.actions.PickupAction;
import frc.team2052.powerup.auto.actions.PrintAction;
import frc.team2052.powerup.auto.actions.SeriesAction;

import java.util.Arrays;

/**
 * Created by KnightKrawler on 3/7/2018.
 */
public class TestIntake extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {
        runAction(new SeriesAction(Arrays.asList(
                new PrintAction("TESTING AUTO"),
                new PickupAction(PickupAction.PickupStateEnum.RESETCUBEPICKUPTIMEOUT, 4),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED)
        )));
    }
}
