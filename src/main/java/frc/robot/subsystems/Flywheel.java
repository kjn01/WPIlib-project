// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.plaf.TreeUI;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
  /** Creates a new Flywheel. */

  private CANSparkMax flywheelMotor;
  private CANSparkMax flywheelSlave;
  private RelativeEncoder flywheelEncoder;

  private SparkMaxPIDController flywheelController;

  private PIDController controller;
  private final double kFF = 0.0075;
  private final double kP = 0.0002;
  private final double setpoint = 1500;

  public Flywheel() {
      flywheelMotor = new CANSparkMax(9, MotorType.kBrushless);
      flywheelSlave = new CANSparkMax(10, MotorType.kBrushless);
      flywheelSlave.follow(flywheelMotor, true);

      flywheelController = flywheelMotor.getPIDController();
      flywheelController.setP(kP);
      flywheelController.setFF(kFF);
      
      flywheelEncoder = flywheelMotor.getEncoder();
      controller = new PIDController(kP, 0.0, 0.0);

  }
public void forward() {
  flywheelController.setReference(-setpoint, ControlType.kVelocity);
  
}

  // public void forward() {
  //   flywheelMotor.setVoltage(controller.calculate(flywheelEncoder.getVelocity(), setpoint) + kFF);
  // }

  public void stop() {
    flywheelMotor.setVoltage(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
