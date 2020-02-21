package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Climber extends Subsystem {
    VictorSPX m_climbermotor;
    VictorSPX m_winchmotor_right;
    VictorSPX m_winchmotor_left;
    //GroupMotorControllers m_winchmotors;

    public Climber() {
        m_climbermotor = new VictorSPX(7);
    	m_winchmotor_right = new VictorSPX(8);
    	m_winchmotor_left = new VictorSPX(9);
    	//m_winchmotors = new GroupMotorControllers(m_winchmotor_left, m_winchmotor_right);
    }

    @Override
    public void initDefaultCommand() {
        //Set the default command for the subsystem here
    }

    public void ClimberExtend() {
        SetExtensionMotorOutput(1);
    }
    public void ClimberRetract() {
        SetExtensionMotorOutput(-1);

    }
    public void ClimberStop() {
        SetExtensionMotorOutput(0);

    }
    void SetExtensionMotorOutput(double value) {
        m_climbermotor.set(ControlMode.PercentOutput, value);
    }
    public void WinchRetract() {
        SetWinchMotorOutput(1);
    }
    public void WinchExtend() {
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

