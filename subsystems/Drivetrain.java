package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import main.java.frc.robot.OI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drivetrain extends Subsystem{
    
    double rightMotorSpeed;
    double leftMotorSpeed; 
    VictorSP m_frontLeft;
    VictorSP m_rearLeft;
    SpeedControllerGroup m_left;
    VictorSP m_frontRight;
    VictorSP m_rearRight;
    SpeedControllerGroup m_Right;
    DifferentialDrive m_drive;
    Encoder l_enc;
    Encoder r_enc;
    
    public Drivetrain() {
        m_frontLeft = new VictorSP(1);
    	m_rearLeft = new VictorSP(2);
    	m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    	m_frontRight = new VictorSP(3);
    	m_rearRight = new VictorSP(4);
        m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
        m_drive = new DifferentialDrive(m_left, m_right);
        l_enc = new Encoder(k_left_encoder_port_a,k_left_encoder_port_b);
        r_enc = new Encoder(k_right_encoder_port_a,k_right_encoder_port_b);
    }
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
    }
    public void drive(Joystick joystick){
        m_Drive.tankDrive(drivercontroller.getRawAxis(OI.AXIS_LY), drivercontroller.getRawAxis(OI.AXIS_RX));
    } 


    @Override
    public void initDefaultCommand() {
        //Set the default command for the subsystem here
        //uses: "setDefaultCommand(new <command>());"
    }
}

    