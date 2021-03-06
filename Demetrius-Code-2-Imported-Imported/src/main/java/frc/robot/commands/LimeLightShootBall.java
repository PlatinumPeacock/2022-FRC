// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class LimeLightShootBall extends CommandBase {
  Shooter shooter;
  double shooterSpeed;
  public static double adjust = 0;

  /** Creates a new LimeLightShootBall. */
  public LimeLightShootBall(Shooter s, double d) {
    shooter = s;
    addRequirements(shooter);
    //shooterSpeed = (0.018 * (d)) + .25;
    shooterSpeed = (0.32 + 0.00627 * (d + adjust) + 0.000312 * Math.pow((d + adjust), 2));
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.limeLightShootBall(shooterSpeed);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}