package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeOn extends Command {
    public IntakeOn(){
        requires(Robot.m_intake);
    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {
        Robot.m_intake.turnon();
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