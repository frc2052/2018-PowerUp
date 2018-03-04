package frc.team2052.powerup.auto.actions;

import com.first.team2052.lib.vec.RigidTransform2d;
import com.first.team2052.lib.vec.Rotation2d;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.DriveHelper;
import frc.team2052.powerup.RobotState;
import frc.team2052.powerup.subsystems.PixyCam;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class DriveVelocityHeadingAction implements Action {
    private boolean isFinished = false;
    private DriveTrain drive = null;
    private double driveTimeSeconds = 0;
    private double startTimeSeconds = 0;
    private double inchesPerSecond = 0;

    public DriveVelocityHeadingAction(double inchesPerSec, double seconds) {
        this.driveTimeSeconds =seconds;
        this.inchesPerSecond = inchesPerSec;
        drive = DriveTrain.getInstance();
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
        startTimeSeconds = Timer.getFPGATimestamp();
    }

    @Override
    public void update() {
        if (Timer.getFPGATimestamp() - driveTimeSeconds > startTimeSeconds) {
            isFinished = true;
        } else {
            drive.setVelocityHeadingSetpoint(this.inchesPerSecond, Rotation2d.fromDegrees(0));
        }
    }
}
