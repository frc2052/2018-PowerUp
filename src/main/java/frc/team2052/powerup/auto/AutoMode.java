package frc.team2052.powerup.auto;

public abstract class AutoMode extends AutoModeBase {
    public boolean isBlue() {
        return AutoModeSelector.getSide() == AutoModeSelector.Side.BLUE;
    }

    public boolean isRed() {
        return AutoModeSelector.getSide() == AutoModeSelector.Side.RED;
    }

    public AutoModeSelector.Side getSide() {
        return AutoModeSelector.getSide();
    }
}
