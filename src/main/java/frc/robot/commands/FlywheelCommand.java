// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Flywheel;

public class FlywheelCommand extends CommandBase {
  /** Creates a new Flywheel. */

  private Flywheel flywheel;
  private FlywheelState flywheelState;

  public FlywheelCommand(Flywheel flywheel, FlywheelState flywheelState) {
    this.flywheel = flywheel;
    this.flywheelState = flywheelState;
    addRequirements(flywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    flywheel.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (flywheelState) {
      case RUN:
        flywheel.forward();
        break;
      case STOP:
        flywheel.stop();
        break;
      default:
        flywheel.stop();
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    flywheel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public enum FlywheelState {
    RUN,
    STOP
  }
}
