package frc.team2052.powerup.subsystems;

import frc.team2052.powerup.Constants;

public class Arm {

    public int delta = (currentArmAngle - Constants.armDeltaSubtractor);

    if(delta < Constants.dangerZoneArm && delta > -Constants.dangerZoneArm ) {
        if(wristAngle < 0 && wristAngle > -Constants.dangerZoneWrist) {
            set wristAngle = -Constants.dangerZoneWrist;
        }
        else if(delta < Constants.dangerZoneWrist) {
            set wristAngle = Constants.dangerZoneWrist;
        }
    }

}
