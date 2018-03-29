package frc.team2052.powerup.auto;

import com.first.team2052.lib.path.Path;
import com.first.team2052.lib.vec.Translation2d;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;
import java.util.List;

public abstract class AutoMode extends AutoModeBase {

    protected List<Action> autoLine(){
        System.out.println("HEADING TO AUTO LINE");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new FollowPathAction(new Path(AutoPaths.AutoLine), false)
        );
    }
    protected List<Action> centerLeftSwitch(){
        System.out.println("HEADING TO CENTER LEFT SWITCH");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.CLSwitch), false), 6), //pathing to left switch
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE)//pushes cube out
        );
    }

    protected List<Action> centerRightSwitch(){
        System.out.println("HEADING TO CENTER RIGHT SWITCH");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.CRSwitch), false), 6), //pathing to right switch
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE)//pushes cube out
        );
    }

//    protected List<Action> anotherCubeCenterLeftSwitch(){
//        System.out.println("EATING ANOTHER CUBE AT LEFT SWITCH");
//        return Arrays.asList(
//                new PrintAction("STARTING ANOTHER CUBE"),
//                new ParallelAction(Arrays.asList(
//                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLSwitch), true), 6),
//                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("LowerElevator"),
//                                new PrintAction("Starting Lowering Elevator"),
//                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))))), //lowers pickup to position pointing out
//                new PrintAction("Starting vision"),
//                new ParallelAction(Arrays.asList(
//                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
//                        new VisionCubeAction())),
//                new PickupAction(PickupAction.PickupStateEnum.OFF),
//                new PrintAction("Finished Vision"),
//                 new ParallelAction(Arrays.asList(
//                         new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(50, -60)), 6),
//                         new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH))),
//                new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CLSwitch.get(AutoPaths.CLSwitch.size() - 1).position)), 6),
//                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),//pushes cube out
//                new PrintAction("Dropped off cube")
//        );
//    }

    protected List<Action> anotherCubeCenterLeftSwitch(){
        System.out.println("ANOTHER RIGHT SWITCH CUBE IS YUMMY");
        return Arrays.asList(
                new PrintAction("STARTING ANOTHER CUBE"),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.ReverseLSwitch), true), 6),
                        new PrintAction("In parrallel action"),
                        new SeriesAction(Arrays.asList(
                                new WaitAction(.5),
                                new PrintAction("starting Lowering Elevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP),
                                new PrintAction("done Lowering Elevator")))
                )), //lowers pickup to position pointing out
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CLSwitch.get(AutoPaths.CLSwitch.size() - 1 ).position.getX() - 25, AutoPaths.CLSwitch.get(AutoPaths.CLSwitch.size() - 1).position.getY())), 3),
                new PrintAction("Starting vision"),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(50, -70)), 6),
                new PrintAction("STARTING BACK TO SWITCH"),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CLSwitch.get(AutoPaths.CLSwitch.size() - 1).position)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),
                new PrintAction("Dropped off cube."),
                new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.Reverse2LSwitch), true), 6)
        );
    }

    protected List<Action> anotherCubeCenterRightSwitch(){
        System.out.println("ANOTHER RIGHT SWITCH CUBE IS YUMMY");
        return Arrays.asList(
                new PrintAction("STARTING ANOTHER CUBE"),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.ReverseRSwitch), true), 6),
                        new PrintAction("In parrallel action"),
                        new SeriesAction(Arrays.asList(
                                new WaitAction(.5),
                                new PrintAction("starting Lowering Elevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP),
                                new PrintAction("done Lowering Elevator")))
                )), //lowers pickup to position pointing out
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CRSwitch.get(AutoPaths.CRSwitch.size() - 1 ).position.getX() - 25, AutoPaths.CRSwitch.get(AutoPaths.CRSwitch.size() - 1).position.getY())), 3),
                new PrintAction("Starting vision"),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(50, 56)), 6),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                new PrintAction("STARTING BACK TO SWITCH"),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.CRSwitch.get(AutoPaths.CRSwitch.size() - 1).position)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),
                new PrintAction("Dropped off cube.")
        );
    }

    protected List<Action> rightToRightSwitch(){
        System.out.println("HEADING RIGHT SWITCH");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.RRSwitch), false),8), //pathing to the right switch
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new  ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH), //Elevator raises to place on switch
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE) //pushes cube out
        );
    }

    protected List<Action> rightToRightScale(){
        System.out.println("HEADING TO RIGHT SCALE");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.RRScale), false), 10), //pathing to the right scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Elevator raises to place on balanced scale
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE) //pushes cube out
        );
    }

    protected List<Action> rightToLeftScale() {
        System.out.println("HEADING OVER TO LEFT SCALE");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.RLScale), false), 10), //pathing to the left scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Elevator raises to place on balanced scale
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))),
                new WaitAction(.5),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE), //pushes cube out
                new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.ReverseRLScale), true), 5)
        );
    }

    protected List<Action> anotherCubeRightSwitchToSwitch(){
        System.out.println("SWITCH: PUT ANOTHER CUBE ON RIGHT SWITCH");
        return Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRRSwitch), true), 8),
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("LowerElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))))),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(193,  -45)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE)
        );
    }

    protected List<Action> anotherCubeRightSwitchToScale(){
        System.out.println("SWITCH: PUT ANOTHER CUBE ON RIGHT SWITCH");
        return Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRRSwitch), true), 8),
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("LowerElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))))),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getX() - 40, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() + 15)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING))),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.RRScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 0, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() - 10)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRScale), true), 6)
        );
    }

    protected List<Action> anotherCubeRightScaleToScale(){
        System.out.println("ANOTHER CUBE ON RIGHT SCALE");
        return Arrays.asList(
                new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRRScale), true), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RScaleToRSwitchCube), false), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getX() - 40, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() + 20)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING))),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRScale), true), 6)
        );
    }

    protected List<Action> anotherCubeRightScaleToSwitch(){
        System.out.println("ANOTHER CUBE ON RIGHT SWITCH");
        return Arrays.asList(
                new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseRRScale), true), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.RScaleToRSwitchCube), false), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(220, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() - 50)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH))),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(200, AutoPaths.RRScale.get(AutoPaths.RRScale.size() - 1).position.getY() - 50)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE)
        );
    }

    protected List<Action> leftToLeftSwitch(){
        System.out.println("HEADING TO LEFT SWITCH");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.LLSwitch), false), 8), //pathing to the left switch
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new  ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH), //Elevator raises to place on switch
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE) //pushes cube out
        );
    }

    protected List<Action> leftToLeftScale(){
        System.out.println("HEADING TO LEFT SCALE");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeOutOrHaltedDriveAction(new FollowPathAction(new Path(AutoPaths.LLScale), false), 10), //pathing to the left scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Elevator raises to place on balanced scale
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))), //lowers pickup to position pointing out
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE) //pushes cube out
        );
    }

    protected List<Action> leftToRightScale(){
        System.out.println("HEADING OVER TO RIGHT SCALE");
        return Arrays.asList(
                new WaitAction(AutoModeSelector.getWaitTime()),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LRScale), false), 10), //pathing to the right scale
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("RaiseElevator"),
                                new  ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING), //Elevator raises to place on balanced scale
                                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN))))),
                new WaitAction(.5),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE), //pushes cube out
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLRScale), true), 5)

        );
    }

    protected List<Action> anotherCubeLeftSwitchToSwitch(){
        System.out.println("ANOTHER CUBE TO LEFT SWITCH");
        return Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLSwitch), true), 8),
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("LowerElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))))),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(193,  45)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE)
        );
    }

    protected List<Action> anotherCubeLeftSwitchToScale(){
        System.out.println("ANOTHER CUBE TO LEFT SCALE");
        return Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLSwitch), true), 8),
                        new SeriesAction(Arrays.asList(new WaitForPathMarkerAction("LowerElevator"),
                                new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))))),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 40, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() - 15)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING))),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 0, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() + 10)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLScale), true), 6)
        );
    }

    protected List<Action> anotherCubeLeftScaleToSwitch(){
        System.out.println("ANOTHER CUBE TO SWITCH");
        return Arrays.asList(
                new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLScale), true), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LScaleToLSwitchCube), false), 6),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(220, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() + 50)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SWITCH))),
                 new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(200, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() + 50)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE)
        );
    }
    protected List<Action> anotherCubeLeftScaleToScale(){
        System.out.println("ANOTHER TO LEFT SCALE");
        return Arrays.asList(
                new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLScale), true), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.LScaleToLSwitchCube), false), 6),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 40, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() - 20)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING))),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 0, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() + 10)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLScale), true), 6)
                );
    }

    protected List<Action> anotherCubeLeftScaleToScaleTURNING(){
        System.out.println("ANOTHER TO LEFT SCALE");
        return Arrays.asList(
                new MoveArmAction(MoveArmAction.ArmPositionEnum.START),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLLScale), true), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.PICKUP))),
                new MoveArmAction(MoveArmAction.ArmPositionEnum.DOWN),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 90),
                new ParallelAction(Arrays.asList(
                        new PickupAction(PickupAction.PickupStateEnum.INTAKETILLCUBED),
                        new VisionCubeAction())),
                new PrintAction("Finished Vision"),
                new ParallelAction(Arrays.asList(
                        new TimeoutAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, true, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 40, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() - 20)), 6),
                        new ElevatorAction(Elevator.ElevatorPresetEnum.SCALE_HIGH_STACKING))),
                new TimeOutOrHaltedDriveAction(new FollowDynamicPathAction(FollowDynamicPathAction.PathMode.RUNPATHTOTARGET, false, new Translation2d(AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getX() - 0, AutoPaths.LLScale.get(AutoPaths.LLScale.size() - 1).position.getY() + 10)), 6),
                new PickupAction(PickupAction.PickupStateEnum.TIMEDOUTTAKE),
                new TimeoutAction(new FollowPathAction(new Path(AutoPaths.ReverseLScale), true), 6)
        );
    }
}