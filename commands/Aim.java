package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
public class Aim extends Command {
  public Aim(){
      requires(Robot.m_vision);
  }

  //CALLED THE FIRST TIME COMMAND RUNS
  @Override
  protected void initialize() {
    Robot.m_vision.Visionturnon();
  }

  //CALLED REPEATEDLY DURING COMMAND
  @Override
  protected void execute() {

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