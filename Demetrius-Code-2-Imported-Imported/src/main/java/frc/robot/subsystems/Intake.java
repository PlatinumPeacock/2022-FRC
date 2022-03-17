// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Intake extends SubsystemBase {
  VictorSPX intake;

  /** Creates a new Intake. */
  public Intake() {
    intake = new VictorSPX(Constants.INTAKE);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intakeBall(double speed)
  {
    intake.set(ControlMode.PercentOutput, speed * -1);
  }


  public void stop()
  {
    intake.set(ControlMode.PercentOutput, 0);
  }
}
