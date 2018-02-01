package frc.team2052.powerup.auto;

import edu.wpi.first.wpilibj.DriverStation;

public class FieldConfig {
    public static boolean isMySwitchLeft() { //Left = true, Right = false
        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.charAt(0) == 'L')
        {
            return true;
        } else {
           return false;
        }
    }

    public static boolean isMyScaleLeft() { //Left = true, Right = false
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
