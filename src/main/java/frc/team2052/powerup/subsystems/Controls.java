package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Created by KnightKrawler on 1/15/2018.
 */
public class Controls {
    private static Controls instance = new Controls();
    public static Controls getInstance() {return instance;}

    private Joystick secondaryStick = new Joystick(2);

    public boolean GetOpenIntake() {
        return secondaryStick.getRawButton(4);
    }
}
