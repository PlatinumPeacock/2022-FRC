// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Elevator extends SubsystemBase {
  VictorSPX elevator;
  VictorSPX elevator2;
  VictorSPX feeder;
  /** Creates a new Elevator. */
  public Elevator() {
    elevator = new VictorSPX(Constants.HORI_ELEVATOR);
    elevator2 = new VictorSPX(Constants.VERT_ELEVATOR);
    feeder = new VictorSPX(Constants.SHOOTERFEEDER);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void elevatorUp(double speed)
  {
    elevator2.set(ControlMode.PercentOutput, speed * -1);
  }

  public void elevatorHorizontal(double speed)
  {
    elevator.set(ControlMode.PercentOutput, speed);
  }

  public void elevatorBoth(double speed)
  {
    elevator.set(ControlMode.PercentOutput, speed * -1);
    elevator2.set(ControlMode.PercentOutput, speed);
  }

  public void elevatorReverse(double speed)
  {
    elevator.set(ControlMode.PercentOutput, speed * -1);
    elevator2.set(ControlMode.PercentOutput, speed);
    feeder.set(ControlMode.PercentOutput, speed * -1);

  }

  public void stop()
  {
    elevator.set(ControlMode.PercentOutput, 0);
  }

  public void stop2()
  {
    elevator2.set(ControlMode.PercentOutput, 0);
  }

  public void stop3()
  {
    elevator.set(ControlMode.PercentOutput, 0);
    elevator2.set(ControlMode.PercentOutput, 0);
  }
}
