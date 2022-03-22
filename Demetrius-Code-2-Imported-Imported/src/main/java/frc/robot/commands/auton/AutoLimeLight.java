// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Elevator;
import frc.robot.Constants;

public class AutoLimeLight extends CommandBase {
  Shooter shooter;
  LimeLight limeLight;
  Elevator elevator;
  Timer timer;
  double shooterSpeed;
  boolean finish = false;
  
  //limelight variables
  public static double tx;
  public static double ty;
  public static double ta;
  public static double tv;
  public static double distance;

  /** Creates a new LimeLightShootBall. */
  public AutoLimeLight(Shooter s, LimeLight l, Elevator e) {
    shooter = s;
    limeLight = l;
    elevator = e;
    addRequirements(limeLight);
    addRequirements(shooter);
    addRequirements(elevator);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    updateLimeLightTracking();
    shooterSpeed = (0.32 + 0.00627 * (distance) + 0.000312 * Math.pow((distance), 2));
    timer.reset();
    timer.start();
    while(timer.get() <= 3)
    {
      shooter.shootBall(shooterSpeed);
      while(timer.get() <= 3 && timer.get() >= 1)
      {
        elevator.elevatorBoth(-Constants.ELEVATOR_SPEED);
      }
    }
    finish = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stop();
    elevator.stop3();
    }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }

  public void updateLimeLightTracking(){
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    distance = ((26/3)-(11/3)) / Math.tan(Math.toRadians(20 + ty));
  }
}