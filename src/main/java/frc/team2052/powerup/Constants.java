package frc.team2052.powerup;

/**
 * Created by KnightKrawler on 2/9/2018.
 */
public class Constants {public static int intakeSolenoid =1;

    public final static int kCANBusConfigTimeoutMS = 10;

    /////////// Motor IDs ////////////
    public static int kDriveRightMasterId = 1;
    public static int kDriveRightSlaveId = 2;
    public static int kDriveLeftSlaveId = 3;
    public static int kDriveLeftMasterId = 4;
    public static int kElevatorMotorID = 7;
    public static int intakeRightMotorId =8;
    public static int intakeLeftMotorId =9;
//todo: decide which constants are good

    /////////// Solenoids ////////////
    public static int inOutSolenoid =1;
    public static int upDownLSolenoidIn =2;
    public static int upDownSolenoid1Out =3;
    public static int upDownSolenoid2In =4;
    public static int upDownSolenoid2Out =5;
    public static int intakeSolenoidLiftIn1 =7;
    public static int intakeSolenoidLiftOut1 =8;
    public static int intakeSolenoidLiftIn2 =9;
    public static int intakeSolenoidLiftOut2 =10;
    public static int kRampPinLeftInId = 0;
    public static int kRampPinLeftOutId = 1;
    public static int kRampPinRightInId = 2;
    public static int kRampPinRightOutId = 3;
    public static int kRightRampInId = 4;
    public static int kRightRampOutId = 5;
    public static int kLeftRampInId = 6;
    public static int kLeftRampOutId = 7;

    /////////// Intake ////////////
    public static double intakeInSpeed = 1;
    public static double intakeOutSpeed = -.6;
    public static double intakeStopSpeed = 0;

    /////////// Buttons ////////////
    public static int kElevatorPickupHeightButton = 8;
    public static int kElevatorSwitchHeightButton = 2;
    public static int kElevatorScale_OneHeightButton = 4;
    public static int kElevatorScale_TwoHeightButton = 3;
    public static int kElevatorScale_ThreeHeightButton = 5;
    public static int kJoystickDropLeftPin = 8;
    public static int kJoystickDropRightPin = 9;
    public static int kJoystickRaiseRightRamp = 11;
    public static int kJoystickRaiseLeftRamp = 6;
    public static int kJoystickLowerLeftRamp = 7;
    public static int kJoystickLowerRightRamp = 10;
    public static int kJoystickPullWinch = 7;
    public static int kJoystickClosed = 3;
    public static int kJoystickOpenOff = 60;
    public static int kJoystickCloseClamp = 11;
    public static int kJoystickOpenClamp = 10;
    public static int kJoystickIntakeUp = 9;

    /////////// Elevator ////////////
    public static int kElevatorMaxHeight = 50; //50 for testing 74 for real
    public static int kElevatorMinHeight = 2;
    public static double kElevatorInchesPerRotation = 74/8; // total travel distance for elevator is 74 inches in 8 revolutions
    public static double kElevatorVelocityKp = 1;
    public static double kElevatorVelocityKi = 0;
    public static double kElevatorVelocityKd = 0;
    public static double kElevatorVelocityKf = 0;
    public static int kElevatorVelocityIZone = 0;
    public static int kElevatorTicksPerRot = 1024 * 4;

    public static int kElevatorSwitchHeight = 25;
    public static int kElevatorScaleBalancedHeight = 64;
    public static int kElevatorRampSeconds = 0;
    public static double kElevatorPeakPower = .4;

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
    public static double kPathFollowingMaxAccel = 100;
    public static double kPathFollowingMaxVel = 200;
    public static double kDriveHeadingVelocityKp = 5.0;
    public static double kDriveHeadingVelocityKi = 0.1;
    public static double kDriveHeadingVelocityKd = 60.0;
    public static double kTrackLengthInches = 8.265;//TODO: Need to update these values
    public static double kTrackWidthInches = 23.8;
    public static double kTrackEffectiveDiameter = (kTrackWidthInches * kTrackWidthInches + kTrackLengthInches * kTrackLengthInches) / kTrackWidthInches;
    public final static double kTrackScrubFactor = 0.5;
    //Hornet    public final static double kTrackScrubFactor = 0.65;
    //Hornet    public final static double kTrackEffectiveDiameter = (27.25 * 27.25 + 13 * 13) / 27.25;  //33.45
    public static double kDriveOpenLoopRampRate = .5; // this many seconds from 0 to full power
    public static double kDriveClosedLoopRampRate = 0.0;
}
