package frc.robot.AutonomousRoutines;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import java.io.File;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import frc.robot.commands.DriveTank;
import frc.robot.AutonomousCommands.*;

public class AMidRun12Point extends CommandGroup{
    private static final int k_ticks_per_rev = 4096;
	private static final double k_wheel_diameter = 4.0 / 12.0;
	private static final double k_max_velocity = 10;
	private static final int k_left_encoder_port_a = 0;
	private static final int k_left_encoder_port_b = 1;
	private static final int k_right_encoder_port_a = 2;
    private static final int k_right_encoder_port_b = 3;
    private static final String k_path_name = "DriveForwardThroughTrench";
    
    Filesystem filesystem;
    Timer m_timer;
    Encoder l_enc;
    Encoder r_enc;
    EncoderFollower l_enc_follower;
    EncoderFollower r_enc_follower;
    Notifier m_enc_notifier;

    public AMidRun12Point(){
        requires(Robot.m_drivetrain);
    }

    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {
       
        m_timer = new Timer();
        l_enc = new Encoder(k_left_encoder_port_a,k_left_encoder_port_b);
        r_enc = new Encoder(k_right_encoder_port_a,k_right_encoder_port_b);
        Trajectory trajectory = TrajectoryUtil.fromPathweaverJson(Paths.get("/home/lvuser/deploy/DriveForwardThroughTrench.wpilib.json"));
        Trajectory left_trajectory = PathfinderFRC.getTrajectory(k_path_name + ".left");
        Trajectory right_trajectory = PathfinderFRC.getTrajectory(k_path_name + ".right");
        
        l_enc_follower = new EncoderFollower(left_trajectory);
        r_enc_follower = new EncoderFollower(right_trajectory);

        l_enc_follower.configureEncoder(l_enc.get(), k_ticks_per_rev, k_wheel_diameter);

        l_enc_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);

        r_enc_follower.configureEncoder(r_enc.get(), k_ticks_per_rev, k_wheel_diameter);

        r_enc_follower.configurePIDVA(1.0, 0.0, 0.0, 1 / k_max_velocity, 0);

        m_enc_notifier = new Notifier(this::followPath);
        m_enc_notifier.startPeriodic(left_trajectory.get(0).dt);
    }

}





