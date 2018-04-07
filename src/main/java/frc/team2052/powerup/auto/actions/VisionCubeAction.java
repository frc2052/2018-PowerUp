package frc.team2052.powerup.auto.actions;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.Constants;
import frc.team2052.powerup.DriveHelper;
import frc.team2052.powerup.subsystems.Interfaces.PickupSubsystem;
import frc.team2052.powerup.subsystems.PixyCam;
import frc.team2052.powerup.subsystems.SubsystemFactory;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;

public class VisionCubeAction implements Action {
    private boolean isDone = false;
    private DriveTrain drive = null;
    private PixyCam pixyCam = null;
    private DigitalInput detectCube = null;
    private PickupSubsystem pickup = null;

    private double startTime;
    private double timeOut = 4;

    public VisionCubeAction(){
        this.pickup = SubsystemFactory.getPickup();
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
        startTime = Timer.getFPGATimestamp();
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
                } else if (timeOut < Timer.getFPGATimestamp() - startTime){
                    isDone = true;
                    System.out.println("NO CUBE. VISION DONE");
                    System.out.println("PixyCam timed out at time " + Timer.getFPGATimestamp() + " Timeout = " + timeOut + " Timepassed " + (Timer.getFPGATimestamp() - startTime));
                    drive.setOpenLoop(DriveSignal.NEUTRAL);
                }else if (1 > Timer.getFPGATimestamp() - startTime){ //first second don't see a cube, drive forward and hope we see it
                    DriveHelper dh = new DriveHelper();
                    drive.setOpenLoop(dh.drive(Constants.kVisionDrivePercent, 0, false));
                }
            } catch (Exception exc) {
                System.out.println("ERROR: getting vision inputs " + exc.getMessage());
                exc.printStackTrace();
            }
            try {
                if (this.pickup.isCubePickedUp()) {
                    System.out.println("CUBE WAS TOUCHED");
                    isDone = true;
                    drive.setOpenLoop(DriveSignal.NEUTRAL);
                }
            } catch (Exception e) {
                System.out.println("ERROR: Failed to check if pickup has cube. ENDING VISION " + e.getMessage());
                e.printStackTrace();
                isDone = true;
            }
        }
    }
}
