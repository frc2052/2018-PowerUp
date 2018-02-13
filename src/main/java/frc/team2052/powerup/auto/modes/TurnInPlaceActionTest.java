package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.TurnInPlaceAction;

import java.util.Arrays;


public class TurnInPlaceActionTest extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
        runAction(new SeriesAction(Arrays.asList(new TurnInPlaceAction(60.0))));
    }
}
