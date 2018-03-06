package frc.team2052.powerup.subsystems.Fakes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;

public class FakePickup implements PickupSubsystem {
    private static FakePickup instance = new FakePickup();
    public static FakePickup getInstance() {
        return instance;
    }

    @Override
    public void intake() {

    }

    @Override
    public void outtake() {

    }

    @Override
    public void stopped() {

    }

    @Override
    public void pickupPositionDown() {

    }

    @Override
    public void pickupPositionRaised() {

    }

    @Override
    public void pickupPositionStartingConfig() {

    }
}
