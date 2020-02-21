
//									SVHS IRON RANGERS CODE FOR THE 2020 SEASON
//**************************************************************************************************************

//package org.usfirst.frc.team7621.robot;

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;//need this and smart dashboard for auto choice
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.playingwithfusion.TimeOfFlight;
import frc.robot.commands.ZeroGyroCommand;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Climber;
import frc.robot.AutonomousRoutines.*;

//************************************************************************************************************************

public class Robot extends TimedRobot {
	
	private static Command defaultAutoCommand;
	public static final boolean SKIP_GYRO_CALIBRATION = false;
	public static Drivetrain m_drivetrain;
	public static Intake m_intake;
	public static SendableChooser <Command> autoChooser;
	public static OI m_OI;
	public static Vision m_vision;
	public static Shooter m_shooter;
	public static Climber m_climber;
	VictorSPX IndexingMotor = new VictorSPX(6);
	Spark ShootingMotor = new Spark(1);
	TimeOfFlight Intake_sensor = new TimeOfFlight(3);
	Solenoid LightRing = new Solenoid(6,7);
	Compressor c = new Compressor(0);
	
//*********************************************************************************************************************************************
	//Initilazition function
	public void robotInit(){
		m_OI = new OI();
		m_drivetrain = new Drivetrain();
		m_intake = new Intake();
		m_vision = new Vision();
		m_shooter = new Shooter();
		m_climber = new Climber();

		//NavX Calibration
		if(SKIP_GYRO_CALIBRATION) {
			System.out.println("Skipping gyro calibration");
		  } else {
			System.out.println("Calibrating Gyro...");
			ZeroGyroCommand.ahrs.isCalibrating();
			System.out.println("Gyro calibrated");
		  }

		//Selector for Auto
		autoChooser = new SendableChooser<>();
		autoChooser.setDefaultOption("AMoveAndShoot", defaultAutoCommand);
		
		SmartDashboard.putData("AutoChooser", autoChooser);
		SmartDashboard.putData(new ZeroGyroCommand());


			//Points "table" to the NetworkTable database called "chameleon-vision"

//**************************************************************************************************************************

	@Override
	public void autonomousInit() {
	    if (autoChooser != null) {
			Command autonomousCommand = autoChooser.getSelected();
			if (autonomousCommand != null) {
				  autonomousCommand.start();
					} else {
			  System.out.println("Auto Null Warning #1");
			  defaultAutoCommand.start();
				}
				  } else {
				System.out.println("Auto Null Warning #2");
				defaultAutoCommand.start();
					  }
			  ZeroGyroCommand.ahrs.reset();
		  
	}

//**************************************************************************************************************************************************

	@Override
	public void autonomousPeriodic() {	





	}

//**************************************************************************************************************************************************
	
	@Override
	public void teleopPeriodic() {
	
				

	}
}

