package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class claw_setup extends OpMode {


    Servo Claw;
    float speedfactor = 0.001F;
    float Clawclose = 0.7412F;
    float Clawopen = 0.7367F;


    @Override
    public void init() {
        Claw = hardwareMap.get(Servo.class, "Claw");


        Claw.setPosition(Clawopen);
    }


    @Override
    public void loop() {

        if (gamepad1.back) {
            Claw.setPosition(Clawopen);

        }

        if (gamepad1.dpad_right && !gamepad1.left_bumper) {
            Claw.setPosition(Clawclose);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));
        }
        if (gamepad1.dpad_left && !gamepad1.left_bumper) {
            Claw.setPosition(Clawopen);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));


            if (gamepad1.dpad_right && gamepad1.left_bumper) {
                Claw.setPosition(Claw.getPosition() - speedfactor);
                telemetry.addData("Claw Pos:", (Claw.getPosition()));
            }

        }

    }
}