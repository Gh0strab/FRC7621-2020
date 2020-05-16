package frc.robot.subsystems;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends Subsystem {

    NetworkTable table;
	NetworkTableEntry targetX;
	NetworkTableEntry targetY;

	double rotationError;
    double distanceError;

	double KpRot=-0.045;//adjust kp values to make aim less/ more responsive. this will fix your jittering problem
	double KpDist=-0.07;
	double angleTolerance=5;//Deadzone for the angle control loop
	double distanceTolerance=-7.2;//Deadzone for the distance control loop
	double constantForce=0.05;
	double rotationAjust;
    double distanceAjust;
    
    public Vision(){
        
    }

    @Override
    public void initDefaultCommand(){
        //commented out sections are from chameleon vision, delete after testing with limelight

        //table=NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("PS3EYE");
        table=NetworkTableInstance.getDefault().getTable("limelight");

        }
        public void Aim(){

            //targetX=table.getEntry("targetYaw"); 
            //targetY=table.getEntry("targetPitch");
            targetX=table.getEntry("tx");
            targetY=table.getEntry("ty");

            rotationAjust=0;
            distanceAjust=0;

            double CameraHeight = 2;
            double TargetHeight = 6.925; 
            double CameraAngle = 45;

            rotationError=targetX.getDouble(0.0);
            distanceError=(TargetHeight - CameraHeight) / Math.tan(CameraAngle + targetY.getDouble(0.0));
            //distanceError=targetY.getDouble(0.0); 
       
                if(rotationError>angleTolerance)
                        rotationAjust=(KpRot*rotationError+constantForce)*-1;
                else
                        if(rotationError<angleTolerance)
                                rotationAjust= (KpRot*rotationError-constantForce)*-1;
                
                if(distanceError>distanceTolerance)
                        distanceAjust=KpDist*distanceError+constantForce;
                else
                        if(distanceError<distanceTolerance)
                                distanceAjust=KpDist*distanceError-constantForce;
               
                                Robot.m_drivetrain.visionDrive(); 
        }
        public void StopAim(){

    }
}

