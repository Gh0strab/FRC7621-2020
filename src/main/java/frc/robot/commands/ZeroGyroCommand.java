package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
public class ZeroGyroCommand extends InstantCommand {
 
public static final AHRS ahrs = new AHRS(SPI.Port.kMXP);
  @Override
  protected void initialize() {
    ahrs.reset();
    
  }
}