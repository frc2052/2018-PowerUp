package frc.team2052.powerup.auto;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * To get info from FMS about which scale and switch correspond to our alliance
 */
public class FieldConfig {
    public static String gameData;


    public static boolean isMySwitchLeft() { //Switch is left = true, Switch is right = false
        //string gameData;
        //gameData = DriverStation.getInstance().getGameSpecificMessage(); //getting the three letters telling us which scale/switch is ours

        if (gameData !=null && gameData.length() > 1) {
            if (gameData.charAt(0) == 'L') //if the first letter is left (our switch is left)
            {
                System.out.println("switch is left " + gameData);
                return true;
            } else {
                System.out.println("switch is right " + gameData);
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
        //String gameData;
        //gameData = DriverStation.getInstance().getGameSpecificMessage(); //getting the three letters telling us which scale/switch is ours
        if (gameData !=null && gameData.length() > 2) {
            if (gameData.charAt(1) == 'L') //if second letter is left (our scale is left)
            {
                System.out.println("scale is left " + gameData);
                return true;
            } else {
                System.out.println("scale is left " + gameData);
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
}
