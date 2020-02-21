package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.OI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Joystick;


public class Drivetrain extends Subsystem{
    
    double rightMotorSpeed;
    double leftMotorSpeed; 
    VictorSP m_frontLeft;
    VictorSP m_rearLeft;
    SpeedControllerGroup m_left;
    VictorSP m_frontRight;
    VictorSP m_rearRight;
    SpeedControllerGroup m_right;
    DifferentialDrive m_drivetrain;
    Encoder l_enc;
    Encoder r_enc;
    
    public Drivetrain() {
        m_frontLeft = new VictorSP(1);
    	m_rearLeft = new VictorSP(2);
    	m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    	m_frontRight = new VictorSP(3);
    	m_rearRight = new VictorSP(4);
        m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
        m_drivetrain = new DifferentialDrive(m_left, m_right);
        l_enc = new Encoder(0,1);
        r_enc = new Encoder(2,3);
    }
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(l_enc.getRate(), r_enc.getRate());
    }
    public void drive(Joystick drivercontroller){  
        m_drivetrain.tankDrive(drivercontroller.getRawAxis(OI.AXIS_LY), drivercontroller.getRawAxis(OI.AXIS_RX));
    } 
    public void tankDriveVolts(double leftVolts, double rightVolts) {
        m_left.setVoltage(leftVolts);
        m_right.setVoltage(-rightVolts);
        m_drivetrain.feed();
    }
    @Override
    public void initDefaultCommand() {
        //Set the default command for the subsystem here
        //uses: "setDefaultCommand(new <command>());"
    }
	public void arcadeDrive(double distanceAjust, double rotationAjust) {
	}
}

    