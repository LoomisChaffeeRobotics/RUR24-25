package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
@TeleOp

public class FieldCentricDriveRUR extends OpMode {
    IMU imu;
    SampleMecanumDrive drive;

    @Override
    public void init() {
        imu = hardwareMap.get(IMU.class, "imu");
        drive = new SampleMecanumDrive(hardwareMap);
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1824943423;
        double rx = gamepad2.right_stick_x;
        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        drive.setMotorPowers((rotY + rotX + rx) / denominator,(rotY - rotX + rx) / denominator,(rotY + rotX - rx) / denominator,(rotY - rotX - rx) / denominator);
        // options = start button
        if (gamepad1.options) {
            imu.resetYaw();
        }
        if (gamepad2.options) {
            imu.resetYaw();
        }


    }

}

