package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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

//driver motor control
    VictorSPX leftRearDrive = new VictorSPX(4);
    VictorSPX rightRearDrive  = new VictorSPX(1);
    VictorSPX leftFrontDrive = new VictorSPX(3);
    VictorSPX rightFrontDrive = new VictorSPX(0);
    DifferentialDrive drive;

    public Drivetrain() {
        //INITIALIZE YOUR VARIABLES HERE

        //SET SPEEDCONTROLLERGROUP(S) HERE

        //INITIALIZE ENCODERS HERE
    }

    public void drive(Joystick joystick){
/*       	//This makes us go fast
    if (JoyStick.getRawButton(OI.X)) {	
        double throttleValue = JoyStick.getRawAxis(1);
        double turnValue = JoyStick.getRawAxis(4);
        rightMotorSpeed = throttleValue + turnValue;
        leftMotorSpeed = -1 * (throttleValue - turnValue);
            leftFrontDrive.set(ControlMode.PercentOutput, leftMotorSpeed * 1);
            rightFrontDrive.set(ControlMode.PercentOutput, rightMotorSpeed * 1);
        }else{
            double throttleValue = JoyStick.getRawAxis(1);
            double turnValue = JoyStick.getRawAxis(4);
            rightMotorSpeed = throttleValue + turnValue;
            leftMotorSpeed = -1 * (throttleValue - turnValue);
            leftFrontDrive.set(ControlMode.PercentOutput, leftMotorSpeed * .40);
            rightFrontDrive.set(ControlMode.PercentOutput, rightMotorSpeed * .40);
            */
        double throttleValue = joystick.getRawAxis(1);
        double turnValue = joystick.getRawAxis(4);
        rightMotorSpeed = throttleValue + turnValue;
        leftMotorSpeed = -1 * (throttleValue - turnValue);
            leftFrontDrive.set(ControlMode.PercentOutput, leftMotorSpeed * 1);
            rightFrontDrive.set(ControlMode.PercentOutput, rightMotorSpeed * 1);
    } 


    @Override
    public void initDefaultCommand() {
        //Set the default command for the subsystem here
        //uses: "setDefaultCommand(new <command>());"
    }
}

    