package frc.team2052.powerup.auto;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Created by Lancelot on 1/20/2018.
 * To get info from FMS about which scale and switch correspond to our alliance
 */
public class FieldConfig {
    public static boolean isMySwitchLeft() { //Left = true, Right = false
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.charAt(0) == 'L')
        {
            System.out.println("switch is left " + gameData);
            return true;
        } else {
            System.out.println("switch is right " + gameData);
            return false;
        }
    }

    public static boolean isMyScaleLeft() { //Left = true, Right = false
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.charAt(1) == 'L')
        {
            System.out.println("scale is left " + gameData);
            return true;
        } else {
            System.out.println("scale is left " + gameData);
            return false;
        }
    }
}
