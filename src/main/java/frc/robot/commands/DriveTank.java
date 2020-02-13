package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveTank extends Command {
    public DriveTank(){
        requires(Robot.m_drivetrain);

    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {

    }

    //CALLED REPEATEDLY DURING COMMAND
    @Override
    protected void execute() {
        //DO THE DRIVING HERE
        
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        //STOP DRIVING HERE
    }

    @Override
    protected void interrupted() {
        end();
    }
}