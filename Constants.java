package main.java.frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
public final class Constants {
    public static final class DriveConstants {
      public static final int kLeftMotor1Port = 0;
      public static final int kLeftMotor2Port = 1;
      public static final int kRightMotor1Port = 2;
      public static final int kRightMotor2Port = 3;
  
      public static final int[] kLeftEncoderPorts = new int[]{0, 1};
      public static final int[] kRightEncoderPorts = new int[]{2, 3};
      public static final boolean kLeftEncoderReversed = false;
      public static final boolean kRightEncoderReversed = true;
  
      public static final double kTrackwidthMeters = 0.69;
      public static final DifferentialDriveKinematics kDriveKinematics =
          new DifferentialDriveKinematics(kTrackwidthMeters);
  
      public static final int kEncoderCPR = 1024;
      public static final double kWheelDiameterMeters = 0.15;
      public static final double kEncoderDistancePerPulse =
          
          (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;
  
      public static final boolean kGyroReversed = false;
  
      public static final double ksVolts = 0.22;//tune these
      public static final double kvVoltSecondsPerMeter = 1.98;
      public static final double kaVoltSecondsSquaredPerMeter = 0.2;
  
      
      public static final double kPDriveVel = 10;
    }
  
  
    public static final class AutoConstants {
      public static final double kMaxSpeedMetersPerSecond = 3;
      public static final double kMaxAccelerationMetersPerSecondSquared = 3;
  
      // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
      public static final double kRamseteB = 2;
      public static final double kRamseteZeta = 0.7;
    }
  }