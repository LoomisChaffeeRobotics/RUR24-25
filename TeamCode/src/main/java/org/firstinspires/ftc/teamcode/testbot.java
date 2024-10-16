package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class testbot extends OpMode {

    Servo Claw;
    float speedfactor = 0.002F;
    float Clawclose = 0.39F;
    float Clawopen = Clawclose + 0.29F;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;
    DcMotor tester;
    double x = 0;
    double y = 0;
    double rx = 0;
    double n = 0;
    double ns = 0;
    int cnt = 1;
    int rtdepressed = 0;


    @Override
    public void init() {

        Claw = hardwareMap.get(Servo.class, "Claw");
        Claw.setPosition(Clawopen);

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        tester = hardwareMap.get(DcMotor.class, "tester");
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


        if ((gamepad1.right_trigger >= 0.2 && !gamepad1.left_bumper) && cnt == 2 && rtdepressed == 0) {
            Claw.setPosition(Clawopen);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));
            cnt = 1;
            rtdepressed = 1;
        }
        else if ((gamepad1.right_trigger >= 0.2 && !gamepad1.left_bumper) && cnt == 1 && rtdepressed == 0) {
            Claw.setPosition(Clawclose);
            telemetry.addData("Claw Pos:", (Claw.getPosition()));
            cnt = 2;
            rtdepressed = 1;
        }
        if (gamepad1.right_trigger < 0.2) {rtdepressed = 0;}

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


        if (gamepad1.dpad_right && n == 0)
            ns = 0.7;
            tester.setPower(ns);
            n = 1;
        if (gamepad1.dpad_left && n == 0)
            ns = 0.7;
            tester.setPower(-ns);
            n = 1;
        if (!gamepad1.dpad_left && !gamepad1.dpad_right)
            n = 0;
            ns = 0;
            tester.setPower(ns);


        frontLeft.setPower(y + x + rx);
        rearLeft.setPower(y - x + rx);
        frontRight.setPower(y - x - rx);
        rearRight.setPower(y + x - rx);
        y = 0;
        x = 0;
        rx = 0;
    }

}
