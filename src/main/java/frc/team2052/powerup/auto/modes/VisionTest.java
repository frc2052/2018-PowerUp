package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.actions.*;

import java.util.Arrays;

public class VisionTest extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {
        System.out.println("RUNNING VISION TEST");
        runAction(new SeriesAction(Arrays.asList(
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction()
                ))
        )));
    }
}
