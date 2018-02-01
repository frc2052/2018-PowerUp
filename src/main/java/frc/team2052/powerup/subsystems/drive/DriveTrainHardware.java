package frc.team2052.powerup.subsystems.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import frc.team2052.powerup.constants.DriveConstants;

/**
 * Created by KnightKrawler on 1/19/2018.
 */
class DriveTrainHardware {
    protected static final int kVelocityControlSlot = 0;

    final TalonSRX rightMaster;
    final TalonSRX leftMaster;
    private final TalonSRX rightSlave;
    private final TalonSRX leftSlave;

    AHRS navXGyro = null;
    //https://github.com/kauailabs/navxmxp/blob/master/roborio/java/navXMXP_Java_DataMonitor/src/org/usfirst/frc/team2465/robot/Robot.java
    private boolean isBrakeMode = true;


    DriveTrainHardware() {
        rightMaster = new TalonSRX(DriveConstants.kDriveRightMasterId);
        rightSlave = new TalonSRX(DriveConstants.kDriveRightSlaveId);

        leftMaster = new TalonSRX(DriveConstants.kDriveLeftMasterId);
        leftSlave = new TalonSRX(DriveConstants.kDriveLeftSlaveId);


        //todo: check if setting encoderticks is needed

        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kVelocityControlSlot, DriveConstants.kCANBusConfigTimeoutMS);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kVelocityControlSlot, DriveConstants.kCANBusConfigTimeoutMS);

        leftMaster.configOpenloopRamp(DriveConstants.kOpenLoopRampRate, DriveConstants.kCANBusConfigTimeoutMS);
        leftMaster.configClosedloopRamp(DriveConstants.kClosedLoopRampRate, DriveConstants.kCANBusConfigTimeoutMS);
        rightMaster.configOpenloopRamp(DriveConstants.kOpenLoopRampRate, DriveConstants.kCANBusConfigTimeoutMS);
        rightMaster.configClosedloopRamp(DriveConstants.kClosedLoopRampRate, DriveConstants.kCANBusConfigTimeoutMS);

        //Fix sensor polarity
        rightMaster.setInverted(false);
        rightSlave.setInverted(false);

        leftMaster.setInverted(true);
        leftSlave.setInverted(true);

        //Configure talons for follower mode
        rightSlave.set(ControlMode.Follower, rightMaster.getDeviceID());
        leftSlave.set(ControlMode.Follower, leftMaster.getDeviceID());

        // Load velocity control gains //todo: decide timeout seconds for
        leftMaster.config_kP(kVelocityControlSlot, DriveConstants.kDriveVelocityKp, DriveConstants.kCANBusConfigTimeoutMS);
        leftMaster.config_kI(kVelocityControlSlot, DriveConstants.kDriveVelocityKi, DriveConstants.kCANBusConfigTimeoutMS);
        leftMaster.config_kD(kVelocityControlSlot, DriveConstants.kDriveVelocityKd, DriveConstants.kCANBusConfigTimeoutMS);
        leftMaster.config_kF(kVelocityControlSlot, DriveConstants.kDriveVelocityKf, DriveConstants.kCANBusConfigTimeoutMS);
        leftMaster.config_IntegralZone(kVelocityControlSlot, DriveConstants.kDriveVelocityIZone, DriveConstants.kCANBusConfigTimeoutMS);

        rightMaster.config_kP(kVelocityControlSlot, DriveConstants.kDriveVelocityKp, DriveConstants.kCANBusConfigTimeoutMS);
        rightMaster.config_kI(kVelocityControlSlot, DriveConstants.kDriveVelocityKi, DriveConstants.kCANBusConfigTimeoutMS);
        rightMaster.config_kD(kVelocityControlSlot, DriveConstants.kDriveVelocityKd, DriveConstants.kCANBusConfigTimeoutMS);
        rightMaster.config_kF(kVelocityControlSlot, DriveConstants.kDriveVelocityKf, DriveConstants.kCANBusConfigTimeoutMS);
        rightMaster.config_IntegralZone(kVelocityControlSlot, DriveConstants.kDriveVelocityIZone, DriveConstants.kCANBusConfigTimeoutMS);

        leftMaster.configMotionCruiseVelocity(430, DriveConstants.kCANBusConfigTimeoutMS);//todo: decide timeout seconds
        rightMaster.configMotionCruiseVelocity(300,DriveConstants.kCANBusConfigTimeoutMS);

        try {
            /***********************************************************************
             * navX-MXP:
             * - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB.
             * - See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
             *
             * navX-Micro:
             * - Communication via I2C (RoboRIO MXP or Onboard) and USB.
             * - See http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
             *
             * Multiple navX-model devices on a single robot are supported.
             ************************************************************************/
            navXGyro = new AHRS(I2C.Port.kMXP);//todo: test if the gyro uses onboard
            //ahrs = new AHRS(SerialPort.Port.kMXP, SerialDataType.kProcessedData, (byte)50);
            navXGyro.enableLogging(true);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
            System.out.println("Error instantiating navX MXP:  " + ex.getMessage());
        }

        setBrakeMode(false);
    }

    public void setBrakeMode(boolean on) {
        if (isBrakeMode != on) {
            leftMaster.setNeutralMode(NeutralMode.Brake); //was break mode now uses enum
            leftSlave.setNeutralMode(NeutralMode.Brake);
            rightMaster.setNeutralMode(NeutralMode.Brake);
            rightSlave.setNeutralMode(NeutralMode.Brake);
            isBrakeMode = on;
        }
    }
}
