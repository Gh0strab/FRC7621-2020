package frc.robot.AutonomousRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutonomousCommands.*;
import frc.robot.commands.*;
public class AMidRun12Point extends CommandGroup{
    public AMidRun12Point(){
        addSequential(new ATrenchRun());
        addSequential(new Shoot());
    }
}




