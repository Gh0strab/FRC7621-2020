package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {

    public Shooter() {
        requires(Robot.m_shooter);
        requires(Robot.m_vision);
    }

    @Override
    public void initDefaultCommand() {
        
    }
}