package frc.robot.subsystems;

import frc.robot.Portmap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Intake extends Subsystem {
    
    DoubleSolenoid IntakeCylinder = new DoubleSolenoid(Portmap.INTAKE_DEPLOY, Portmap.INTAKE_RETRACT);  
	VictorSPX IntakeMotor = new VictorSPX(Portmap.INTAKE_MOTOR);
   

    public Intake() {

    }

    @Override
    public void initDefaultCommand() {
        
    }

    public void TurnOn(){
        IntakeCylinder.set(DoubleSolenoid.Value.kForward);
        IntakeMotor.set(ControlMode.PercentOutput, -.75);

    }
    public void TurnOff(){
        IntakeCylinder.set(DoubleSolenoid.Value.kReverse);
        IntakeMotor.set(ControlMode.PercentOutput, 0);	
    }
    public void OutTake(){
        IntakeMotor.set(ControlMode.PercentOutput, .45);
    }
    
    
}