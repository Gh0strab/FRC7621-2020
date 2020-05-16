package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.Constants;
import frc.robot.Portmap;
import frc.robot.Robot;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Shooter extends Subsystem {
    /*
    CANSparkMax[] shooter_motors = new CANSparkMax[]{
        new CANSparkMax(Portmap.LEFT_SHOOTER_MOTOR, MotorType.kBrushless),
        new CANSparkMax(Portmap.RIGHT_SHOOTER_MOTOR, MotorType.kBrushless),
    };
    */
    CANSparkMax rightneo = new CANSparkMax(Portmap.RIGHT_SHOOTER_MOTOR, MotorType.kBrushless);
    CANSparkMax leftneo = new CANSparkMax(Portmap.LEFT_SHOOTER_MOTOR, MotorType.kBrushless);

    
    CANEncoder encoder;
    CANPIDController controller;
    Constants constants = Constants.getConstants();

    double setpoint=(5000);
    
    public Shooter(){
        
    }
    
    @Override
    public void initDefaultCommand() {
        
    }
    public void Shoot(){
        rightneo.set(-1);
        leftneo.set(1);
        encoder = rightneo.getEncoder();
        if(Robot.m_vision.rotationError<Robot.m_vision.angleTolerance){
            if(encoder.getVelocity()>setpoint){
                Robot.m_indexer.IndexFeed();
            }else{   
                Robot.m_indexer.IndexStop();     
                    }
            }else{
    
            }

    }
    /*
    public void Shoot(){
       
            shooter_motors[1].follow(shooter_motors[0], true);
            shooter_motors[0].set(0);
            for ( CANSparkMax motor : shooter_motors ) {
                motor.setIdleMode(IdleMode.kCoast);
            }
            encoder = shooter_motors[0].getEncoder();
            controller.setFeedbackDevice(encoder);
            stop();
            updateConstants();

    if(Robot.m_vision.rotationError>Robot.m_vision.angleTolerance){
        if(encoder.getVelocity()>setpoint){
            Robot.m_indexer.IndexFeed();
        }else{               
                }
        }else{

        }
    }
     */
    public void StopShoot(){
        rightneo.set(0);
        leftneo.set(0);
    }
    
    public void set(double setpoint) {
        controller.setReference(setpoint, ControlType.kVelocity);
    }
    public void stop() {
        controller.setReference(0, ControlType.kDutyCycle);
    }
    public void updateConstants() {
        controller.setOutputRange(-1, 0);
        controller.setP(Constants.shooterP);
        controller.setI(Constants.shooterI);
        controller.setD(Constants.shooterD);
        controller.setFF(Constants.shooterF);
    }
    
}
