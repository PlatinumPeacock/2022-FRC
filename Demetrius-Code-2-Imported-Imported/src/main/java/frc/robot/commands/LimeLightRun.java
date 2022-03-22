// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimeLight;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.DriveTrainTrial;
import frc.robot.subsystems.RotateShooter;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Elevator;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class LimeLightRun extends CommandBase {
  LimeLight limeLight;
  RotateShooter rotateShooter;
  Shooter shooter;
  DriveTrainTrial driveTrain;
  Timer timer;
  Elevator elevator;
  JoystickButton elevatorBothButton = new JoystickButton(RobotContainer.driverJoystick3, XboxController.Button.kY.value);
  JoystickButton shootButton = new JoystickButton(RobotContainer.driverJoystick3, XboxController.Button.kX.value);

  //limelight variables
  public static double tx;
  public static double ty;
  public static double ta;
  public static double tv;
  public static double distance;


  /** Creates a new limeLightRun. */
  public LimeLightRun(LimeLight l, RotateShooter rs, Shooter s, DriveTrainTrial d, Elevator e ) {
    limeLight = l;
    rotateShooter = rs;
    addRequirements(rotateShooter);
    shooter = s;
    addRequirements(shooter);
    elevator = e;
    addRequirements(elevator);
    driveTrain = d;
    addRequirements(driveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    //timer.reset();
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute(){
  NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  
  updateLimeLightTracking();
  driveTrain.driveWithJoysticks();
  while(tx < 0)
  {
    driveTrain.driveWithJoysticks();
    rotateShooter.rotateShooterHead(0.15);
    if(tx > -5)
    {
      rotateShooter.stop();
      break;
    }
    updateLimeLightTracking();
  }

  while(tx > 0)
  {
    driveTrain.driveWithJoysticks();
    rotateShooter.rotateShooterHead(-0.15);
    if(tx < 5)
    {
      rotateShooter.stop();
      break;
    }
    updateLimeLightTracking();
  }

  driveTrain.driveWithJoysticks();

  if(tx > -3 && tx < 3)
  {
    limeLightShooterSpeed();
  }

  if(tv == 0)
  {
    driveTrain.driveWithJoysticks();
    rotateShooter.stop();
    shooter.shootBall(Constants.SHOOTER_SPEED);
  }

}
  
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    //shooter.shootBall(0);
    driveTrain.driveWithJoysticks();
    shooter.slow();
    driveTrain.driveWithJoysticks();
    shooter.stop();
    driveTrain.driveWithJoysticks();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  //updates limelight info
  public void updateLimeLightTracking(){
    driveTrain.driveWithJoysticks();
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    distance = ((26/3)-(11/3)) / Math.tan(Math.toRadians(20 + ty));
  }

  public void limeLightShooterSpeed()
  {
    driveTrain.driveWithJoysticks();
    if(tv==1)
    {
      driveTrain.driveWithJoysticks();
      LimeLightShootBall shootBall = new LimeLightShootBall(shooter, distance);
      shootBall.initialize();
      elevatorBothButton.whileHeld(new ElevatorBoth(elevator, 1));
    }
    else
    {
      driveTrain.driveWithJoysticks();
      shooter.shootBall(0.40);
      elevatorBothButton.whileHeld(new ElevatorBoth(elevator, 1));
    }
  }

  public double getDistance()
  {
    updateLimeLightTracking();
    return distance;
  }
}