package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.actions.MoveUntilTimeAction;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.WaitAction;
import frc.team2052.powerup.subsystems.drive.DriveSignal;

import java.util.Arrays;

public class AutolineWithTimerCenterLeft extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {
        System.out.println("RUNNING AUTO LINE WITH TIMER LEFT");
        runAction(new SeriesAction(Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new MoveUntilTimeAction(1, new DriveSignal(.2, .2)),
                new MoveUntilTimeAction(1.3, new DriveSignal(.2, 0)),
                new MoveUntilTimeAction(2, new DriveSignal(.2, .2)),
                new MoveUntilTimeAction(1, new DriveSignal(0, .2)),
                new MoveUntilTimeAction(1.8, new DriveSignal(.2, .2)))));
    }
}
//when starting in center, crosses auto line to the left using a timer