package frc.robot.subsystems;

import frc.robot.Portmap;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;


public class Climber extends Subsystem {
    WPI_VictorSPX m_climbermotor;
    WPI_VictorSPX m_winchmotor_right;
    WPI_VictorSPX m_winchmotor_left;

    public Climber() {
        m_climbermotor = new WPI_VictorSPX(Portmap.CLIMBER_EXTENSION_MOTOR);
    	m_winchmotor_right = new WPI_VictorSPX(Portmap.CLIMBER_WINCH_MOTORRIGHT);
    	m_winchmotor_left = new WPI_VictorSPX(Portmap.CLIMBER_WINCH_MOTORLEFT);
    	
    }

    @Override
    public void initDefaultCommand() {
        //Set the default command for the subsystem here
    }

    public void ClimberExtend() {
        SetExtensionMotorOutput(.5);
    }
    public void ClimberRetract() {
        SetExtensionMotorOutput(-.5);

    }
    public void ClimberStop() {
        SetExtensionMotorOutput(0);

    }
    void SetExtensionMotorOutput(double value) {
        m_climbermotor.set(ControlMode.PercentOutput, value);
    }
    public void WinchReset(){
        SetWinchMotorOutput(1);
    }
    public void WinchRetract() {
        SetWinchMotorOutput(-1);
    }
    public void WinchStop() {
        SetWinchMotorOutput(0);
    }
    void SetWinchMotorOutput(double value) {
        m_winchmotor_right.set(ControlMode.PercentOutput, value);
        m_winchmotor_left.set(ControlMode.PercentOutput, value);
    }


}

