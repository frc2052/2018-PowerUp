package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoTest extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException {

        List<Path.Waypoint> TestPath = new ArrayList();
        TestPath.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        TestPath.add(new Path.Waypoint(new Translation2d(12, 0), 40)); //moving forward
        TestPath.add(new Path.Waypoint(new Translation2d(12, 12), 40)); //moving right
        TestPath.add(new Path.Waypoint(new Translation2d(12, 0), 40)); //moving left

        List<Path.Waypoint> TestPathBack = new ArrayList();
        TestPath.add(new Path.Waypoint(new Translation2d(0, 0), 40));
        TestPath.add(new Path.Waypoint(new Translation2d(12, 0), 40)); //moving backward

        new SeriesAction(Arrays.asList(
                new FollowPathAction(new Path(TestPath), false), //moving forward, left, right
                new FollowPathAction(new Path(TestPathBack), true), //moving backward

                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Going to each state for elevator
                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_BALANCED),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP),

                new WantClosedAction(), //pickup closed
                new WantOpenOffAction(), // pickup open and off
                new WantOpenIntakeAction(), //pickup open and intaking
                new WantOpenOutakeAction() //pickup open and outaking
        ));

    }
}
