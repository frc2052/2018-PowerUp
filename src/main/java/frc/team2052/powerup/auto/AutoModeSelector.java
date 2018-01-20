package frc.team2052.powerup.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.auto.modes.*;

public class AutoModeSelector {
    private static SendableChooser<AutoModeDefinition> sendableChooserAutoMode;
    private static SendableChooser<WaitTimeDefinition> sendableChooserWaitTime;

    public static double SelectedWaitTime;
    public static void putToSmartDashboard() {
        sendableChooserAutoMode = new SendableChooser<AutoModeDefinition>();
        for (int i = 0; i < AutoModeDefinition.values().length; i++) {
            AutoModeDefinition mode = AutoModeDefinition.values()[i];
            if (i == 0) {
                sendableChooserAutoMode.addDefault(mode.name, mode);
            } else {
                sendableChooserAutoMode.addObject(mode.name, mode);
            }
        }
        SmartDashboard.putData("auto_modes", sendableChooserAutoMode);

        sendableChooserWaitTime = new SendableChooser<WaitTimeDefinition>();
        for (int i = 0; i < WaitTimeDefinition.values().length; i++) {
            WaitTimeDefinition wait = WaitTimeDefinition.values()[i];
            if (i == 0) {
                sendableChooserWaitTime.addDefault(wait.name, wait);
            } else {
                sendableChooserWaitTime.addObject(wait.name, wait);
            }
        }
        SmartDashboard.putData("auto_modes", sendableChooserAutoMode);
    }

    public static AutoModeBase getAutoInstance() {
        return sendableChooserAutoMode.getSelected().getInstance();
    } //returns selected enum method

    public enum AutoModeDefinition {
        DONT_MOVE("Don't Move", DontMove.class),
        LSTARTONLYSCALE("Start left, go only to scale",LStartOnlyScale.class),
        LSTARTPERFERSCALE("Start left, prefer to go to scale",LStartPreferScale.class),
        LSTARTPREFERSWITCH("Start left, prefer to go to switch", LStartPreferSwitch.class),
        RSTARTONLYSCALE("Start right, go only to scale", RStartOnlyScale.class),
        RSTARTPREFERSCALE("Start right, prefer to go to scale", RStartPreferScale.class),
        RSTARTPREFERSWITCH("Start right, prefer to go to switch", RStartPreferSwitch.class),
        CENTER("Start in center, go to switch", Center.class),
        AUTOLINE("Just pass Autoline", Autoline.class);


        private final Class<? extends AutoMode> clazz;
        private final String name;


        AutoModeDefinition(String name, Class<? extends AutoMode> clazz) {
            this.clazz = clazz;
            this.name = name;
        }

        public AutoModeBase getInstance() {
            AutoModeBase instance;
            try {
                instance = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                return null;
            }
            return instance;
        }
    }

    public enum WaitTimeDefinition {
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

        WaitTimeDefinition(double waitTime,String Name) {
            WaitTime = waitTime;
            name = Name;
        }

        public double getWaitTime(){
            SelectedWaitTime = WaitTime;
            return SelectedWaitTime;
        }
    }
}
