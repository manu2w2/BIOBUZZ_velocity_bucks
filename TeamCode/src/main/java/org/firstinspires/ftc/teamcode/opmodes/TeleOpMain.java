package org.firstinspires.ftc.teamcode.opmodes;

import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCommand;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSubsystem;

@TeleOp(name = "SolversLib: TeleOp Mecanum", group = "TeleOp")
public class TeleOpMain extends CommandOpMode {

  private DriveSubsystem driveSubsystem;
  //private IntakeSubsystem intakeSubsystem;
  private GamepadEx driverGamepad;

  @Override
  public void initialize() {
    // 1. Inicializar el Gamepad envolviéndolo en el GamepadEx de SolversLib
    driverGamepad = new GamepadEx(gamepad1);

    // 2. Inicializar Subsistemas
    driveSubsystem = new DriveSubsystem(hardwareMap);
    //intakeSubsystem = new IntakeSubsystem(hardwareMap);

    // 3. Asignar Comando por defecto para el chasis
    driveSubsystem.setDefaultCommand(new DriveCommand(
      driveSubsystem,
      () -> driverGamepad.getLeftX(),   // Strafe (Eje X del stick izquierdo)
      () -> driverGamepad.getLeftY(),   // Forward (Eje Y del stick izquierdo)
      () -> driverGamepad.getRightX()   // Turn (Eje X del stick derecho)
    ));

    // 4. Asignar Eventos a los Botones para el Intake
    // Al mantener A -> Tragar; Al soltar A -> Parar
   // new GamepadButton(driverGamepad, GamepadKeys.Button.A)
     // .whenPressed(new InstantCommand(intakeSubsystem::intake, intakeSubsystem))
      //.whenReleased(new InstantCommand(intakeSubsystem::stop, intakeSubsystem));

    // Al mantener B -> Expulsar; Al soltar B -> Parar
   // new GamepadButton(driverGamepad, GamepadKeys.Button.B)
    //  .whenPressed(new InstantCommand(intakeSubsystem::outtake, intakeSubsystem))
     // .whenReleased(new InstantCommand(intakeSubsystem::stop, intakeSubsystem));
  }
}
