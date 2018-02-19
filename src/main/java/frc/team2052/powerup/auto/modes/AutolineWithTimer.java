package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.actions.MoveUntilTimeAction;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;
import frc.team2052.powerup.subsystems.drive.DriveSignal;

import java.util.Arrays;

public class AutolineWithTimer extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {
        runAction(new SeriesAction(Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new MoveUntilTimeAction(4.5, new DriveSignal(.2, .2)))));
    }
}
//when starting on left or right side, crosses auto line using a timer