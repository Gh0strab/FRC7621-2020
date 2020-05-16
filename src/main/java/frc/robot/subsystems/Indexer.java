package frc.robot.subsystems;

import frc.robot.Portmap;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class Indexer extends Subsystem {
    WPI_VictorSPX UpperIndex = new WPI_VictorSPX(Portmap.INDEX_UPPER_MOTOR);
    WPI_VictorSPX LowerIndex = new WPI_VictorSPX(Portmap.INDEX_LOWER_MOTOR);

    

    public Indexer() {
        
    }
    public void IndexFeed(){
        UpperIndex.set(.45);
        LowerIndex.set(-.45);
    }
    public void Index(){
        UpperIndex.set(.15);
        LowerIndex.set(-.15);

    }
    public void IndexStop(){
        UpperIndex.set(0);
        LowerIndex.set(0);
    }
    public void OutTake(){
        //UpperIndex.set(-.35);
        //LowerIndex.set(.35);
    }

@Override
public void initDefaultCommand() {

}

}