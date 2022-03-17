// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import edu.wpi.first.cscore.UsbCamera;
//import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveWithJoysticksTrial;
import frc.robot.commands.ElevatorBoth;
import frc.robot.commands.ElevatorHorizontal;
import frc.robot.commands.IntakeBall;
import frc.robot.commands.RotateShooterHead;
import frc.robot.commands.ShootBall;
import frc.robot.commands.auton.AutonomousOne;
import frc.robot.commands.auton.DriveForwardTimed;
import frc.robot.commands.LimeLightRun;
import frc.robot.commands.PinPull;
import frc.robot.commands.Climb;
import frc.robot.commands.ReverseEverything;
import frc.robot.commands.AdjustDistance;
import frc.robot.subsystems.DriveTrainTrial;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.RotateShooter;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hanger;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 * 
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  private final DriveTrainTrial driveTrain;
  private final DriveWithJoysticksTrial driveWithJoystick;
  private final DriveForwardTimed driveForwardTimed;
  public static Joystick driverJoystick1;
  public static Joystick driverJoystick2;
  public static XboxController driverJoystick3;

  private final Shooter shooter;
  private final ShootBall shootBall;

  private final RotateShooter rotateShooter;
  private RotateShooterHead rotateHeadRight;
  private RotateShooterHead rotateHeadLeft;

  private final Intake intake;
  private final IntakeBall intakeBall;

  private final Elevator elevator;
  private final ReverseEverything reverseEverything;

  private final LimeLight limeLight;

  private final Hanger hanger;

  //private final AutonomousOne autonomousOne;
  //private final AidensAuton aidensAuton;
  //private final AutonomousTwo autonomousTwo;

 SendableChooser<Command> chooser = new SendableChooser<>();


  /** All Xbox controller inputs defined below */
  /*final JoystickButton leftBumper = new JoystickButton(driverJoystick3, 5);
  final JoystickButton rightBumper = new JoystickButton(driverJoystick3, 6);
  final JoystickButton aButton = new JoystickButton(driverJoystick3, 1);
  final JoystickButton bButton = new JoystickButton(driverJoystick3, 2);
  final JoystickButton xButton = new JoystickButton(driverJoystick3, 3);
  final JoystickButton yButton = new JoystickButton(driverJoystick3, 4);
  final JoystickButton rightStick = new JoystickButton(driverJoystick3, 10);
  final JoystickButton backButton = new JoystickButton(driverJoystick3, 7);
  final JoystickButton startButton = new JoystickButton(driverJoystick3, 8);
  */

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
  driveTrain = new DriveTrainTrial();
  driveWithJoystick = new DriveWithJoysticksTrial(driveTrain);
  driveTrain.setDefaultCommand(driveWithJoystick);

  driveForwardTimed = new DriveForwardTimed(driveTrain);
  driveForwardTimed.addRequirements(driveTrain);

  driverJoystick1 = new Joystick(Constants.JOYSTICK11);
  driverJoystick2 = new Joystick(Constants.JOYSTICK22);
  driverJoystick3 = new XboxController(Constants.JOYSTICK3);

  shooter = new Shooter();
  shootBall = new ShootBall(shooter);
  shootBall.addRequirements(shooter);

  rotateShooter = new RotateShooter();
  rotateHeadRight = new RotateShooterHead(rotateShooter, -1);
  rotateHeadRight.addRequirements(rotateShooter);

  rotateHeadLeft = new RotateShooterHead(rotateShooter, 1);
  rotateHeadLeft.addRequirements(rotateShooter);

  intake = new Intake();
  intakeBall = new IntakeBall(intake, 1);
  intakeBall.addRequirements(intake);
    
  elevator = new Elevator();
  reverseEverything = new ReverseEverything(elevator, shooter, intake);
  
  limeLight = new LimeLight();

  hanger = new Hanger();

  
  AutonomousOne autonOne = new AutonomousOne(driveTrain, shooter, limeLight,rotateShooter, elevator);
  //autonomousOne = new AutonomousOne(driveTrain, shooter, limeLight, rotateShooter, elevator);
  //autonomousTwo = new AutonomousTwo(driveTrain, shooter);

  //Add choices as options here
 //chooser.addOption("Autonomous Two", autonomousTwo);
  //Default option
  chooser.setDefaultOption("Autonomous One", autonOne);
  //chooser.setDefaultOption("Aiden's Auton", AidensAuton);
 
  //chooser.setDefaultOption("Autonomous One", autonomousOne);
  //Add choice so smart dashboard
  SmartDashboard.putData("Autonomous", chooser);

  //Initialize camera
  //UsbCamera camera = CameraServer.startAutomaticCapture();
  //camera.setResolution(Constants.CAMERA_RES_X, Constants.CAMERA_RES_Y);

    // Configure the button bindings
    configureButtonBindings();
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link Joystick}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //add other buttons
      JoystickButton  elevatorReverse = new JoystickButton(driverJoystick3, XboxController.Button.kRightStick.value);
      elevatorReverse.whileHeld(shootBall);

      JoystickButton cameraButton = new JoystickButton(driverJoystick3, XboxController.Button.kB.value);
      cameraButton.whileHeld(new LimeLightRun(limeLight, rotateShooter, shooter, driveTrain, elevator));

      JoystickButton intakeButton = new JoystickButton(driverJoystick3, XboxController.Button.kA.value);
      intakeButton.whileHeld(new IntakeBall(intake, 1));

      JoystickButton elevatorHorizontalButton = new JoystickButton(driverJoystick3, XboxController.Button.kA.value);
      elevatorHorizontalButton.whileHeld(new ElevatorHorizontal(elevator));

      JoystickButton elevatorBothButton = new JoystickButton(driverJoystick3, XboxController.Button.kY.value);
      elevatorBothButton.whileHeld(new ElevatorBoth(elevator, 1));

      JoystickButton rotaterButtonRight = new JoystickButton(driverJoystick3, XboxController.Button.kRightBumper.value);
      rotaterButtonRight.whileHeld(rotateHeadRight);

      JoystickButton rotaterButtonLeft = new JoystickButton(driverJoystick3, XboxController.Button.kLeftBumper.value);
      rotaterButtonLeft.whileHeld(rotateHeadLeft); //The left bumper will rotate the rotater in the opposite direction, this hasn't been made yet.

      JoystickButton intakeReverseButton = new JoystickButton(driverJoystick3, XboxController.Button.kX.value);
      intakeReverseButton.whileHeld(reverseEverything);

      JoystickButton shooterButton = new JoystickButton(driverJoystick3, XboxController.Button.kX.value);
      shooterButton.whileHeld(new ElevatorBoth(elevator, -1));
      
      JoystickButton pinPullButton = new JoystickButton(driverJoystick3, XboxController.Button.kBack.value);
      pinPullButton.whileHeld(new PinPull(hanger));

      JoystickButton climbButton = new JoystickButton(driverJoystick3, XboxController.Button.kStart.value);
      climbButton.whileHeld(new Climb(hanger));

      //JoystickButton centerTurret = new JoystickButton(driverJoystick3, XboxController.Button.kLeftStick);
      
      
  }
  public static enum HAND {
    LEFT, RIGHT
}
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
     
    //An ExampleCommand will run in autonomous
  return chooser.getSelected();
  }
}
