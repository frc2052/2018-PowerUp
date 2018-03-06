package frc.team2052.powerup.auto.modes;

import com.first.team2052.lib.path.Path;
import frc.team2052.powerup.auto.*;
import frc.team2052.powerup.auto.actions.*;
import frc.team2052.powerup.subsystems.Elevator;

import java.util.Arrays;

public class Center extends CenterStartBase{
    @Override
    protected void init() throws AutoModeEndedException {
        System.out.println("RUNNING CENTER");
        if(FieldConfig.isMySwitchLeft()) { //if left switch is ours
            System.out.println("HEADING TO L SWITCH");
            leftSwitch();
        }
        else { //if right switch is ours
            System.out.println("HEADING TO R SWITCH");
            rightSwitch();

        }
    }
}
