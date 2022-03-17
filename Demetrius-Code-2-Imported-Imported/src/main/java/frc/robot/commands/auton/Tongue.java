package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class Tongue extends CommandBase{
    Elevator elevator;
    private boolean finish = false;
    Timer timer;
    /** Creates a new DriveForwardTimed. */
    public Tongue(Elevator e) {
    elevator = e;
    addRequirements(elevator);
    timer = new Timer();
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      timer.reset();
      timer.start();
      while(timer.get() < 0.1)
      {
        elevator.elevatorReverse(-Constants.AUTONOMOUS_SPEED);
      }
      finish = true;
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}
    
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      elevator.stop3();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return finish;
    }    
}
