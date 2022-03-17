package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AdjustDistance extends CommandBase {
  double adjustment;
  public AdjustDistance(int i) {
    adjustment = i;
  }

  @Override
  public void initialize() {
    LimeLightShootBall.adjust += adjustment;
    System.out.println("Distance Added/Subtracted" + adjustment);
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
