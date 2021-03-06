// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

public class ReverseEverything extends CommandBase {
  Elevator elevator;
  Shooter shooter;
  Intake intake;
  /** Creates a new ElevatorUp. */
  public ReverseEverything(Elevator e, Shooter s, Intake i) {
    elevator = e;
    shooter = s;
    intake = i;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.reverseShooterFeed();
    elevator.elevatorReverse(Constants.ELEVATOR_SPEED);
    intake.intakeBall(-Constants.INTAKE_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.stop3();
    shooter.stop();
    intake.stop(); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

