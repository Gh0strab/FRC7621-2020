package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.OI;


public class Intake extends Subsystem {
    
	DoubleSolenoid IntakeCylinder = new DoubleSolenoid(0, 1);
	VictorSPX IntakeMotor = new VictorSPX(5);

    public Intake() {

    }

    @Override
    public void initDefaultCommand() {
        //Set the default command for the subsystem here
       
    }

    public void turnon(){
        IntakeCylinder.set(DoubleSolenoid.Value.kForward);
        IntakeMotor.set(ControlMode.PercentOutput, 1);
    }
    public void turnoff(){
        IntakeCylinder.set(DoubleSolenoid.Value.kReverse);
        IntakeMotor.set(ControlMode.PercentOutput, 0);	
    }
}