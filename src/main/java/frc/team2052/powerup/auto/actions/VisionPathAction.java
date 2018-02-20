package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Vision.VisionProcessor;

/**
 * Created by KnightKrawler on 2/19/2018.
 */
public class VisionPathAction implements Action {
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        System.out.println("ERROR FOR VISION" + VisionProcessor.getInstance().getError());
        /*
        if (controls.wantVisionAlign()) {
            if(!visionTurn) {
                VisionTrackingTurnAngleResult latestTargetResult = VisionProcessor.getInstance().getLatestTargetResult();
                if (latestTargetResult.isValid) {
                    visionTurn = true;
                    visionTurnAngle = Rotation2d.fromDegrees(driveTrain.getGyroAngleDegrees() + latestTargetResult.turnAngle);
                }
                if(visionTurn) {
                    driveTrain.setVelocityHeadingSetpoint(10 * controls.getTank(), visionTurnAngle);
                    */
    }
}
