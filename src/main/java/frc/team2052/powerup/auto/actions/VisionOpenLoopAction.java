package frc.team2052.powerup.auto.actions;

import frc.team2052.powerup.subsystems.AmpGetter;
import frc.team2052.powerup.subsystems.Vision.VisionProcessor;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class VisionOpenLoopAction implements Action{

    private double leftIncrease;
    private double rightIncrease;

    private DriveSignal visionDrive = new DriveSignal(0, 0);

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return AmpGetter.getCurrentIntake1(0) >= 30 || AmpGetter.getCurrentIntake2(2) >= 30 || VisionProcessor.getInstance().checkLostTarget();
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        double error = (VisionProcessor.getInstance().getError() / (VisionProcessor.getInstance().getXRange()/2))/4;
        // error range is from -xrange/2 to xRange/2
        //devide by x range/2 to get a value between -1 to 1
        //devide by 4 to get a value between -.25 and .25
        if (error > 0){
            leftIncrease = Math.abs(error);
            rightIncrease = 0;
        }else{
            leftIncrease = 0;
            rightIncrease = Math.abs(error);
        }
        visionDrive.leftMotorSpeedPercent = .25 + leftIncrease;
        visionDrive.rightMotorSpeedPercent = .25 + rightIncrease;

        DriveTrain.getInstance().setOpenLoop(visionDrive);

    }
}
