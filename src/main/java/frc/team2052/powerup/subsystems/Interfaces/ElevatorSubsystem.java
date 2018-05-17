package frc.team2052.powerup.subsystems.Interfaces;

import com.first.team2052.lib.Loopable;
import frc.team2052.powerup.subsystems.Elevator;

 public interface ElevatorSubsystem extends Loopable {
    void zeroSensor();

    double getHeightInches();

    void setTarget(Elevator.ElevatorPresetEnum posEnum);

    void setCurrentPosAsTarget();

    void setEmergencyDown(boolean isPressed);

    void setEmergencyUp(boolean isPressed);

    boolean getCarriageIsMoving ();
    
    void setElevatorAdjustmentUp(boolean isPressed);

    void setElevatorAdjustmentDown(boolean isPressed);

    int getHeightInchesForPreset(Elevator.ElevatorPresetEnum posEnum);

}
