package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.drive.DriveTrain;

//Keeps robot from moving forward or backward until correct angle is reached per the gyro
public class WaitUntilAngle implements Action {
    private final double angle;

    public WaitUntilAngle(double angle){
        this.angle = angle;
    }

    @Override
    public void done() {
    }

    @Override
    public boolean isFinished() {
        return Math.abs(DriveTrain.getInstance().getGyroAngleDegrees() - angle) < 1.5; 
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }
}
