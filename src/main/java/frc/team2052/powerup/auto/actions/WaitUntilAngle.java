package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.constants.DriveConstants;
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
        return Math.abs(DriveTrain.getInstance().getGyroAngleDegrees() - angle) < DriveConstants.kGyroDeadzone;
    } //Defines Gyro Deadzone

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }
}
