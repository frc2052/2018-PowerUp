package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.Constants;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class TurnInPlaceAction implements Action {

    double error; //the angle the robot is off
    double angle; //the angle the robot is currently
    double target; //the angle we want to go to
    double integral;
    double piOutput;
    double p = 1.0/120;
    double i = 1.0/120;

    public TurnInPlaceAction(double targetDegrees){
        this.target = targetDegrees;
    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        if (error <.5){ //if the robot is .5 degrees or less from the target position then finish, if not keep turning
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        angle = Math.abs(DriveTrain.getInstance().getGyroAngleDegrees()%360); //angle communicates with the gyro to find the current angle
        error = target - angle; //setting the error angle to equal the difference of the target angle and current angle
        integral = integral + error * Constants.kControlLoopPeriod;
        piOutput = p *error + i * integral;
        System.out.println("PI Output; " + piOutput/10 + "  ERROR: " + error);
        DriveTrain.getInstance().turnInPlace( -piOutput/10,piOutput/10); //setting the speeds for the left and right wheels for turning
    }
}
