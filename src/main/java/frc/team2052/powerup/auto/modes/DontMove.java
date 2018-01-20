package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;

import java.util.Arrays;

/**
 * Created by KnightKrawler on 1/15/2018.
 */
public class DontMove extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {
        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
    }
}
