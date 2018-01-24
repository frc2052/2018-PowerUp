package frc.team2052.powerup.constants;

//todo: decide which constats are good
public class DriveConstants {

    public final static int kCANBusConfigTimeoutMS = 10;

    public static int kDriveRightMasterId = 1;
    public static int kDriveRightSlaveId = 2;
    public static int kDriveLeftMasterId = 3;
    public static int kDriveLeftSlaveId = 4;

    public final static double kTrackScrubFactor = 0.65;
    public final static double kTrackEffectiveDiameter = (27.25 * 27.25 + 13 * 13) / 27.25;
    private static final int kDriveTicksPerRot = 256; //grayhill 63R encoders have 256 pulses per rotation (PPR)
    //Solenoid Id's for shifting
    public static int kDriveOutSolenoidId = 1;
    public static int kDriveInSolenoidId = 0;
    // Default state of the drive train transmission when in teleopInit, autoInit, and robotInit
    public static boolean kDriveDefaultHighGear = false;
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
}
