package frc.team2052.powerup.subsystems;

import frc.team2052.powerup.Constants;

/**
 * Created by LloydBigglesen on 1/17/2018.
 */
public class Elevator {
    set.Height(height){
        motor = encoderValue;
    }
    get.Height(){
        return motor = (motor.encoder*constants.inchmultiplier);
    }

    get.Armpos(){
        return solodnoid.in;
    }
    set.Armpos(){
        solonoid.in = true;
        solonoid.out = !true;
    }












}
