// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Elevator;

public class AutoShoot extends CommandBase {
  Shooter shooter;
  Elevator elevator;
  Timer timer;
  private boolean finish = false;
  /** Creates a new AutoShoot. */
  public AutoShoot(Shooter s, Elevator e) {
   shooter = s;
   elevator = e;
   addRequirements(shooter);
   timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    while(timer.get() <= 3)
    {
      shooter.shootBall(Constants.SHOOTER_SPEED);
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
}
