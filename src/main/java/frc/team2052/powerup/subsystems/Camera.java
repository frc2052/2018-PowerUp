package frc.team2052.powerup.subsystems;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import org.opencv.core.Mat;

public class Camera {
    private static Camera instance = null;
    public static Camera getInstance() { //if the camera isn't working the robot can still do things
        if (instance == null) {
            try {
                instance = new Camera();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create Camera: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }


    private Thread visionThread;

    public void init() {
        visionThread = new Thread(() -> {


            AxisCamera camera = CameraServer.getInstance().addAxisCamera("10.20.52.11");
            camera.setResolution(320, 240);
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 320, 240);
            Mat mat = new Mat();

            while (!Thread.interrupted()) {
                if (cvSink.grabFrame(mat) == 0) {
                    outputStream.notifyError(cvSink.getError());
                    continue;
                }
                outputStream.putFrame(mat);
            }
        });
        visionThread.setDaemon(true);
        visionThread.start();
    }
}
