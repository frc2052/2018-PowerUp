package frc.team2052.powerup.subsystems.Fakes;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;

public class FakePickup implements PickupSubsystem {
    private static FakePickup instance = new FakePickup();
    public static FakePickup getInstance() {
        return instance;
    }

    private DigitalInput touchingCubeInput = null;

    private FakePickup(){
        if (touchingCubeInput == null) {
            try {
                touchingCubeInput = new DigitalInput(1);
            }catch (Exception exe){

            }
        }
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

    @Override
    public boolean isCubePickedUp() {
        return !touchingCubeInput.get(); //todo?????? CHECK THIS
    }
}
