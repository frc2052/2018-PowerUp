package frc.team2052.powerup;

/**
 * Created by KnightKrawler on 2/9/2018.
 */
public class Constants {public static int intakeSolenoid =1;

    public final static int kCANBusConfigTimeoutMS = 10;

    /////////// DEVICE IDs ////////////
    public static int kPDPId = 0;
    public static int kDriveRightMasterId = 4;
    public static int kDriveRightSlaveId = 3;
    public static int kDriveLeftSlaveId = 2;
    public static int kDriveLeftMasterId = 1;
    public static int kElevatorMotorID = 7;
    public static int pickupRightMotorId =8;
    public static int pickupLeftMotorId =9;

    public static int kRampLeftServoId = 0;//todo: find values
    public static int kRampRightServoId = 1;


    /////////// Solenoids ////////////
    public static int armLongSolenoidOut = 3;
    public static int armLongSolenoidIn = 2;
    public static int armShortSolenoidOut =0;
    public static int armShortSolenoidIn = 1;
    public static int jawsSolenoidOut =21;
    public static int jawsSolenoidIn =22;

    public static int kRightRampInId = 7;
    public static int kRightRampOutId = 5;
    public static int kLeftRampInId = 6;
    public static int kLeftRampOutId = 4;

    /////////// Intake ////////////
    public static double intakeInSpeedLeft = 1;
    public static double intakeInSpeedRight = intakeInSpeedLeft * .8;
    public static double intakeOutSpeed = -.35;
    public static double intakeStopSpeed = .1;

    /////////// Ramp ////////////
    public static int kRampRightServoReleaseAngle = 150;
    public static int kRampRightServoClosedAngle = 0;
    public static int kRampLeftServoReleaseAngle = 90;
    public static int kRampLeftServoClosedAngle = 0;

    /////////// Buttons ////////////
    //SECONDARY
    public static int kElevatorPickupHeightButton = 8;
    public static int kElevatorSwitchHeightButton = 2;
    public static int kElevatorScale_OneHeightButton = 4;
    public static int kElevatorScale_TwoHeightButton = 3;
    public static int kElevatorScale_ThreeHeightButton = 5;
    public static int kElevatorScale_TrimUpButton =  6;
    public static int kElevatorScale_TrimDownButton =  7;
    public static int kManualElevator_RaiseButton = 11; //emergency manual elevator control
    public static int kManualElevator_LowerButton = 12; //-Find manual elevator buttons

    public static int kJoystickDropLeftPin = 9;
    public static int kJoystickDropRightPin = 9;
    public static int kJoystickRaiseRightRamp = 5;
    public static int kJoystickRaiseLeftRamp = 5;
    public static int kJoystickLowerLeftRamp = 2;
    public static int kJoystickLowerRightRamp = 2;

    public static int kJoystickIntakeUp = 9;
    public static int  kJoystickIntakeStartConfig = 10;

    /////////// Elevator ////////////
    public static int kElevatorMaxHeight = 74; //50 for testing 74 for real
    public static int kElevatorMinHeight = 0;
    public static double kElevatorInchesPerRotation = 74/8; // total travel distance for elevator is 74 inches in 8 revolutions
    public static double kElevatorVelocityKp = 1;
    public static double kElevatorVelocityKi = 0;
    public static double kElevatorVelocityKd = 0;
    public static double kElevatorVelocityKf = 0;
    public static int kElevatorVelocityIZone = 0;
    public static int kElevatorTicksPerRot = 1024 * 4;

    public static int kElevatorSwitchHeight = 32;
    public static int kElevatorScaleBalancedHeight = 64;
    public static int kElevatorRampSeconds = 0;
    public static double kElevatorPeakUpPower = .7;
    public static double kElevatorPeakDownPower = .1;

    /////////// Control Loops ////////////
    public static final double kControlLoopPeriod = 1.0 / 100.0;
    public static final double kSlowControlLoopPeriod = 1.0 / 10.0;

    /////////// Drive ////////////
    //grayhill 63R encoders have 256 pulses per rotation (PPR) x4edges because quadrature output
    public static int kDriveEncoderTicksPerRot = 1024; //encoder is directly on the wheel making the raio 1:1
    public static double kDriveWheelDiameterInches = 6.0;
    public static double kDriveVelocityKp = 0.2;
    public static double kDriveVelocityKi = 0.0;
    public static double kDriveVelocityKd = 2.0;
    public static double kDriveVelocityKf = 0.3;
    public static int kDriveVelocityIZone = 0;
    public static double kDriveVelocityRampRate = 0.0;
    public static int kDriveVelocityAllowableError = 0;
    public static double kPathFollowingLookahead = 24;
    public static double kPathFollowingMinVel = 40;
    public static double kPathFollowingMaxAccel = 150;
    public static double kPathFollowingMaxVel = 250;
    public static double kDriveHeadingVelocityKp = 5.0;
    public static double kDriveHeadingVelocityKi = 0.1;
    public static double kDriveHeadingVelocityKd = 60.0;
    public static double kTrackLengthInches = 11.75;
    public static double kTrackWidthInches = 23;
    public static double kTrackEffectiveDiameter = (kTrackWidthInches * kTrackWidthInches + kTrackLengthInches * kTrackLengthInches) / kTrackWidthInches;
    public final static double kTrackScrubFactor = 0.5;
    //Hornet    public final static double kTrackScrubFactor = 0.65;
    //Hornet    public final static double kTrackEffectiveDiameter = (27.25 * 27.25 + 13 * 13) / 27.25;  //33.45
    public static double kDriveOpenLoopRampRate = .5; // this many seconds from 0 to full power
    public static double kDriveClosedLoopRampRate = 0.0;
}
