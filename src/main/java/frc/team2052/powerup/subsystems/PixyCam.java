package frc.team2052.powerup.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

public class PixyCam {

    private static PixyCam instance = null;
    public static PixyCam getInstance() {
        if (instance == null) {
            try {
                instance = new PixyCam();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create PixyCam: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private AnalogInput positionAnalog = null;
    private DigitalInput detectCubeInput = null;


    public void init(){
        if(positionAnalog == null){
            positionAnalog = new AnalogInput(1);
        }
        if(detectCubeInput == null) {
            detectCubeInput = new DigitalInput(0);
        }

    }

    public boolean getCubeInput(){
        return detectCubeInput.get();
    }

    public double getPositionVoltage(){
        return positionAnalog.getVoltage();
    }
}
