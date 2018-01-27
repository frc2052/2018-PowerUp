package frc.team2052.powerup.auto;

public class AutoModeRunner {
    Thread autoThread;
    AutoModeBase autoMode;

    public void start() {//Initalizes auto mode
        if (autoMode == null)
            return;
        autoThread = new Thread(() -> autoMode.start());
        autoThread.start();
    }

    public void setAutoMode(AutoModeBase autoMode) {//Runs auto mode selector
        System.out.println(autoMode);
        this.autoMode = autoMode;
    }

    public void stop() {//Stops auto mode
        if (autoMode != null)
            autoMode.stop();
        autoThread = null;
    }


}


