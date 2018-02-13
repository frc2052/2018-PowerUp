package frc.team2052.powerup.subsystems;
import com.first.team2052.lib.ControlLoop;
import com.first.team2052.lib.RevRoboticsPressureSensor;
import com.first.team2052.lib.vec.RigidTransform2d;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.constants.ControlLoopConstants;
import frc.team2052.powerup.subsystems.Controls;
import frc.team2052.powerup.subsystems.Elevator;
import frc.team2052.powerup.subsystems.Intake;
import frc.team2052.powerup.subsystems.Ramp;
import frc.team2052.powerup.subsystems.drive.DriveSignal;
import frc.team2052.powerup.subsystems.drive.DriveTrain;
import frc.team2052.powerup.Robot;


public class AmpGetter extends PowerDistributionPanel
{
    private int n_module;
    public void PowerDistributionPanel(int module) {
        n_module = module;
        checkPDPModule(module);
        setName("PowerDistributionPanel", "n_module");
    }
    public double getCurrentIntake1(int channel) {
        double current1 = PDPJNI.getPDPChannelCurrent((byte) 0, n_module);

        checkPDPChannel(0);
        System.out.println("Intake Motor 1 Amps ="  + current1);
        return current1;
    }
    public double getCurrentIntake2(int channel) {
        double current2 = PDPJNI.getPDPChannelCurrent((byte) 2, n_module);

        checkPDPChannel(2);
        System.out.println("Intake Motor 2 Amps ="  + current2);
        return current2;
    }
    public double getCurrentElevator(int channel) {
        double current3 = PDPJNI.getPDPChannelCurrent((byte) 3, n_module);

        checkPDPChannel(3);
        System.out.println("Elevator Motor Amps ="  + current3);
        return current3;
    }
}
