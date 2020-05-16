package frc.robot;

public final class Constants {
    private Constants() {}

    private static Constants instance = new Constants();

    public static Constants getConstants() {
        return instance;
    }
  public static final double shooterP = 0.0011;
  public static final double shooterI = 0;
  public static final double shooterD = 4;
  public static final double shooterF = 0.00017;
  public double debugShooterSet = -5700;


}
