package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class WinchStop extends InstantCommand {
    public WinchStop(){
        requires(Robot.m_climber);
    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {
        Robot.m_climber.WinchStop();

    }
}