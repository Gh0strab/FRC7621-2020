package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class Shooter extends Subsystem {
    CANSparkMax[] shooter_motors = new CANSparkMax[]{
        new CANSparkMax(0, MotorType.kBrushless),
        new CANSparkMax(1, MotorType.kBrushless),
    };
    maxRPM = 5700;
    CANEncoder encoder;
    CANPIDController controller;
    double setpoint= OI.RT.getRawAxis * maxRPM
        
    public Shooter() {
        requires(Robot.m_shooter);
        requires(Robot.m_vision);
        shooter_motors[1].follow(shooter_motors[0], true);
        shooter_motors[0].set(0);
        encoder = shooter_motors[0].getEncoder();
        controller.setFeedbackDevice(encoder);
        stop();
        updateConstants();
    }

    @Override
    public void initDefaultCommand() {
        
    }
    public void set(double setpoint) {
        controller.setReference(setpoint, ControlType.kVelocity);
    }
    public void stop() {
        ontroller.setReference(0, ControlType.kDutyCycle);
    }
    public void updateConstants() {
        controller.setOutputRange(-1, 0);
        controller.setP(constants.shooterP);
        controller.setI(constants.shooterI);
        controller.setD(constants.shooterD);
        controller.setFF(constants.shooterF);
    }
    
}
