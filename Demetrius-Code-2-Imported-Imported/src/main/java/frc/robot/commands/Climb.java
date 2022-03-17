package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hanger;

public class Climb extends CommandBase{
  Hanger hanger;
  int direction;
  /** Creates a new ElevatorUp. */
  public Climb(Hanger h) {
    hanger = h;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hanger.climb();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hanger.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
