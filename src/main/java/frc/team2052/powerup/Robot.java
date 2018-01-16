package frc.team2052.powerup;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    Boolean Hi = true;
    Double Name = 9.0;

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
    public void teleopPeriodic() {
        SmartDashboard.putBoolean("Hi",Hi);
        SmartDashboard.putNumber("Name",Name);

    }

    @Override
    public void testPeriodic() { }
}