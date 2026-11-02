package org.firstinspires.ftc.teamcode.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOp Mecanum Oficial", group = "Chasis")
public class chasis_mecanum extends LinearOpMode {

    // 1. Declarar los 4 motores del chasis
    private DcMotor frontLeft  = null;
    private DcMotor backLeft   = null;
    private DcMotor frontRight = null;
    private DcMotor backRight  = null;

    @Override
    public void runOpMode() {

        // 2. Mapear los motores con los nombres exactos que pusiste en la Driver Station (Configuración)
        frontLeft  = hardwareMap.get(DcMotor.class, "front_left");
        backLeft   = hardwareMap.get(DcMotor.class, "back_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backRight  = hardwareMap.get(DcMotor.class, "back_right");

        // 3. INVERSIÓN CRÍTICA: Los motores de un lado deben girar al revés para avanzar recto.
        // Generalmente se invierte el lado izquierdo, pero si tu robot va hacia atrás, invierte el derecho.
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Inicializado - Esperando inicio");
        telemetry.update();

        // Esperar a que el piloto presione el botón PLAY
        waitForStart();

        // 4. Bucle principal de TeleOp (2 minutos)
        while (opModeIsActive()) {

            // Leer joysticks del Gamepad 1
            double y  = -gamepad1.left_stick_y;  // Avanzar/Retroceder (Invertido por defecto en mandos)
            double x  =  gamepad1.left_stick_x;  // Desplazamiento lateral (Strafe)
            double rx =  gamepad1.right_stick_x; // Rotación sobre su propio eje

            // Fórmulas matemáticas para la cinemática Mecanum
            double frontLeftPower  = y + x + rx;
            double backLeftPower   = y - x + rx;
            double frontRightPower = y - x - rx;
            double backRightPower  = y + x - rx;

            // Normalizar las potencias para que ningún valor supere 1.0 o -1.0
            double max = Math.max(Math.abs(frontLeftPower), Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backRightPower));

            if (max > 1.0) {
                frontLeftPower  /= max;
                backLeftPower   /= max;
                frontRightPower /= max;
                backRightPower  /= max;
            }

            // Asignar potencia calculada a cada uno de los motores
            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            // Enviar datos en tiempo real a la pantalla del teléfono/Driver Station (Útil para debugear)
            telemetry.addData("Motores Izquierdos", "Enf: %.2f, Atr: %.2f", frontLeftPower, backLeftPower);
            telemetry.addData("Motores Derechos", "Enf: %.2f, Atr: %.2f", frontRightPower, backRightPower);
            telemetry.update();
        }
    }
}
