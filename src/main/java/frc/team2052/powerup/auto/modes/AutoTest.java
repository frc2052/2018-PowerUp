package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.Constants;
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
        System.out.println("RUNNING AUTO TEST");

//        List<Path.Waypoint> TestPath = new ArrayList();
//        TestPath.add(new Path.Waypoint(new Translation2d(0, 0), 40));
//        TestPath.add(new Path.Waypoint(new Translation2d(12, 0), 40)); //moving forward
//        TestPath.add(new Path.Waypoint(new Translation2d(12, 12), 40)); //moving right
//        TestPath.add(new Path.Waypoint(new Translation2d(12, 0), 40)); //moving left

        List<Path.Waypoint> TestPath = new ArrayList();
        TestPath.add(new Path.Waypoint(new Translation2d(0, 0), Constants.kPathFollowingMaxVel));
        TestPath.add(new Path.Waypoint(new Translation2d(36, 0), Constants.kPathFollowingMaxVel)); //moving backward

        runAction(new SeriesAction(Arrays.asList(
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new WaitAction(2),
                new TimeoutAction(new FollowPathAction(new Path(TestPath), false), 3), //moving forward
                new TimeoutAction(new FollowPathAction(new Path(TestPath), true), 3), //moving backward

                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Going to each state for elevator
                new WaitAction(1),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH),
                new WaitAction(1),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_BALANCED),
                new WaitAction(1),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                new WaitAction(1),
                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP),

                new TimeoutAction(new PickupAction(PickupAction.PickupStateEnum.TIMEDINTAKE, 2), 2), //pickup Intake
                new PickupAction(PickupAction.PickupStateEnum.OFF), //pickup off
                new WaitAction(1),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE, 2), //pickup outtake
                new WaitAction(2),
                new PickupAction(PickupAction.PickupStateEnum.OFF), //pickup off

                new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                new WaitAction(2),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new WaitAction(2),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.UP),
                new WaitAction(2),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN)
        )));

    }
}
