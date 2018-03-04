package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class TurnInPlaceAction implements Action {

    double baseSpeedConstant = .20;
    double baseSpeed; //holds the current base speed
    double error; //the angle the robot is off
    double angle; //the angle the robot is currently
    double target; //the angle we want to go to
    double output; //the output to the wheels
    double P = 1.0; //SpeedCurveMultiplier. this will increase the speed that we start at and increase the acceleration

    private boolean isFinished = false;
    /**
     *
     * @param turnDegrees the change in rotation in degrees
     */

    public TurnInPlaceAction(double turnDegrees){
        this.target = DriveTrain.getInstance().getGyroAngleDegrees() + turnDegrees; //turns a displacemen value to a set degree value
    }

    @Override
    public void done() {
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void start() {
    }

    @Override
    public void update() {
        angle = DriveTrain.getInstance().getGyroAngleDegrees(); //angle communicates with the gyro to find the current angle
        error = target - angle; //setting the error angle to equal the difference of the target angle and current angle

        if (Math.abs(error) < .5){ //if the robot is .5 degrees or less from the target position then finish, if not keep turning
            isFinished= true;
            DriveTrain.getInstance().turnInPlace(0,0); //stops turning
        }
        else {
            if (error < 0) { //if we are to the right of the target make the base speed negativve
                baseSpeed = -baseSpeedConstant;
            } else {
                baseSpeed = baseSpeedConstant;
            }

            output = baseSpeed + P * (error / 360); //base speed is constant and error slowly goes down so the robot will slow down as it gets closer
            DriveTrain.getInstance().turnInPlace(-output, output); //setting the speeds for the left and right wheels for turning

            System.out.println("Current Angle: " + angle + "  Target: " + target + "   Error: " + error + "   Output: " + output);
        }
    }
}
