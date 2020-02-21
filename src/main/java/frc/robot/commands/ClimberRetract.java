package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ClimberRetract extends InstantCommand {
    public ClimberRetract(){
        requires(Robot.m_climber);
    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {
        Robot.m_climber.ClimberRetract();

    }
}