
    package frc.robot;

    import edu.wpi.first.wpilibj.Joystick;
    import edu.wpi.first.wpilibj.buttons.Button;
    import edu.wpi.first.wpilibj.buttons.JoystickButton;
    
    import frc.robot.commands.*;

    public class OI {
        public static final int BUTNUM_A = 1;
        public static final int BUTNUM_B = 2;
        public static final int BUTNUM_X = 3;
        public static final int BUTNUM_Y = 4;
        
        public static final int BUTNUM_LB = 5;
        public static final int BUTNUM_RB = 6;
        
        public static final int BUTNUM_BACK = 7;
        public static final int BUTNUM_START = 8;
        
        public static final int BUTNUM_LT = 2;
        public static final int BUTNUM_RT = 3;
        
        public static final int BUTNUM_LS = 9;
        public static final int BUTNUM_RS = 10;
    
        public static final int AXIS_LY = 1;
        public static final int AXIS_RX = 4;
        
        public static final int DIR_RIGHT = 90;
        public static final int DIR_UP = 0;
        public static final int DIR_LEFT = 270;
        public static final int DIR_DOWN = 180;

        //SWAP OUT "PORT" VARIABLE BELOW
        public Joystick driverController = new Joystick(0);

        Button BTN_A = new JoystickButton(driverController, BUTNUM_A);
        Button BTN_B = new JoystickButton(driverController, BUTNUM_B);
        Button BTN_X = new JoystickButton(driverController, BUTNUM_X);
        Button BTN_Y = new JoystickButton(driverController, BUTNUM_Y);

        Button BTN_LT = new JoystickButton(driverController, BUTNUM_LT);
        Button BTN_RT = new JoystickButton(driverController, BUTNUM_RT);
        Button BTN_LB = new JoystickButton(driverController, BUTNUM_LB);
        Button BTN_RB = new JoystickButton(driverController, BUTNUM_RB);

        Button BTN_LS = new JoystickButton(driverController, BUTNUM_LS);
        Button BTN_RS = new JoystickButton(driverController, BUTNUM_RS);

        Button BTN_START = new JoystickButton(driverController, BUTNUM_START);
        Button BTN_BACK = new JoystickButton(driverController, BUTNUM_BACK);

        //CONSTRUCTOR
        public OI() {
            BTN_LT.whenPressed(new IntakeOn());
            BTN_LT.whenReleased(new IntakeOff());
            BTN_RT.whenPressed(new Aim());
            BTN_RT.whenReleased(new StopAim());
        }
    }
