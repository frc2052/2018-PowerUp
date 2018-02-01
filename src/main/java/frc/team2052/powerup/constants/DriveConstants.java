package frc.team2052.powerup.constants;

//todo: decide which constats are good
public class DriveConstants {

    public final static int kCANBusConfigTimeoutMS = 10;

    public static int kDriveRightMasterId = 1;
    public static int kDriveRightSlaveId = 2;
    public static int kDriveLeftMasterId = 4;
    public static int kDriveLeftSlaveId = 3;

    public static double kOpenLoopRampRate = .5; // this many seconds from 0 to full power
    public static double kClosedLoopRampRate = 0.0;

    public final static double kTrackScrubFactor = 0.65;
    public final static double kTrackEffectiveDiameter = (27.25 * 27.25 + 13 * 13) / 27.25;
    private static final int kDriveTicksPerRot = 1024; //grayhill 63R encoders have 256 pulses per rotation (PPR) x4edges because quadrature output
    //Solenoid Id's for shifting
    public static int kDriveOutSolenoidId = 1;
    public static int kDriveInSolenoidId = 0;
    // VEX 3 CIM ball shift with 3rd stage 3 rotations per gearbox output shaft rotation * ratio for gearbox
    // 5529.6 native units per rot
    public static int kDriveEncoderTicksPerRot = kDriveTicksPerRot; //encoder is directly on the wheel making the raio 1:1
    public static double kDriveWheelDiameterInches = 6.0;
    public static double kDriveVelocityKp = 0.2;
    public static double kDriveVelocityKi = 0.0;
    public static double kDriveVelocityKd = 2.0;
    public static double kDriveVelocityKf = 0.3;
    public static int kDriveVelocityIZone = 0;
    public static double kDriveVelocityRampRate = 0.0;
    public static int kDriveVelocityAllowableError = 0;
    public static double kPathFollowingLookahead = 24;
    public static double kPathFollowingMaxAccel = 50;
    public static double kPathFollowingMaxVel = 90;
    public static double kDriveHeadingVelocityKp = 5.0;
    public static double kDriveHeadingVelocityKi = 0.1;
    public static double kDriveHeadingVelocityKd = 60.0;
    public static double kGyroDeadzone = 1.5;
    public static final double kThrottleDeadband = 0.1;
    public static final double kWheelDeadband = 0.1;
    public static final double kTurnSensitivity = 1.25;
}
