package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.drive.DriveTrain;

import java.util.Set;

public class WaitForPathMarkerAction implements Action {

    private DriveTrain mDrive = DriveTrain.getInstance();
    private String mMarker;

    public WaitForPathMarkerAction(String marker) {
        mMarker = marker;
    }

    @Override
    public boolean isFinished() {
        Set<String> markers = mDrive.getPathMarkersCrossed();
        return (markers != null && markers.contains(mMarker));
    }

    @Override
    public void update() {
    }

    @Override
    public void done() {
    }

    @Override
    public void start() {
    }

}
