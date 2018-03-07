package frc.team2052.powerup.subsystems.Interfaces;

    public interface PickupSubsystem {

        void intake();

        void outtake();

        void stopped();

        void pickupPositionDown();

        void pickupPositionRaised();

        void pickupPositionStartingConfig();

        boolean isCubePickedUp();
    }