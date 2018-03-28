package frc.team2052.powerup.subsystems.Interfaces;

    public interface PickupSubsystem {

        void resetAmpTimer();

        void intake();

        void outtake();

        void stopped();

        void shoot();

        void pickupPositionDown();

        void pickupPositionRaised();

        void pickupPositionStartingConfig();

        boolean isCubePickedUp();

        void ResetCubePickupTimeoutSeconds(double newTimeout);
    }