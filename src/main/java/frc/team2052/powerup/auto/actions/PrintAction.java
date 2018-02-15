package frc.team2052.powerup.auto.actions;

public class PrintAction implements Action {
    String message ;

    public PrintAction (String message){
        this.message = message;
    }

    @Override
    public void done() {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start() {
        System.out.println ("PrintAction: " + message);
    }

    @Override
    public void update() {

    }
}
