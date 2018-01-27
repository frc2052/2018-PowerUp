package frc.team2052.powerup;

import com.first.team2052.lib.Loopable;
import com.first.team2052.lib.interpolables.InterpolatingDouble;
import com.first.team2052.lib.vec.RigidTransform2d;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

import java.util.Map;

/**
 * Created by KnightKrawler on 1/23/2018.
 */

//Generates current velocity values from looper
public class PositionLoggerLoopable implements Loopable {

    private static PositionLoggerLoopable instance = new PositionLoggerLoopable();

    private PositionLoggerLoopable() {
    }

    public static PositionLoggerLoopable getInstance() {
        return instance;
    }

    @Override
    public void update() { // InterpolatingDouble, RigidTransform2d are how the spline curve saves its position, this just dumps that data to console
        Map.Entry<InterpolatingDouble, RigidTransform2d> latestFieldToVehicle = RobotState.getInstance().getLatestFieldToVehicle();
        System.out.printf("%s %s%n", latestFieldToVehicle.getKey().value, latestFieldToVehicle.getValue());
        System.out.println((DriveTrain.getInstance().getLeftVelocityInchesPerSec() + DriveTrain.getInstance().getRightVelocityInchesPerSec()) / 2.0);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
