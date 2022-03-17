// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;

public class RotateShooter extends SubsystemBase {
  TalonSRX rotateShooter;
  Faults faults = new Faults();
  /** Creates a new RotateShooter. */
  public RotateShooter() {
    rotateShooter = new TalonSRX(Constants.ROTATER);
    rotateShooter.setSensorPhase(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void rotateShooterHead(double speed)
  {
    rotateShooter.getFaults(faults);
    System.out.println("Sensor Pos: " + rotateShooter.getSelectedSensorPosition());
    if(rotateShooter.getSelectedSensorPosition() > 110000 && speed > 0)
    {
      stop();
    }
    else if(rotateShooter.getSelectedSensorPosition() < -110000 && speed < 0)
    {
      stop();
    }
    else
    {
      rotateShooter.set(ControlMode.PercentOutput, speed);
    }
  }

  public void stop()
  {
    rotateShooter.set(ControlMode.PercentOutput, 0);
  }
}
