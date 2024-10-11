package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class claw_mecanum extends OpMode {

    Servo Claw;
    float speedfactor = 0.002F;
    float Clawclose = 0.4600F;
    float Clawopen = 0.7500F;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;
    double x = 0;
    double y = 0;
    double rx = 0;


    @Override
    public void init() {

        Claw = hardwareMap.get(Servo.class, "Claw");
        Claw.setPosition(Clawopen);

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }


    @Override
    public void loop() {
        if (gamepad1.back) {
            Claw.setPosition(Clawopen);

        }


        if (gamepad1.right_trigger >= 0.2 && !gamepad1.left_bumper && Claw.getPosition() == Clawclose) {
            Claw.setPosition(Clawopen);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));
        }
        else if (gamepad1.right_trigger >= 0.2 && !gamepad1.left_bumper && Claw.getPosition() == Clawopen) {
            Claw.setPosition(Clawclose);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));
        }

        if (gamepad1.right_trigger >= 0.2 && gamepad1.left_bumper) {
            Claw.setPosition(Claw.getPosition() - speedfactor);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));
        }
        if (gamepad1.right_bumper && gamepad1.left_bumper) {
            Claw.setPosition(Claw.getPosition() + speedfactor);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));
        }


        if (gamepad1.left_trigger >= 0.3) {
            y = (0.3) * gamepad1.left_stick_y;
        }
        else if (gamepad1.left_trigger <= 0.3){
            y = gamepad1.left_stick_y;
        }

        if (gamepad1.left_trigger >= 0.3) {
            x = (0.3) * gamepad1.left_stick_x;
        }
        else if (gamepad1.left_trigger <= 0.3){
            x = gamepad1.left_stick_x;
        }

        if (gamepad1.left_trigger >= 0.3) {
            rx = (0.3) * gamepad1.right_stick_x;
        }
        else if (gamepad1.left_trigger <= 0.3){
            rx = gamepad1.right_stick_x;
        }


        frontLeft.setPower(y + x + rx);
        rearLeft.setPower(y - x + rx);
        frontRight.setPower(y - x - rx);
        rearRight.setPower(y + x - rx);
        y = 0;
        x = 0;
        rx = 0;
    }

}
