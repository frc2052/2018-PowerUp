package frc.team2052.powerup.auto.actions;

import com.first.team2052.lib.vec.RigidTransform2d;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.DriveHelper;
import frc.team2052.powerup.RobotState;
import frc.team2052.powerup.subsystems.PixyCam;
import frc.team2052.powerup.subsystems.SubsystemFactory;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class VisionCubeAction implements Action {
    private boolean isDone = false;
    private DriveTrain drive = null;
    private PixyCam pixyCam = null;
    private DigitalInput detectCube = null;
//    private Pickup intake = null;

    public VisionCubeAction(){
        drive = DriveTrain.getInstance();
        pixyCam = PixyCam.getInstance();
    }

    @Override
    public void done() {
    }

    @Override
    public boolean isFinished() {
        return isDone;
    }

    @Override
    public void start() {
        pixyCam.init();
    }

    @Override
    public void update() {
        if (!isDone) {
            double maxVal = (3.3 / 2);
            try {
                if (pixyCam.getCubeInput()) {
                    double turn = pixyCam.getPositionVoltage();
                    if (turn > 3.3) //should never happen
                    {
                        System.out.println("Vision: Voltage was off the charts!");
                        turn = 3.3;
                    }
                    turn = turn - maxVal; //shift the voltage so that instead of 0 to 3.3 it is -1.65 to 1.65
                    turn = turn / maxVal; //get a value between -1 and 1 for turn velocity

                    System.out.println("TURN: " + turn);

                    DriveHelper dh = new DriveHelper();
                    drive.setOpenLoop(dh.drive(Constants.kVisionDrivePercent, -turn / 2, false));
                    RigidTransform2d robot_pose = RobotState.getInstance().getLatestFieldToVehicle().getValue();
//                System.out.println("VISION ROBOT POSE ----   DEGREES: " + robot_pose.getRotation().getDegrees() + " X: " + robot_pose.getTranslation().getX() + " Y:" + robot_pose.getTranslation().getY());
                } else {
                    System.out.println("NO CUBE. VISION DONE");
                    drive.setOpenLoop(DriveSignal.NEUTRAL);
                    isDone = true;
                }
            } catch (Exception exc) {
                System.out.println("Error getting vision inputs " + exc.getMessage());
                exc.printStackTrace();
            }
            if (SubsystemFactory.getPickup().isCubePickedUp()) {
                System.out.println("CUBE WAS TOUCHED");
                isDone = true;
                drive.setOpenLoop(DriveSignal.NEUTRAL);
            }
        }
    }
}
