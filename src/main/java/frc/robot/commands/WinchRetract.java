package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class WinchRetract extends InstantCommand {
    public WinchRetract(){
        requires(Robot.m_climber);
    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {
        Robot.m_climber.WinchRetract();

    }
}