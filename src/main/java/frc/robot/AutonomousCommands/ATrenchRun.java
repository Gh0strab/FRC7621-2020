package frc.robot.AutonomousCommands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import java.io.File;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.ZeroGyroCommand;

public class ATrenchRun extends CommandGroup{
    
    private static final int k_ticks_per_rev = 4096;
	private static final double k_wheel_diameter = 4.0 / 12.0;
	private static final double k_max_velocity = 10;
	private static final int k_left_encoder_port_a = 0;
	private static final int k_left_encoder_port_b = 1;
	private static final int k_right_encoder_port_a = 2;
    private static final int k_right_encoder_port_b = 3;
    private static String trajectoryJSON = "output/ATrenchRun.wpilib.json";
    private static final String k_path_name = trajectoryJSON;
    
    Filesystem filesystem;
    Timer m_timer;
    Encoder l_enc;
    Encoder r_enc;
    EncoderFollower l_enc_follower;
    EncoderFollower r_enc_follower;
    Notifier m_enc_notifier;
    AHRS ahrs;

    double rightMotorSpeed;
    double leftMotorSpeed; 
    VictorSP m_frontLeft;
    VictorSP m_rearLeft;
    SpeedControllerGroup m_left;
    VictorSP m_frontRight;
    VictorSP m_rearRight;
    SpeedControllerGroup m_right;
    DifferentialDrive m_drive;

    public ATrenchRun(){
        requires(Robot.m_drivetrain);
    }
    
    //CALLED THE FIRST TIME COMMAND RUNS
    @Override
    protected void initialize() {

        m_frontLeft = new VictorSP(1);
    	m_rearLeft = new VictorSP(2);
    	m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    	m_frontRight = new VictorSP(3);
    	m_rearRight = new VictorSP(4);
        m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
		m_drive = new DifferentialDrive(m_left, m_right);
        m_timer = new Timer();
        l_enc = new Encoder(k_left_encoder_port_a,k_left_encoder_port_b);
        r_enc = new Encoder(k_right_encoder_port_a,k_right_encoder_port_b);

        Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
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
    private void followPath() {
        if (l_enc_follower.isFinished() || r_enc_follower.isFinished()) {
          m_enc_notifier.stop();
        } else {
          double left_speed = l_enc_follower.calculate(l_enc.get());
          double right_speed = r_enc_follower.calculate(r_enc.get());
          double heading = ahrs.getAngle();
          double desired_heading = Pathfinder.r2d(l_enc_follower.getHeading());
          double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
          double turn =  0.8 * (-1.0/80.0) * heading_difference;
          m_left.set(left_speed + turn);
          m_right.set(right_speed - turn);
        }
      }
    

}