package frc.team2052.powerup.auto;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Created by Lancelot on 1/20/2018.
 */
public class FieldConfig {
    public static boolean isMySwitchLeft() {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.charAt(0) == 'L')
        {
            return true;
        } else {
           return false;
        }
    }

    public static boolean isMyScaleLeft() {
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.charAt(1) == 'L')
        {
            return true;
        } else {
            return false;
        }
    }
}
