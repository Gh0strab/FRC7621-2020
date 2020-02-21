package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Aim;
import frc.robot.commands.StopAim;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision extends Subsystem {
    public static Drivetrain m_drivetrain;

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
    public Vision() {
        
    }

    @Override
    public void initDefaultCommand() {
        m_drivetrain = new Drivetrain();
        table=NetworkTableInstance.getDefault().getTable("chameleon-vision").getSubTable("PS3EYE");
		targetX=table.getEntry("yaw"); 
        targetY=table.getEntry("pitch");
        
        }
        public void Visionturnon(){
            rotationAjust=0;
            distanceAjust=0;
                /* Fetches the rotation and distance values from the vision co processor
                    sets the value to 0.0 if the value doesnt exist in the database */
                rotationError=targetX.getDouble(0.0);
                distanceError=targetY.getDouble(0.0)-8; //subtract maximum distance to get distance error
                
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
                
                m_drivetrain.arcadeDrive(distanceAjust,rotationAjust);
        }
        public void Visionturnoff(){

    }
}

