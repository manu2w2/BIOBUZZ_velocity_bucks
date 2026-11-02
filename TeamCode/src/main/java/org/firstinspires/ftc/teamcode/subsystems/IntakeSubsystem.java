package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.constants.RobotConstants;

public class IntakeSubsystem extends SubsystemBase {

  private final Motor intakeMotor;

  public IntakeSubsystem(HardwareMap hardwareMap) {
    intakeMotor = new Motor(hardwareMap, RobotConstants.INTAKE_MOTOR);
    // SolversLib mantiene la misma API robusta para motores
    intakeMotor.setRunMode(Motor.RunMode.RawPower);
    intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
  }

  public void setPower(double power) {
    intakeMotor.set(power);
  }

  public void intake() {
    setPower(RobotConstants.INTAKE_POWER);
  }

  public void outtake() {
    setPower(-RobotConstants.INTAKE_POWER);
  }

  public void stop() {
    setPower(0);
  }
}
