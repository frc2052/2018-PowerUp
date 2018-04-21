package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class TurnInPlaceAction implements Action {

    private double baseSpeedConstant = .2;
    private double turnAngle;
    private double baseSpeed; //holds the current base speed
    private double error; //the angle the robot is off
    private double angle; //the angle the robot is currently
    private double target; //the angle we want to go to
    private double output; //the output to the wheels
    private double P = 1.5; //SpeedCurveMultiplier. this will increase the speed that we start at and increase the decceleration
    private TurnMode mode;

    private boolean isFinished = false;
    /**
     *
     * @param turnDegrees the change in rotation in degrees
     */

    public TurnInPlaceAction(TurnMode mode, double turnDegrees){
        this.mode = mode;
        turnAngle = turnDegrees;
    }

    public TurnInPlaceAction(TurnMode mode, double turnDegrees, double baseSpeed){
        this.mode = mode;
        turnAngle = turnDegrees;
        baseSpeedConstant = baseSpeed;
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
        if (mode == TurnMode.ROBOTCENTRIC){
            this.target = DriveTrain.getInstance().getGyroAngleDegrees() + turnAngle; //turns a displacemen value to a set degree value
        }else {
            this.target = turnAngle;
        }
    }

    @Override
    public void update() {
        angle = DriveTrain.getInstance().getGyroAngleDegrees(); //angle communicates with the gyro to find the current angle
        error = target - angle; //setting the error angle to equal the difference of the target angle and current angle

        if (Math.abs(error) < 2.5){ //if the robot is 2 degrees or less from the target position then finish, if not keep turning
            isFinished= true;
            DriveTrain.getInstance().turnInPlace(0,0); //stops turning
        }
        else {
            if (error < 0) { //if we are to the right of the target make the base speed negativve
                baseSpeed = -baseSpeedConstant;
            } else {
                baseSpeed = baseSpeedConstant;
            }

            double proportional = Math.pow(1.5, (Math.abs(error)/18) - 10);

            if (error < 0){
                proportional = -proportional;
            }
            output = baseSpeed + P * proportional; //(P * (error / 360)); //base speed is constant and error slowly goes down so the robot will slow down as it gets closer


            if (output > .8){
                output = .8;
            }else if (output < -.8) {
                output = -.8;
            }

            DriveTrain.getInstance().turnInPlace(-output, output); //setting the speeds for the left and right wheels for turning

            System.out.println("Current Angle: " + angle + "  Target: " + target + "   prop: " + proportional + "   Error: " + error + "   Output: " + output);
        }
    }

    public enum TurnMode{
        ROBOTCENTRIC,
        FIELDCENTRIC
    }
}
