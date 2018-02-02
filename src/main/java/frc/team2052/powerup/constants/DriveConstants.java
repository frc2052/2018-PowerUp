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

//    public final static double kTrackScrubFactor = 0.65;
//    public final static double kTrackEffectiveDiameter = (27.25 * 27.25 + 13 * 13) / 27.25;  //33.45

    //TODO: Need to update these values
    public static double kTrackLengthInches = 8.265;
    public static double kTrackWidthInches = 23.8;
    public static double kTrackEffectiveDiameter = (kTrackWidthInches * kTrackWidthInches + kTrackLengthInches * kTrackLengthInches) / kTrackWidthInches;
    public final static double kTrackScrubFactor = 0.5;

    private static final int kDriveTicksPerRot = 1024; //grayhill 63R encoders have 256 pulses per rotation (PPR) x4edges because quadrature output

    //TODO: remove shifting solonoids
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
    public static double kPathFollowingMaxVel = 200;
    public static double kDriveHeadingVelocityKp = 5.0;
    public static double kDriveHeadingVelocityKi = 0.1;
    public static double kDriveHeadingVelocityKd = 60.0;
}
