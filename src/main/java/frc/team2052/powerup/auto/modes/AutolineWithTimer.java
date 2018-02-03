package frc.team2052.powerup.auto.modes;

        import com.first.team2052.lib.path.Path;
        import frc.team2052.powerup.auto.*;
        import frc.team2052.powerup.auto.actions.FollowPathAction;
        import frc.team2052.powerup.auto.actions.MoveUntilTimeAction;
        import frc.team2052.powerup.auto.actions.SeriesAction;
        import frc.team2052.powerup.auto.actions.WaitAction;
        import frc.team2052.powerup.subsystems.drive.DriveSignal;

        import java.util.Arrays;

public class AutolineWithTimer extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {
        runAction(new SeriesAction(Arrays.asList(new WaitAction(AutoModeSelector.SelectedWaitTime))));
        runAction(new SeriesAction(Arrays.asList(new MoveUntilTimeAction(1, new DriveSignal(.2, .2)))));
    }
}
