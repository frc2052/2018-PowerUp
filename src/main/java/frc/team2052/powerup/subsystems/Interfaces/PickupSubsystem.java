package frc.team2052.powerup.subsystems.Interfaces;

    public interface PickupSubsystem {

        void resetAmpTimer();

        void intake();

        void outtake();

        void mediumOuttake();

        void stopped();

        void shoot();

        void openIntake(boolean open);

        void pickupPositionDown();

        void pickupPositionRaised();

        boolean isPickupRaised();

        void pickupPositionStartingConfig();

        boolean isCubePickedUp();

        void ResetCubePickupTimeoutSeconds(double newTimeout);
    }