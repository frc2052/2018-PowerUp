package frc.team2052.powerup.auto;

import com.first.team2052.lib.Loopable;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * To get info from FMS about which scale and switch correspond to our alliance
 */
public class FieldConfig implements Loopable {

    private static String gameData = null;

    public static boolean isMySwitchLeft() { //Switch is left = true, Switch is right = false
        if (gameData != null && gameData.length() > 1) {
            if (gameData.charAt(0) == 'L') //if the first letter is left (our switch is left)
            {
                System.out.println("Switch is left " + gameData);
                return true;
            } else {
                System.out.println("Switch is right " + gameData);
                return false;
            }
        }else{
            System.out.println("gameData was null or less then 1 charector");
            try{
                System.out.println(gameData);
            }catch (Exception exc){
                System.out.println("failed to print gamedata");
            }
            return true;
        }
    }

    public static boolean isMyScaleLeft() { //Scale is left = true, Scale is right = false
        if (gameData != null && gameData.length() > 2) {
            if (gameData.charAt(1) == 'L') //if second letter is left (our scale is left)
            {
                System.out.println("Scale is left " + gameData);
                return true;
            } else {
                System.out.println("Scale is right " + gameData);
                return false;
            }
        }else{
            System.out.println("gameData was null or less then 2 charectors");
            try{
                System.out.println(gameData);
            }catch (Exception exc){
                System.out.println("failed to print gamedata");
            }
            return true;
        }
    }

    public static boolean hasGameData() { return gameData != null && gameData.length() > 0; }
    public static void reset() { gameData = null; }

    public static String getGameData() {
        if (gameData == null)
        {
            return "[NULL]";
        } else {
            return gameData;
        }
    }

    //FMS is not gaurenteed to give us the game data on first try, so loop until you get it
    @Override
    public void update() {
        String newValue = DriverStation.getInstance().getGameSpecificMessage();
        if (newValue != null)
        {
            newValue = newValue.trim();
        }
        if (gameData == null || !gameData.equals(newValue)) {
            if (newValue != null && newValue.length() > 0 && newValue.length() < 4) {
                if (gameData != null) {
                    System.out.println("GAME DATA CHANGED FROM " + gameData + " TO " + newValue);
                }
                gameData = DriverStation.getInstance().getGameSpecificMessage(); //getting the three letters telling us which scale/switch is ours
            } else if (newValue == null) {
                System.out.println("DRIVER STATION VALUE FOR GAME DATA WAS NULL!");
            } else {
                System.out.println("DRIVER STATION VALUE FOR GAME DATA WAS INVALID: [" + newValue + "]");
            }
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
