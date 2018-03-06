package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class VisionTest extends AutoMode {

    @Override
    protected  void init() throws AutoModeEndedException {
        System.out.println("RUNNING VISION TEST");
        runAction(new SeriesAction(Arrays.asList(
                new VisionCubeAction()
        )));
    }
}
