package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.Vision.VisionProcessor;

/**
 * Created by KnightKrawler on 2/19/2018.
 */
public class VisionTrackAction implements Action {
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return VisionProcessor.getInstance().getArea() < 1200;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        System.out.println("ERROR FOR VISION" + VisionProcessor.getInstance().getError());
    }
}
