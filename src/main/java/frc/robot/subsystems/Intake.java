package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private CANSparkMax intakePivot;
    private CANSparkMax intakeMotor;
    private RelativeEncoder motorEncoder;
    private final int minPivot = 0;
    private final int maxPivot = 45;
    public Intake() {
        intakePivot = new CANSparkMax(14, MotorType.kBrushless);
        intakeMotor = new CANSparkMax(12, MotorType.kBrushless);
        motorEncoder = intakeMotor.getEncoder();
    }

    public void motorForward() {
        intakeMotor.set(0.5);
    }

    public void motorBackward() {
        intakeMotor.set(-0.5);
    }

    public void stopMotor() {
        intakeMotor.set(0);
    }

    public void pivotForward() {
        if (getPos() < maxPivot) {
            intakePivot.set(1);
        }
        else {
            stopPivot();
        }
    }

    public void pivotBackward() {
        if (getPos() > minPivot) {
            intakePivot.set(-1);
        }
        else {
            stopPivot();
        }
    }

    public void stopPivot() {
        intakePivot.set(0);
    }

    public double getPos() {
        return motorEncoder.getPosition();
    }
}
