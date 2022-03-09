// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Shooter extends SubsystemBase {

 TalonFX shooter;
 TalonFX shooter2Fx;
 VictorSPX shooterFeeder; 
 double shooterSpeed = Constants.SHOOTER_SPEED;
 double shooterFeederSpeed = Constants.SHOOTERFEEDER_SPEED;
 public static double speedCheck;

   //Creates a new Shooter. 
  public Shooter() {
    shooter = new TalonFX(Constants.LEFT_SHOOTER);
    shooter2Fx = new TalonFX(Constants.RIGHT_SHOOTER);
    shooterFeeder = new VictorSPX(Constants.SHOOTERFEEDER);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shootBall(double speed)
  {
    speedCheck = speed;
    
    Timer timer = new Timer();
    timer.reset();
    timer.start();
    while(timer.get() <= 0.25) {
      shooter.set(ControlMode.PercentOutput, ((timer.get()*2) * speed)*-1);
      shooter2Fx.set(ControlMode.PercentOutput, ((timer.get()*2) * speed));
      }
      
    shooter.set(ControlMode.PercentOutput, speed*-1);
    shooter2Fx.set(ControlMode.PercentOutput, speed);
    shooterFeeder.set(ControlMode.PercentOutput, speed);
  }
  
   public void slow(){
     Timer timer = new Timer();
     timer.reset();
     timer.start();
     while(timer.get() <= speedCheck){
       shooter.set(ControlMode.PercentOutput, (speedCheck-timer.get())*-1);
       shooter2Fx.set(ControlMode.PercentOutput, (speedCheck-timer.get()));
     }
     
     shooterFeeder.set(ControlMode.PercentOutput, 0);
    }
  
 

  public void stop()
  {
    shooter.set(ControlMode.PercentOutput, 0);
    shooter2Fx.set(ControlMode.PercentOutput, 0);
    shooterFeeder.set(ControlMode.PercentOutput, 0);
    //shooter.stopMotor();
  } 

  public double getSpeed()
  {
    return speedCheck;
  }
  
  public void limeLightShootBall(double speed) 
  {
    shooter.set(ControlMode.PercentOutput, speed * -1);
    shooter2Fx.set(ControlMode.PercentOutput, speed);
    shooterFeeder.set(ControlMode.PercentOutput, 1);
  }
}

