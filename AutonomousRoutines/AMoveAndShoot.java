package frc.robot.AutonomousRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutonomousCommands.*;
import frc.robot.commands.*;
import main.java.frc.robot.AutonomousCommands.AMoveandShoot;
public class ATrenchRun24Point extends CommandGroup{
    public ATrenchRun24Point(){
        addSequential(new AMoveandShoot());
        addSequential(new Aim());
        addSequential(new Shoot());
    }
}