package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;


@TeleOp
public class DoNothingWithCameraOn extends OpMode {

    IMU imu;
    AprilTagProcessor myAprilTagProcessor;
    VisionPortal myVisionPortal;
    AprilTagDetection myAprilTagDetection;

    @Override
    public void init() {
        imu = hardwareMap.get(IMU.class, "imu");
// Create the AprilTag processor and assign it to a variable.
        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        // Enable or disable the AprilTag processor.
        myVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), myAprilTagProcessor);
//        myAprilTagDetection = AprilTagDetection.easy;
    }

    @Override
    public void loop() {

        double myTagPoseX = myAprilTagDetection.ftcPose.x;
        double myTagPoseY = myAprilTagDetection.ftcPose.y;
        double myTagPoseZ = myAprilTagDetection.ftcPose.z;
        double myTagPosePitch = myAprilTagDetection.ftcPose.pitch;
        double myTagPoseRoll = myAprilTagDetection.ftcPose.roll;
        double myTagPoseYaw = myAprilTagDetection.ftcPose.yaw;
    }
}
