//idk if this works
package org.team2471.frc.steamworks.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ZeroGyroCommand extends InstantCommand {
public static final Gyro gyro = new Gyro(new NavX());
  @Override
  protected void initialize() {
    gyro.reset();
  }
}
