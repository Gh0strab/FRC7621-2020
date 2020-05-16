
//									SVHS IRON RANGERS CODE FOR THE 2020 SEASON
//**************************************************************************************************************

//package org.usfirst.frc.team7621.robot;

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;//need this and smart dashboard for auto choice
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.AutonomousRoutines.MoveAndShoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Gyro;

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
	public static Gyro m_gyro;
	public static Indexer m_indexer;
	public Joystick driverController = new Joystick(0);
	Compressor c = new Compressor(0);
	
//*********************************************************************************************************************************************
	//Initilazition function
	public void robotInit(){
		
		m_drivetrain = new Drivetrain();
		m_intake = new Intake();
		m_indexer = new Indexer();
		m_vision = new Vision();
		m_shooter = new Shooter();
		m_climber = new Climber();
		m_gyro = new Gyro();
		m_OI = new OI();
		
		//NavX Calibration
		if(SKIP_GYRO_CALIBRATION) {
			System.out.println("Skipping gyro calibration");
		  } else {
			System.out.println("Calibrating Gyro...");
			m_gyro.isCalibrating();
			System.out.println("Gyro calibrated");

		  }
		  autoChooser = new SendableChooser<>();
		  autoChooser.addOption("MoveAndShoot", new MoveAndShoot());
		  autoChooser.addOption("default", defaultAutoCommand);
		  SmartDashboard.putData("AutoChooser", autoChooser);
	}
		  
		


			

//**************************************************************************************************************************

	@Override
	public void autonomousInit() {
		
	    if (autoChooser != null) {
			Command autonomousCommand = autoChooser.getSelected();
			
			if (autonomousCommand != null) {
				  autonomousCommand.start();
					} else {
			  System.out.println("Auto Null Warning #1");
			  //defaultAutoCommand.start();
				}
				  } else {
				System.out.println("Auto Null Warning #2");
				//defaultAutoCommand.start();
					  }
			
					
			  m_gyro.reset();
			 
		  
	}

//**************************************************************************************************************************************************

	@Override
	public void autonomousPeriodic() {	
		 
    
    




	}

//**************************************************************************************************************************************************
	
	@Override
	public void teleopPeriodic() {
		//DRIVE
		m_drivetrain.drive();

		//INTAKE
		if(driverController.getRawAxis(OI.BUTNUM_LT)>.05){
			m_intake.TurnOn();
		}else{
			m_intake.TurnOff();
		}

		//SHOOTER
		if(driverController.getRawAxis(OI.BUTNUM_RT)>.05){
			m_vision.Aim();
			m_shooter.Shoot();
		  	//m_shooter.set(Constants.getConstants().debugShooterSet);
		}else{																																																																																																																																																					
			m_vision.StopAim();
			m_shooter.StopShoot();
			//m_shooter.stop();
		}

		//CLIMBER		
		if(driverController.getRawButton(OI.BUTNUM_A)){
			m_climber.ClimberExtend();
		}else{
			m_climber.ClimberStop();
			if(driverController.getRawButton(OI.BUTNUM_B)){
				m_climber.ClimberRetract();
			}else{
				m_climber.ClimberStop();
		}

		//WINCH
		if(driverController.getRawButton(OI.BUTNUM_X)){
			m_climber.WinchRetract();
			}else{
			m_climber.WinchStop();
		}

		//MOVE INDEXER
		if(driverController.getRawButton(OI.BUTNUM_LB)){
			m_indexer.Index();
			}else{
			m_indexer.IndexStop();
		}

		//OUTTAKE BALLS
		if(driverController.getRawButton(OI.BUTNUM_RB)){
			m_intake.OutTake();
			m_indexer.OutTake();
		}

		}
	}
	@Override
	public void testPeriodic() {
			if(driverController.getRawButton(OI.BUTNUM_B)){
				m_climber.WinchReset();
			}else{
				m_climber.WinchStop();
			
			}
	}
}

