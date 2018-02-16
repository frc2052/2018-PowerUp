package frc.team2052.powerup.subsystems;


import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.hal.PDPJNI;


public class AmpGetter extends PowerDistributionPanel
{
    private static AmpGetter instance = null;
    public static AmpGetter getInstance() {
        if (instance == null) {
            try {
                instance = new AmpGetter();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create AmpGetter: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }
    private int n_module;
    public void PowerDistributionPanel(int module) {
        n_module = module;
        checkPDPModule(module);
        setName("PowerDistributionPanel", "n_module");
    }
    public double getCurrentIntake1(int channel) {
         double current1 = PDPJNI.getPDPChannelCurrent((byte) 0, n_module);

        checkPDPChannel(0);
        System.out.println("Intake Motor 1 Amps ="  + current1);//intake max 30 amps
        return current1;

    }
    public double getCurrentIntake2(int channel) {
        double current2 = PDPJNI.getPDPChannelCurrent((byte) 2, n_module);

        checkPDPChannel(2);
        System.out.println("Intake Motor 2 Amps ="  + current2);
        return current2;
    }
    public double getCurrentElevator(int channel) {
        double current3 = PDPJNI.getPDPChannelCurrent((byte) 3, n_module);//elevator max 40 amps

        checkPDPChannel(3);
        System.out.println("Elevator Motor Amps ="  + current3);
        return current3;
    }

}
