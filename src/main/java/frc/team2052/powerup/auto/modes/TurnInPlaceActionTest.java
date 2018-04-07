package frc.team2052.powerup.auto.modes;

import frc.team2052.powerup.auto.AutoMode;
import frc.team2052.powerup.auto.AutoModeEndedException;
import frc.team2052.powerup.auto.actions.SeriesAction;
import frc.team2052.powerup.auto.actions.TurnInPlaceAction;
import frc.team2052.powerup.auto.actions.WaitAction;

import java.util.Arrays;


public class TurnInPlaceActionTest extends AutoMode {
    @Override
    protected void init() throws AutoModeEndedException {
        System.out.println("RUNNING TURN IN PLACE");
        runAction(new SeriesAction(Arrays.asList(
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.ROBOTCENTRIC,-45.0),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC,90.0),
                new WaitAction(10),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC,90.0)

                )));

    }
}
