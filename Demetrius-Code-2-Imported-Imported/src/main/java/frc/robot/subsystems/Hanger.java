package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Hanger extends SubsystemBase {
    VictorSPX pinPull;
    VictorSPX climber;
    Timer timer;

    /** Creates a new Intake. */
    public Hanger() {
      climber = new VictorSPX(Constants.CLIMBER);
      pinPull = new VictorSPX(Constants.PIN_PULL);
    }

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

    public void climb()
    {
      climber.set(ControlMode.PercentOutput, -0.5);
    }
  
    public void pinPull()
    {
      pinPull.set(ControlMode.PercentOutput, 1.0);
    }
  
  
    public void stop()
    {
      pinPull.set(ControlMode.PercentOutput, 0);
      climber.set(ControlMode.PercentOutput, 0);
    }
}
