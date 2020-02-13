package frc.robot.AutonomousRoutines;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutonomousCommands.*;
import frc.robot.commands.*;
public class ATrenchRun24Point extends CommandGroup{
    public ATrenchRun24Point(){
        addSequential(new ATrenchRun());
        addSequential(new Shoot());
        addSequential(new PickUpTwoBallsTrenchRun());
        addParallel(new IntakeOn());
        addSequential(new TwoBallstoLine());
        addSequential(new Shoot());
    }
}