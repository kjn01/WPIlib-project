package frc.robot.subsystems;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

    private CANSparkMax climberMotor;
    private RelativeEncoder climberEncoder;

    private final int maxHeight = 182;
    private final int minHeight = 0;

    public Climber() {
        climberMotor = new CANSparkMax(19, MotorType.kBrushless);
        climberEncoder = climberMotor.getEncoder();
    }

    public void up() {
        if (getPos() < maxHeight) {
            climberMotor.set(1);
        } else {
            stop();
        }
    }

    public void down() {
        if (getPos() > minHeight) {
            climberMotor.set(-1);
        } else {
            stop();
        }
    }

    public void stop() {
        climberMotor.set(0);
    }

    public double getPos() {
        return climberEncoder.getPosition();
    }

}