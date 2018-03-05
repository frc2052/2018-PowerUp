package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.AutoModeSelector;
import frc.team2052.powerup.auto.AutoPaths;
import frc.team2052.powerup.auto.actions.*;

import java.util.Arrays;

public class TestVelocity extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {
        System.out.println("RUNNING VELOCITY PATH TEST");
        runAction(new SeriesAction(Arrays.asList(
            new DriveVelocityHeadingAction(200,5)
        )));
    }
}
