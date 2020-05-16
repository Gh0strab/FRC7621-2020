package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
public class Aim extends Command {
  public Aim(){
      requires(Robot.m_vision);
    
  }

  //CALLED THE FIRST TIME COMMAND RUNS
  @Override
  protected void initialize() {
    
  }

  //CALLED REPEATEDLY DURING COMMAND
  @Override
  protected void execute() {
    Robot.m_vision.Aim();
  }

  @Override
  protected boolean isFinished() {
      return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
      end();
  }
}