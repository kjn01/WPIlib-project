// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;


public class ClimberCommand extends CommandBase {

  private Climber climber;
  private Direction climberState;

  public ClimberCommand(Climber climber, Direction climberState) {
    this.climber = climber;
    this.climberState = climberState;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climber.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    switch (climberState) {
      case UP:
        climber.up();
        break;
      case DOWN:
        climber.down();
        break;
      case STOP:
        climber.stop();
    }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public enum Direction {
    UP,
    DOWN,
    STOP
  }
}