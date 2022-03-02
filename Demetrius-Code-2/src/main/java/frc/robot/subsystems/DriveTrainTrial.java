// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.GenericHID;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class DriveTrainTrial extends SubsystemBase {
   //public static SpeedController leftFront; //OG
   //public static SpeedController rightFront; //OG
   //public static SpeedController leftBack; //OG
   //public static SpeedController rightBack; //OG

    public static VictorSPX leftFront;
    public static VictorSPX rightFront;
    public static VictorSPX leftRear;
    public static VictorSPX rightRear;
  
  MotorControllerGroup leftMotors;
  MotorControllerGroup rightMotors;
  DifferentialDrive drive;
  private final AnalogInput rangeFinder;

  /** Creates a new DriveTrainTrial. */
  public DriveTrainTrial() {
    leftFront = new VictorSPX(Constants.LEFT_FRONT); // OG
    rightFront = new VictorSPX(Constants.RIGHT_FRONT);
    leftRear = new VictorSPX(Constants.LEFT_REAR);
    rightRear = new VictorSPX(Constants.RIGHT_REAR);

    

    //leftFront.set(ControlMode.PercentOutput, 0.01);
    //rightFront.set(ControlMode.PercentOutput, 0.01);

    rangeFinder = new AnalogInput(Constants.RANGE_FINDER);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveWithJoysticks(Joystick controller, double speed) {
    leftFront.set(ControlMode.PercentOutput, Constants.JOYSTICK1.getY() * -1);
    leftRear.set(ControlMode.PercentOutput, Constants.JOYSTICK1.getY() * -1);
    rightFront.set(ControlMode.PercentOutput, Constants.JOYSTICK2.getY() * -1);
    rightRear.set(ControlMode.PercentOutput, Constants.JOYSTICK2.getY() * -1);
  }


  public void driveForward(double speed)
  {
    drive.tankDrive(speed, speed);
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
    drive.stopMotor();
  }
}
