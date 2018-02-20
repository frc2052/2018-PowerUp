package frc.team2052.powerup.auto.actions;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class ForceEndCurrentPathAction  implements Action {
    private boolean isDone;

    public ForceEndCurrentPathAction() {
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }

    @Override
    public void update() {

    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        DriveTrain.getInstance().abortCurrentPath();
        isDone = true;
    }
}
