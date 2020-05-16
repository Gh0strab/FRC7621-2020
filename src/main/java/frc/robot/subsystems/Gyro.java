package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Gyro extends Subsystem {

    public static final AHRS ahrs = new AHRS(SPI.Port.kMXP);

    public Gyro() {

    }

    @Override
    public void initDefaultCommand() {
        //Set the default command for the subsystem here
    }

    public void reset() {
        ahrs.reset();
    }

    public boolean isCalibrating() {
        return ahrs.isCalibrating();
    }
}

