package frc.team2052.powerup.auto;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2052.powerup.auto.modes.DontMove;

public class AutoModeSelector {
    private static SendableChooser<AutoModeDefinition> sendableChooserAutoMode;

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
    }

    public static AutoModeBase getAutoInstance() {
        return sendableChooserAutoMode.getSelected().getInstance();
    } //returns selected enum method

    public enum AutoModeDefinition {
        DONT_MOVE("Don't Move", DontMove.class);

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
}
