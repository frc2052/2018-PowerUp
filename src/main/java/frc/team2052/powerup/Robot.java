package frc.team2052.powerup;

import edu.wpi.first.wpilibj.IterativeRobot;
import frc.team2052.powerup.subsystems.Controls;
import frc.team2052.powerup.subsystems.Intake;

public class Robot extends IterativeRobot {
    Intake intake = Intake.getInstance();
    Controls ctrl = Controls.getInstance();

    @Override
    public void robotInit() { }

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() {

    }

    @Override
    public void testInit() { }


    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void testPeriodic() { }
}