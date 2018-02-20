package frc.team2052.powerup.subsystems.Vision;

import com.first.team2052.lib.Loopable;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Created by KnightKrawler on 2/19/2018.
 */
public class VisionProcessor implements Loopable {

    private static VisionProcessor instance = null;
    public static VisionProcessor getInstance() {
        if (instance == null) {
            try {
                instance = new VisionProcessor();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create VisionProcessor: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private final String networkTablePath = "GRIP/cubeContoursReport";
    private NetworkTableInstance networkInstance = null;
    private NetworkTable gripTable = null;

    double[] rawX;
    double[] rawArea;
    double targetX;
    double targetArea;
    private int targetElement = 0;
    private int xRange = 570;
    double error = 0;

    public VisionProcessor() {
        networkInstance = NetworkTableInstance.getDefault();
        gripTable = networkInstance.getTable(networkTablePath);

    }

    @Override
    public void update() {
        rawArea = gripTable.getEntry("area").getDoubleArray(new double[0]);
        rawX = gripTable.getEntry("centerX").getDoubleArray(new double[0]);

        findTargetElement();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    private void findTargetElement(){

        double largestArea = 0.0;

        if(rawArea.length != 0){

            for (int i = 0; i < rawArea.length; i++) {
                System.out.println("Element " + i + " equals " + rawArea[i]);
                if (rawArea[i] > largestArea) {
                    largestArea = rawArea[i];
                    targetElement = i;
                    System.out.println("TargetElement: " + targetElement);
                    targetArea = rawArea[targetElement];
                    System.out.println("Target Area: " + targetArea);
                    targetX = rawX[targetElement];
                }
            }
        }
    }

    public double getError(){
        error = targetX - xRange/2;
        System.out.println("Vision Error: " + error);
        return error;
    }

    public double getArea(){
        return targetArea;
    }
}
