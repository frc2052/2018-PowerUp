package frc.team2052.powerup.subsystems.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.team2052.powerup.constants.CANConstants;
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
    //ADIS16448_IMU gyro; //todo:decide what gyro we have
    private boolean isBrakeMode = true;


    DriveTrainHardware() {
        rightMaster = new TalonSRX(CANConstants.kDriveRightMasterId);
        rightSlave = new TalonSRX(CANConstants.kDriveRightSlaveId);

        leftMaster = new TalonSRX(CANConstants.kDriveLeftMasterId);
        leftSlave = new TalonSRX(CANConstants.kDriveLeftSlaveId);


        //Set how many encoder ticks per revolution of the wheels
        //todo: check if setting the encoder is needed
        /*
        leftMaster.
        rightMaster.configEncoderCodesPerRev(DriveConstants.kDriveEncoderTicksPerRot);
         */
        //Fix sensor polarity
        leftMaster.setInverted(false);
        rightMaster.setInverted(true);

        leftMaster.setInverted(true);
        rightMaster.setInverted(false);

        //Configure talons for follower mode
        rightSlave.set(ControlMode.Follower, rightMaster.getDeviceID());

        leftSlave.set(ControlMode.Follower, leftMaster.getDeviceID());

        // Load velocity control gains //todo: decide timeout seconds for
        leftMaster.config_kP(kVelocityControlSlot, DriveConstants.kDriveVelocityKp, 10);//todo: what is slotldx
        leftMaster.config_kI(kVelocityControlSlot, DriveConstants.kDriveVelocityKi, 10);
        leftMaster.config_kD(kVelocityControlSlot, DriveConstants.kDriveVelocityKd, 10);
        leftMaster.config_kF(kVelocityControlSlot, DriveConstants.kDriveVelocityKf, 10);
        leftMaster.config_IntegralZone(kVelocityControlSlot, DriveConstants.kDriveVelocityIZone, 10);
        /*leftMaster.setPID( DriveConstants.kDriveVelocityKi, DriveConstants.kDriveVelocityKd,
                DriveConstants.kDriveVelocityKf, DriveConstants.kDriveVelocityIZone, DriveConstants.kDriveVelocityRampRate,
                kVelocityControlSlot);*/
        //todo: is DriveConstants.kDriveVelocityRampRate and kVelocityControlSlot needed
        rightMaster.config_kP(kVelocityControlSlot, DriveConstants.kDriveVelocityKp, 10);//todo: what is slotldx
        rightMaster.config_kI(kVelocityControlSlot, DriveConstants.kDriveVelocityKi, 10);
        rightMaster.config_kD(kVelocityControlSlot, DriveConstants.kDriveVelocityKd, 10);
        rightMaster.config_kF(kVelocityControlSlot, DriveConstants.kDriveVelocityKf, 10);
        rightMaster.config_IntegralZone(kVelocityControlSlot, DriveConstants.kDriveVelocityIZone, 10);
        /*rightMaster.setPID(DriveConstants.kDriveVelocityKp, DriveConstants.kDriveVelocityKi, DriveConstants.kDriveVelocityKd,
                DriveConstants.kDriveVelocityKf, DriveConstants.kDriveVelocityIZone, DriveConstants.kDriveVelocityRampRate,
                kVelocityControlSlot); */
        //todo: is DriveConstants.kDriveVelocityRampRate and kVelocityControlSlot needed

        leftMaster.configMotionCruiseVelocity(430, 10);//todo: decide timeout seconds
        rightMaster.configMotionCruiseVelocity(300,10);

       // gyro = new ADIS16448_IMU();

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
