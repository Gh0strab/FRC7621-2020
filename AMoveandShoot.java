package main.java.frc.robot.AutonomousCommands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import java.io.File;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.subsystems.Drivetrain;
import main.java.frc.robot.Constants.AutoConstants;
import main.java.frc.robot.Constants.DriveConstants;
import frc.robot.commands.ZeroGyroCommand;

public class AMoveandShoot extends CommandGroup{
    Filesystem filesystem;
    public static Drivetrain m_drivetrain;

    public AMoveandShoot(){
        requires(Robot.m_drivetrain);
    }
    
    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {

        m_drivetrain = new Drivetrain();

        var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                       DriveConstants.kvVoltSecondsPerMeter,
                                       DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);


         TrajectoryConfig config =
        new TrajectoryConfig(AutoConstants.kMaxSpeedMetersPerSecond,
                             AutoConstants.kMaxAccelerationMetersPerSecondSquared)
            
            .setKinematics(DriveConstants.kDriveKinematics)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);

        String trajectoryJSON = "paths/AMoveandShoot.wpilib.json";
                try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
            Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
                } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
                }
                RamseteCommand ramseteCommand = new RamseteCommand(
                    trajectory,
                    m_drivetrain::getPose,
                    new RamseteController(AutoConstants.kRamseteB, AutoConstants.kRamseteZeta),
                    new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                               DriveConstants.kvVoltSecondsPerMeter,
                                               DriveConstants.kaVoltSecondsSquaredPerMeter),
                    DriveConstants.kDriveKinematics,
                    m_drivetrain::getWheelSpeeds,
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    new PIDController(DriveConstants.kPDriveVel, 0, 0),
                    // RamseteCommand passes volts to the callback
                    m_drivetrain::tankDriveVolts,
                    m_drivetrain
                );
            
                // Run path following command, then stop at the end.
                return ramseteCommand.andThen(() -> m_drivetrain.tankDriveVolts(0, 0));
              }
            }