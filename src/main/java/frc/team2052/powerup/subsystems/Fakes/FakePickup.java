package frc.team2052.powerup.subsystems.Fakes;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;

public class FakePickup implements PickupSubsystem {
    private static FakePickup instance = new FakePickup();
    public static FakePickup getInstance() {
        System.out.println("FAKE PICKUP: Resetting first time check cube.");
        instance.ResetCubePickupTimeoutSeconds(4);
        return instance;

    }
    private static DigitalInput touchingCubeInput = null;

    private double startTime = 0;
    private boolean firstCheckComplete = false;

    private double pickupTimeoutSeconds = 0;
    public void ResetCubePickupTimeoutSeconds(double newTimeout) {
        pickupTimeoutSeconds = newTimeout / 2 ; //cut timeout in half for fake pickup so that it doesn't run into stuff
        firstCheckComplete = false;
    }

    private FakePickup(){
        if (touchingCubeInput == null) {
            try {
                touchingCubeInput = new DigitalInput(1);
            }catch (Exception exe){
                System.out.println("Failed to create Fake touching cube DIO");
            }
        }
    }

    @Override
    public void resetAmpTimer() {

    }

    @Override
    public void intake() {
        System.out.println("Fake Pickup: Intake");
    }

    @Override
    public void outtake() {
        System.out.println("Fake Pickup: Outtake");
    }

    @Override
    public void autoOuttake() {

    }

    @Override
    public void stopped() {
        System.out.println("Fake Pickup: Stop");
    }

    @Override
    public void shoot() {

    }

    @Override
    public void openIntake(boolean open) {

    }

    @Override
    public void pickupPositionDown() {
        System.out.println("Fake Pickup: Arm Down");
    }

    @Override
    public void pickupPositionRaised() {
        System.out.println("Fake Pickup: Arm Raised");
    }

    @Override
    public boolean isPickupRaised() {
        return false;
    }

    @Override
    public void pickupPositionStartingConfig() {
        System.out.println("Fake Pickup: Arm Staring Pos");
    }

    @Override
    public boolean isCubePickedUp() {
        if (!firstCheckComplete) {
            System.out.println("FAKE PICKUP: First check for cube!");
            firstCheckComplete = true;
            startTime = Timer.getFPGATimestamp();
        }
        //just in case the toggle on the front of the test robot stops working, or isn't connected
        //only return true if it has been more than 2 seconds since first check for cube
        boolean failover = Timer.getFPGATimestamp() - pickupTimeoutSeconds > startTime;
        if (touchingCubeInput != null) {
            try {
                return !touchingCubeInput.get();
            } catch (Exception e) {
                System.out.println("ERROR: exception in FakePickup.isCubePickedUp - Defaulting to " + failover + ": " + e.getMessage());
//                e.printStackTrace();
                return failover;
            }
        } else {
            System.out.println("Fake touching cube is null. Defaulting to touching = " + failover);
            return failover;
        }
    }
}
