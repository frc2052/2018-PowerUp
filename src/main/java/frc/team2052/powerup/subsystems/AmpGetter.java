package frc.team2052.powerup.subsystems;


import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import frc.team2052.powerup.Constants;


public class AmpGetter
{
    public static double getCurrentIntake1(int channel) {
        try {
            double current1 = PDPJNI.getPDPChannelCurrent((byte) 0, Constants.kPDPId);
            PowerDistributionPanel.checkPDPChannel(0); //this will throw an exception if nothing is on channel 0
            System.out.println("Intake Motor 1 Amps =" + current1);//intake max 30 amps
            return current1;
        }
        catch (Exception exc){
            //Don't kill the robot if someone plugs the talon into a different PDP port
            System.out.println("DANGER: Failed to get current for Intake 1" + exc.getMessage());
            exc.printStackTrace();
            return  0;
        }
    }
    public static double getCurrentIntake2(int channel) {
        try {
            double current2 = PDPJNI.getPDPChannelCurrent((byte) 2, Constants.kPDPId);
            PowerDistributionPanel.checkPDPChannel(2); //this will throw an exception if nothing is on channel 0
            System.out.println("Intake Motor 2 Amps ="  + current2);
            return current2;
        }
        catch (Exception exc){
            //Don't kill the robot if someone plugs the talon into a different PDP port
            System.out.println("DANGER: Failed to get current for Intake 2" + exc.getMessage());
            exc.printStackTrace();
            return  0;
        }
    }
    public static double getCurrentElevator(int channel) {
        try {
            double current3 = PDPJNI.getPDPChannelCurrent((byte) 3, Constants.kPDPId);//elevator max 40 amps
            PowerDistributionPanel.checkPDPChannel(3); //this will throw an exception if nothing is on channel 0
            System.out.println("Elevator Motor Amps ="  + current3);
            return current3;
        }
        catch (Exception exc){
            //Don't kill the robot if someone plugs the talon into a different PDP port
            System.out.println("DANGER: Failed to get current for Elevator" + exc.getMessage());
            exc.printStackTrace();
            return  0;
        }
    }
}
