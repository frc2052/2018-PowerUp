package frc.team2052.powerup.subsystems;

import frc.team2052.powerup.subsystems.Interfaces.ElevatorSubsystem;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;

public class SubsystemFactory {

    public static ElevatorSubsystem getElevator() {
//        CANDeviceFinder finder = new CANDeviceFinder();
//        if (finder.isSRXPresent(Constants.kElevatorMotorID)) {
//            System.out.println("Factory real elevator");
            return Elevator.getInstance();
//        }
//        else {
//            System.out.println("Factory fake elevator");
//            return FakeElevator.getInstance();
//        }
    }
    public static PickupSubsystem getPickup() {
//        CANDeviceFinder finder = new CANDeviceFinder();
//        if(finder.isSRXPresent(Constants.pickupLeftMotorId) || finder.isSRXPresent(Constants.pickupRightMotorId)){
//            System.out.println("Factory real pickup");
            return Pickup.getInstance();
//        }
//        else {
//            System.out.println("Factory fake pickup");
//            return FakePickup.getInstance();
//        }
    }
}
