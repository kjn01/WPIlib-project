// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase {
  /** Creates a new IntakeCommand. */
  private Intake intake;
  private IntakeState intakeState;

  public IntakeCommand(Intake intake, IntakeState intakeState) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.intakeState = intakeState;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.stopMotor();
    intake.stopPivot();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (intakeState) {
      case MOTOR_IN:
        intake.motorForward();
      case MOTOR_OUT:
        intake.motorBackward();
      case MOTOR_STOP:
        intake.stopMotor();
        intake.stopPivot();
      case PIVOT_IN:
        intake.pivotForward();
      case PIVOT_OUT:
        intake.pivotBackward();
      case PIVOT_STOP:
        intake.stopPivot();
      default:
        intake.stopMotor();
        intake.stopPivot();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stopMotor();
    intake.stopPivot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public enum IntakeState {
    MOTOR_IN,
    MOTOR_OUT,
    MOTOR_STOP,
    PIVOT_IN,
    PIVOT_OUT,
    PIVOT_STOP
  }
}
