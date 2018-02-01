package frc.team2052.powerup.subsystems.drive;

public class DriveSignal { //a drive signal is a motor speed for both motors. This allows us to send both variabes at once
    public double leftMotor;  //todo:change variable to include speed
    public double rightMotor;

    public DriveSignal(double left, double right) {
        this.leftMotor = left;
        this.rightMotor = right;
    }

    public static DriveSignal NEUTRAL = new DriveSignal(0, 0);

    @Override
    public String toString() { //overrides the intrinsic tostring method to make debugging easier
        return "L: " + leftMotor + ", R: " + rightMotor;
    }
}
