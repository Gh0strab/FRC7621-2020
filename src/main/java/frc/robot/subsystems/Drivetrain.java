package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.OI;
import frc.robot.Portmap;
import frc.robot.Robot;

public class Drivetrain extends Subsystem{
    
    double rightMotorSpeed;
    double leftMotorSpeed; 
    WPI_VictorSPX m_frontLeft;
    WPI_VictorSPX m_rearLeft;
    SpeedControllerGroup m_left;
    WPI_VictorSPX m_frontRight;
    WPI_VictorSPX m_rearRight;
    SpeedControllerGroup m_right;
    DifferentialDrive m_drivetrain;
    public Joystick driverController = new Joystick(0);
    
    public Drivetrain() {
        m_frontLeft = new WPI_VictorSPX(Portmap.DRIVETRAIN_LEFT_FRONT_SPX);
    	m_rearLeft = new WPI_VictorSPX(Portmap.DRIVETRAIN_LEFT_REAR_SPX);
    	m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    	m_frontRight = new WPI_VictorSPX(Portmap.DRIVETRAIN_RIGHT_FRONT_SPX);
    	m_rearRight = new WPI_VictorSPX(Portmap.DRIVETRAIN_RIGHT_REAR_SPX);
        m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
        m_drivetrain = new DifferentialDrive(m_left, m_right);
    }
    
    public void drive(){ 
        m_drivetrain.tankDrive(-driverController.getRawAxis(OI.AXIS_LY), -driverController.getRawAxis(OI.AXIS_RY));
    } 
    
    @Override
    public void initDefaultCommand() {

    }
	public void visionDrive(){
        m_drivetrain.arcadeDrive(Robot.m_vision.distanceAjust, Robot.m_vision.rotationAjust);
	}
}

    