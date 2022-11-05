// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Climber;
import frc.robot.commands.ClimberCommand;
import frc.robot.commands.ClimberCommand.ClimberState;
import frc.robot.commands.IntakeCommand.IntakeState;
import frc.robot.subsystems.Intake;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final Climber climber = new Climber();
  private final Intake intake = new Intake();

  private final Joystick joystick = new Joystick(1);

  private final JoystickButton xButton = new JoystickButton(joystick, 1);
  private final JoystickButton yButton = new JoystickButton(joystick, 2);
  private final JoystickButton aButton = new JoystickButton(joystick, 3);
  private final JoystickButton bButton = new JoystickButton(joystick, 4);
  private final JoystickButton lBumper = new JoystickButton(joystick, 5);
  private final JoystickButton rBumper = new JoystickButton(joystick, 6);

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    lBumper.whileHeld(new IntakeCommand(intake, IntakeState.MOTOR_IN));
    lBumper.whenReleased(new IntakeCommand(intake, IntakeState.MOTOR_STOP));
    yButton.whenHeld(new IntakeCommand(intake, IntakeState.MOTOR_OUT));
    yButton.whenReleased(new IntakeCommand(intake, IntakeState.MOTOR_STOP));
    bButton.whenHeld(new IntakeCommand(intake, IntakeState.PIVOT_OUT));
    bButton.whenReleased(new IntakeCommand(intake, IntakeState.PIVOT_STOP));
    rBumper.whileHeld(new IntakeCommand(intake, IntakeState.PIVOT_IN));
    rBumper.whenReleased(new IntakeCommand(intake, IntakeState.PIVOT_STOP));
    aButton.whileHeld(new ClimberCommand(climber, ClimberState.UP));
    aButton.whenReleased(new ClimberCommand(climber, ClimberState.STOP));
    xButton.whileHeld(new ClimberCommand(climber, ClimberState.DOWN));
    xButton.whenReleased(new ClimberCommand(climber, ClimberState.STOP));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
