package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;

import java.util.Arrays;

public class DontMove extends AutoMode{
    @Override
    protected void init() throws AutoModeEndedException { //Robot does not move
        System.out.println("RUNNING DON'T MOVE");
    }
}
