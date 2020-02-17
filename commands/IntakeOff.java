package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeOff extends Command {
    public IntakeOff(){
        requires(Robot.m_intake);
    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {

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