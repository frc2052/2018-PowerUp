package frc.team2052.powerup;

import com.first.team2052.lib.Loopable;
import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Rotation2d;
import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

/**
 * Created by KnightKrawler on 1/23/2018.
 */
public class RobotStateEstimator implements Loopable {
    //Start collecting encoder position
    private static RobotStateEstimator instance = new RobotStateEstimator();
    DriveTrain driveTrain = DriveTrain.getInstance();
    RobotState robotState = RobotState.getInstance();
    private double left_encoder_prev_distance = 0;
    private double right_encoder_prev_distance = 0;

    private RobotStateEstimator(){}

    public static RobotStateEstimator getInstance() {
        return instance;
    }

    @Override
    public void onStart() {
        left_encoder_prev_distance = driveTrain.getLeftDistanceInches();
        right_encoder_prev_distance = driveTrain.getRightDistanceInches();
    }

    @Override
    //Update encoder readings at a set time and print values
    public void update() {
        double time = Timer.getFPGATimestamp();
        double left_distance = driveTrain.getLeftDistanceInches();
        double right_distance = driveTrain.getRightDistanceInches();
        Rotation2d gyroAngle = driveTrain.getGyroAngle();

        //System.out.println("Left Encoder: " + left_distance + "   Right Encoder: " + right_distance);
        
        //Generates most previous distance and encoder values
        RigidTransform2d odometry = robotState.generateOdometryFromSensors(
                left_distance - left_encoder_prev_distance,
                right_distance - right_encoder_prev_distance,
                gyroAngle);

        robotState.addVehicleObservation(time, odometry);

        left_encoder_prev_distance = left_distance;
        right_encoder_prev_distance = right_distance;
    }

    @Override
    public void onStop() {

    }
}
