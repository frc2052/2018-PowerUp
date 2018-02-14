package frc.team2052.powerup.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.auto.modes.*;

public class AutoModeSelector {
    private static SendableChooser<AutoModeDefinition> sendableChooserAutoMode; //Makes drop down for Auto Mode Selection
    private static SendableChooser<WaitTimeDefinition> sendableChooserWaitTime; //Makes drop down for Wait Time Selection

    public static double trimFactorX =0;
    public static double trimFactorY =0;

    public static double SelectedWaitTime;
    public static void putToSmartDashboard() { //puts the auto modes and delay options to the smartdashboard
        sendableChooserAutoMode = new SendableChooser<AutoModeDefinition>();
        for (int i = 0; i < AutoModeDefinition.values().length; i++) {
            AutoModeDefinition mode = AutoModeDefinition.values()[i];
            if (i == 0) {
                sendableChooserAutoMode.addDefault(mode.name, mode); //a sendableChooser is a list
            } else {
                sendableChooserAutoMode.addObject(mode.name, mode);
            }
        }
        SmartDashboard.putData("auto_modes", sendableChooserAutoMode);  //allows driver to choose auto modes in Smart Dashboard

        sendableChooserWaitTime = new SendableChooser<WaitTimeDefinition>();
        for (int i = 0; i < WaitTimeDefinition.values().length; i++) {  //compiles all wait time definitions into a sendableChooser
            WaitTimeDefinition wait = WaitTimeDefinition.values()[i];
            if (i == 0) {
                sendableChooserWaitTime.addDefault(wait.name, wait);
            } else {
                sendableChooserWaitTime.addObject(wait.name, wait);
            }
        }
        SmartDashboard.putData("wait_time", sendableChooserWaitTime); //allows driver to choose wait time in Smart Dashboard

        SmartDashboard.putNumber("trim_forward", trimFactorX);
        SmartDashboard.putNumber("trim_right", trimFactorY);
    }

    public static AutoModeBase getAutoInstance() {
        return sendableChooserAutoMode.getSelected().getInstance();
    } //returns selected enum method

    public static AutoModeDefinition getAutoDefinition(){
        return sendableChooserAutoMode.getSelected();
    }
    public enum AutoModeDefinition { //Auto mode options for drive team to choose
        DONT_MOVE("Don't Move", DontMove.class),
        LSTARTONLYSCALE("Start left, go only to scale",LStartOnlyScale.class),
        LSTARTPERFERSCALE("Start left, prefer to go to scale",LStartPreferScale.class),
        LSTARTPREFERSWITCH("Start left, prefer to go to switch", LStartPreferSwitch.class),
        RSTARTONLYSCALE("Start right, go only to scale", RStartOnlyScale.class),
        RSTARTPREFERSCALE("Start right, prefer to go to scale", RStartPreferScale.class),
        RSTARTPREFERSWITCH("Start right, prefer to go to switch", RStartPreferSwitch.class),
        CENTER("Start in center, go to switch", Center.class),
        AUTOLINEWITHTIMER("Start left or right, cross Autoline with timer", AutolineWithTimer.class),
        AUTOLINEWITHTIMERCCENTERRIGHT("Start center, cross autoline to right with timer", AutolineWithTimerCenterRight.class),
        AUTOLINEWITHTIMERCCENTERLEFT("Start center, cross autoline to left with timer", AutolineWithTimerCenterLeft.class),
        TURNINPLACEAUTOTEST("Turn in place test", TurnInPlaceActionTest.class);



        private final Class<? extends AutoMode> clazz; //checks if the Class extends AutoMode, and then stores it in clazz
        private final String name;

        AutoModeDefinition(String name, Class<? extends AutoMode> clazz) { //requires a class that extends AutoMode and a String name of
            this.clazz = clazz;
            this.name = name;
        }

        public AutoModeBase getInstance() { //gets the instance of the AutoModeBase
            AutoModeBase instance;
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
            return instance;
        }
    }

    public enum WaitTimeDefinition { //options of the WaitTime
        ZERO("Wait 0 seconds", 0.0),
        ONE("Wait 1 seconds", 1.0),
        TWO("Wait 2 seconds", 2.0),
        THREE("Wait 3 seconds", 3.0),
        FOUR("Wait 4 seconds", 4.0),
        FIVE("Wait 5 seconds", 5.0),
        SIX("Wait 6 seconds", 6.0),
        SEVEN("Wait 7 seconds", 7.0),
        EIGHT("Wait 8 seconds", 8.0),
        NINE("Wait 9 seconds", 9.0),
        TEN("Wait 10 seconds", 10.0);
        private final double WaitTime;
        private final String name;

        WaitTimeDefinition(String Name,double waitTime) { //takes selected wait time and puts into other variables
            WaitTime = waitTime;
            name = Name;
        }

        public double getWaitTime(){ //returns the wait time we want
            SelectedWaitTime = WaitTime;
            return SelectedWaitTime;
        }
    }
    public static double getTrimY(){
        return SmartDashboard.getNumber("trim_right", trimFactorY);
    }

    public static double getTrimX(){
        return SmartDashboard.getNumber("trim_forward", trimFactorX);
    }
}
