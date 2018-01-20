package frc.team2052.powerup.subsystems;

import com.first.team2052.lib.Loopable;

public class Arm implements Loopable {

    private armState currentArmState;

    @Override
    public void update() {
        switch(currentArmState){
            case SWITCHPOSWRISTUP:
                //psudo code, fake function works as follows: setPos( ARM_POSITION , WRIST_POSITION );
                setPos( 'ARM_POSITION?' , (Math.PI/2) );
                currentArmState = armState.SCALEDOWNPOSWRISTUP;
                return;

            case SWITCHPOSWRISTLEVEL:
                setPos( 'ARM_POSITION?' , (Math.PI/2) );
                currentArmState = armState.?;
                return;

            case SCALEDOWNPOSWRISTUP:
                setPos( 'ARM_POSITION?' , (Math.PI/2) );
                currentArmState = armState.SCALEUPPOSWRISTUP;
                return;

            case SCALEDOWNPOSWRISTDOWN:
                setPos( 'ARM_POSITION?' , (Math.PI/2) );
                currentArmState = armState.SWITCHPOSWRISTLEVEL;
                return;

            case SCALEUPPOSWRISTUP:
                setPos( 'ARM_POSITION?' , (Math.PI/2) );
                currentArmState = armState.SCALEUPPOSWRISTDOWN;
                return;

            case SCALEUPPOSWRISTDOWN:
                setPos( 'ARM_POSITION?' , (Math.PI/2) );
                currentArmState = armState.SWITCHPOSWRISTLEVEL;
                return;

        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    private enum armState{
        SWITCHPOSWRISTUP,
        SWITCHPOSWRISTLEVEL,
        SCALEDOWNPOSWRISTUP,
        SCALEDOWNPOSWRISTDOWN,
        SCALEUPPOSWRISTUP,
        SCALEUPPOSWRISTDOWN;
    }



}
