package frc.team2052.powerup.auto;

import edu.wpi.first.wpilibj.Timer;
import frc.team2052.powerup.auto.actions.SeriesAction;

public abstract class AutoModeBase {

    private boolean running = false; //tells if the robot is enabled
    private Timer timer = new Timer(); //records how long the robot has been enabled (in auto)

    public void delay(double seconds) throws AutoModeEndedException { //delay in the timer
        isRunningWithThrow();
        Timer.delay(seconds);
    }

    public void errorStop(String message) throws AutoModeEndedException { //sends a message to SD if they'res an error
        if (message == null) {
            message = "[NO MESSAGE]";
        }
        System.out.println("AutoModeBase: ErrorStop Ended " + message);
        //stop();
        throw new AutoModeEndedException();
    }

    public double getAutoTime() {
        return timer.get();
    }

    protected abstract void init() throws AutoModeEndedException;

    public boolean isRunning() {
        return running;
    }

    public boolean isRunningWithThrow() throws AutoModeEndedException { //if the robot stops running it throws an exception
        if (!isRunning()) {
            System.out.println("AutoModeBase: isRunningWithThrow was not Running. Throwing Exception");
            throw new AutoModeEndedException();
        }
        return isRunning();
    }

    protected void runAction(SeriesAction action) throws AutoModeEndedException {
        System.out.println("*****Starting to run auto action*****");
        isRunningWithThrow(); //checks to see if running
        action.start(); //begins something
        while (!action.isFinished() && isRunningWithThrow()) { //update while action is not done, but still is running
            action.update();
            try {
                Thread.sleep((long) ((1.0 / 50.0) * 1000.0));
            } catch (InterruptedException e) {
                if (e.getMessage() != null) {
                    System.out.println("Error in AutoMode.runAction " + e.getMessage());
                } else {
                    System.out.println("Error in AutoMode.runAction - No Message");
                }
                e.printStackTrace();
            }
        }
        action.done();
    }

    public void start() { //starting auto and checks to see if it ends early
        running = true;
        timer.reset();
        timer.start();
        try {
            System.out.println("Calling init on AutoMode " + this.getClass().getName());
            init();
        } catch (AutoModeEndedException e) {
            System.out.println("Auto Ended Early");
            e.printStackTrace();
        }
    }


    public void stop() { //stopping auto
        running = false;
        System.out.println("Stopping Auto because Stop was called");
        timer.stop();
    }
}
