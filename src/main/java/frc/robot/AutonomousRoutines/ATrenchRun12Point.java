package frc.robot.AutonomousRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutonomousCommands.*;
import frc.robot.commands.*;
public class ATrenchRun12Point extends CommandGroup{
    public ATrenchRun12Point(){
        addSequential(new ATrenchRun());
        addSequential(new Shoot());
    }
}
