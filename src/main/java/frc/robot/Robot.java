
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
import frc.robot.AutonomousRoutines.*;

//************************************************************************************************************************

public class Robot extends TimedRobot {
	
	private static Command defaultAutoCommand;
	public static final boolean SKIP_GYRO_CALIBRATION = false;
	public static Drivetrain m_drivetrain;
	public static Intake m_intake;
	public static SendableChooser <Command> autoChooser;
	public static OI m_OI;
	
	//vision motor control
	double rightMotorSpeed;
	double leftMotorSpeed; 
	VictorSP m_frontLeft;
	VictorSP m_rearLeft;
	SpeedControllerGroup m_left;
	VictorSP m_frontRight;
	VictorSP m_rearRight;
	SpeedControllerGroup m_Right;
	DifferentialDrive m_drive;

	VictorSPX IndexingMotor = new VictorSPX(6);
	Spark ShootingMotor = new Spark(1);
	TimeOfFlight Intake_sensor = new TimeOfFlight(3);
	Joystick JoyStick = new Joystick(0);
	Solenoid LightRing = new Solenoid(6,7);
	Compressor c = new Compressor(0);

	NetworkTable table;
	NetworkTableEntry targetX;
	NetworkTableEntry targetY;

	double rotationError;
	double distanceError;
	double KpRot=-0.1;
	double KpDist=-0.1;
	double angleTolerance=5;//Deadzone for the angle control loop
	double distanceTolerance=5;//Deadzone for the distance control loop
	double constantForce=0.05;
	double rotationAjust;
	double distanceAjust;
	
//*********************************************************************************************************************************************
	//Initilazition function
	public void robotInit(){
		m_OI = new OI();
		m_drivetrain = new Drivetrain();
		m_intake = new Intake();

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
		autoChooser.setDefaultOption("A Trench 12 Point Auto", defaultAutoCommand);
		autoChooser.addOption("A Trench 24 Point", new ATrenchRun24Point());
		autoChooser.addOption("A Middle 12 Point", new AMidRun12Point());
		autoChooser.addOption("A Middle 24 Point", new AMidRun24Point());
		autoChooser.addOption("B Trench 12 Point", new BTrenchRun12Point());
		autoChooser.addOption("B Trench 24 Point", new BTrenchRun24Point());
		autoChooser.addOption("B Middle 12 Point", new BMidRun12Point());
		autoChooser.addOption("B Middle 24 Point", new BMidRun24Point());
		
		SmartDashboard.putData("AutoChooser", autoChooser);
		SmartDashboard.putData(new ZeroGyroCommand());

		//other stuff
		m_frontLeft = new VictorSP(1);
    	m_rearLeft = new VictorSP(2);
    	m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
    	m_frontRight = new VictorSP(3);
    	m_rearRight = new VictorSP(4);
        m_Right = new SpeedControllerGroup(m_frontRight, m_rearRight);
		m_drive = new DifferentialDrive(m_left, m_Right);
			//Points "table" to the NetworkTable database called "chameleon-vision"
		table=NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("PS3EYE");
			//Points to the database value named "yaw" and "pitch"
		targetX=table.getEntry("yaw"); 
		targetY=table.getEntry("pitch");
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
	
		rotationAjust=0;
		distanceAjust=0;
		//This is vision code to line up the robot with vision target
		if(JoyStick.getRawAxis(OI.BUTNUM_RT)>0){
			/* Fetches the rotation and distance values from the vision co processor
				sets the value to 0.0 if the value doesnt exist in the database */
			rotationError=targetX.getDouble(0.0);
			distanceError=targetY.getDouble(0.0); //subtract maximum distance to get distance error
			
			if(rotationError>angleTolerance)
					rotationAjust=KpRot*rotationError+constantForce;
			else
					if(rotationError<angleTolerance)
							rotationAjust=KpRot*rotationError-constantForce;

			if(distanceError>distanceTolerance)
					distanceAjust=KpDist*distanceError+constantForce;
			else
					if(distanceError<distanceTolerance)
							distanceAjust=KpDist*distanceError-constantForce;
			
			m_drive.arcadeDrive(distanceAjust,rotationAjust);
					   
		}else{
			m_drivetrain.drive(JoyStick);
		
		}
				

	}
}

