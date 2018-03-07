package frc.team2052.powerup.subsystems;

import frc.team2052.powerup.Constants;
import frc.team2052.powerup.subsystems.Fakes.FakeElevator;
import frc.team2052.powerup.subsystems.Fakes.FakePickup;
import frc.team2052.powerup.subsystems.Interfaces.ElevatorSubsystem;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;

public class SubsystemFactory {

    public static ElevatorSubsystem getElevator() {
        CANDeviceFinder finder = new CANDeviceFinder();
        if (finder.isSRXPresent(Constants.kElevatorMotorID)) {
            return Elevator.getInstance();
        }
        else {
            return FakeElevator.getInstance();
        }
    }
    public static PickupSubsystem getPickup() {
        CANDeviceFinder finder = new CANDeviceFinder();
        if(finder.isSRXPresent(Constants.pickupLeftMotorId) || finder.isSRXPresent(Constants.pickupRightMotorId)){
            return Pickup.getInstance();
        }
        else {
            return FakePickup.getInstance();
        }
    }
}
