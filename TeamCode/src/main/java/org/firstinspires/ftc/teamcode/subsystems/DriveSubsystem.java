package org.firstinspires.ftc.teamcode.subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.constants.RobotConstants;

public class DriveSubsystem extends SubsystemBase {

  private final MecanumDrive drive;
  private final Motor frontLeft;
  private final Motor frontRight;
  private final Motor backLeft;
  private final Motor backRight;

  public DriveSubsystem(HardwareMap hardwareMap) {
    // SolversLib Motor class
    frontLeft = new Motor(hardwareMap, RobotConstants.FRONT_LEFT_MOTOR);
    frontRight = new Motor(hardwareMap, RobotConstants.FRONT_RIGHT_MOTOR);
    backLeft = new Motor(hardwareMap, RobotConstants.BACK_LEFT_MOTOR);
    backRight = new Motor(hardwareMap, RobotConstants.BACK_RIGHT_MOTOR);

    // Si usas goBILDA u otros motores, suele ser necesario invertir los de un lado
    frontRight.setInverted(true);
    backRight.setInverted(true);

    drive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
  }

  public void driveRobotCentric(double strafe, double forward, double turn) {
    drive.driveRobotCentric(strafe, forward, turn);
  }
}
