package frc.team2052.powerup.subsystems.Interfaces;

import frc.team2052.powerup.subsystems.Elevator;

 public interface ElevatorSubsystem {
    void zeroSensor();

    double getHeightInches();

    void setTarget(Elevator.ElevatorPresetEnum posEnum);

    void setCurrentPosAsTarget();

    void setEmergencyHold(boolean isPressed);

    void setEmergencyUp(boolean isPressed);

    boolean getCarriageIsMoving ();
    
    void setElevatorAdjustmentUp(boolean isPressed);

    void setElevatorAdjustmentDown(boolean isPressed);

    int getHeightInchesForPreset(Elevator.ElevatorPresetEnum posEnum);

}
