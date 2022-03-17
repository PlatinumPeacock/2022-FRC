// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrainTrial extends SubsystemBase {

    public static VictorSPX leftFront;
    public static VictorSPX rightFront;
    public static VictorSPX leftRear;
    public static VictorSPX rightRear;
  
  private final AnalogInput rangeFinder;

  /** Creates a new DriveTrainTrial. */
  public DriveTrainTrial() {
    leftFront = new VictorSPX(Constants.LEFT_FRONT); // OG
    rightFront = new VictorSPX(Constants.RIGHT_FRONT);
    leftRear = new VictorSPX(Constants.LEFT_REAR);
    rightRear = new VictorSPX(Constants.RIGHT_REAR);

    rangeFinder = new AnalogInput(Constants.RANGE_FINDER);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveWithJoysticks() {
    leftFront.set(ControlMode.PercentOutput, Constants.JOYSTICK1.getY() * -0.9);
    leftRear.set(ControlMode.PercentOutput, Constants.JOYSTICK1.getY() * -0.9);
    rightFront.set(ControlMode.PercentOutput, Constants.JOYSTICK2.getY() * 0.9);
    rightRear.set(ControlMode.PercentOutput, Constants.JOYSTICK2.getY() * 0.9);
  }


  public void driveForward(double speed)
  {
    leftFront.set(ControlMode.PercentOutput, -speed);
    leftRear.set(ControlMode.PercentOutput, -speed);
    rightFront.set(ControlMode.PercentOutput, speed);
    rightRear.set(ControlMode.PercentOutput, speed);
  }

  public boolean driveToDistance(double setPointDistance, double speed)
  {
   while(rangeFinder.getAverageVoltage() > setPointDistance)
   {
    driveForward(speed);
   }
   return true;
  }

  public void stop()
  {
    driveForward(0);
  }
}
