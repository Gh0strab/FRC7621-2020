package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class IntakeOff extends InstantCommand {
    public IntakeOff(){
        requires(Robot.m_intake);
    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {
        Robot.m_intake.TurnOff();

    }
}