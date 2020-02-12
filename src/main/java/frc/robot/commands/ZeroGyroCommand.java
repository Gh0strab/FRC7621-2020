package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.I2C;
//import com.kauailabs.navx.frc.AHRS;

public class ZeroGyroCommand extends InstantCommand {
  //ahrs = new AHRS(I2C.kMXP);
public static final Gyro gyro = new Gyro(new ahrs());
  @Override
  protected void initialize() {
    gyro.reset();
  }
}