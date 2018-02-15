package frc.team2052.powerup.subsystems.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import frc.team2052.powerup.Constants;

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
        rightMaster = new TalonSRX(Constants.kDriveRightMasterId);
        rightSlave = new TalonSRX(Constants.kDriveRightSlaveId);

        leftMaster = new TalonSRX(Constants.kDriveLeftMasterId);
        leftSlave = new TalonSRX(Constants.kDriveLeftSlaveId);



        leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kVelocityControlSlot, Constants.kCANBusConfigTimeoutMS);
        rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, kVelocityControlSlot, Constants.kCANBusConfigTimeoutMS);

        leftMaster.configOpenloopRamp(Constants.kDriveOpenLoopRampRate, Constants.kCANBusConfigTimeoutMS);
        leftMaster.configClosedloopRamp(Constants.kDriveClosedLoopRampRate, Constants.kCANBusConfigTimeoutMS);
        rightMaster.configOpenloopRamp(Constants.kDriveOpenLoopRampRate, Constants.kCANBusConfigTimeoutMS);
        rightMaster.configClosedloopRamp(Constants.kDriveClosedLoopRampRate, Constants.kCANBusConfigTimeoutMS);

        //Fix sensor polarity
        rightMaster.setInverted(true);
        rightSlave.setInverted(true);
        leftMaster.setInverted(false);
        leftSlave.setInverted(false);

        rightMaster.setSensorPhase(false);
        leftMaster.setSensorPhase(false);

        //Configure talons for follower mode
        rightSlave.set(ControlMode.Follower, rightMaster.getDeviceID());
        leftSlave.set(ControlMode.Follower, leftMaster.getDeviceID());

        // Load velocity control gains
        leftMaster.config_kP(kVelocityControlSlot, Constants.kDriveVelocityKp, Constants.kCANBusConfigTimeoutMS);
        leftMaster.config_kI(kVelocityControlSlot, Constants.kDriveVelocityKi, Constants.kCANBusConfigTimeoutMS);
        leftMaster.config_kD(kVelocityControlSlot, Constants.kDriveVelocityKd, Constants.kCANBusConfigTimeoutMS);
        leftMaster.config_kF(kVelocityControlSlot, Constants.kDriveVelocityKf, Constants.kCANBusConfigTimeoutMS);
        leftMaster.config_IntegralZone(kVelocityControlSlot, Constants.kDriveVelocityIZone, Constants.kCANBusConfigTimeoutMS);

        rightMaster.config_kP(kVelocityControlSlot, Constants.kDriveVelocityKp, Constants.kCANBusConfigTimeoutMS);
        rightMaster.config_kI(kVelocityControlSlot, Constants.kDriveVelocityKi, Constants.kCANBusConfigTimeoutMS);
        rightMaster.config_kD(kVelocityControlSlot, Constants.kDriveVelocityKd, Constants.kCANBusConfigTimeoutMS);
        rightMaster.config_kF(kVelocityControlSlot, Constants.kDriveVelocityKf, Constants.kCANBusConfigTimeoutMS);
        rightMaster.config_IntegralZone(kVelocityControlSlot, Constants.kDriveVelocityIZone, Constants.kCANBusConfigTimeoutMS);

        leftMaster.configMotionCruiseVelocity(430, Constants.kCANBusConfigTimeoutMS);
        rightMaster.configMotionCruiseVelocity(430, Constants.kCANBusConfigTimeoutMS);

        leftMaster.configAllowableClosedloopError(kVelocityControlSlot, Constants.kDriveVelocityAllowableError, Constants.kCANBusConfigTimeoutMS);
        leftMaster.selectProfileSlot(kVelocityControlSlot,kVelocityControlSlot);
        rightMaster.selectProfileSlot(kVelocityControlSlot, kVelocityControlSlot);
        rightMaster.configAllowableClosedloopError(kVelocityControlSlot, Constants.kDriveVelocityAllowableError, Constants.kCANBusConfigTimeoutMS);
        setBrakeMode(true);

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
            navXGyro = new AHRS(SPI.Port.kMXP);
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
