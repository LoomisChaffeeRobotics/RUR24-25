package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;


@TeleOp
public class DoNothingWithCameraOn extends OpMode {

    AprilTagProcessor myAprilTagProcessor;
    VisionPortal myVisionPortal;

    @Override
    public void init() {
// Create the AprilTag processor and assign it to a variable.
        myAprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
        // Enable or disable the AprilTag processor.
        myVisionPortal = VisionPortal.easyCreateWithDefaults(hardwareMap.get(WebcamName.class, "Webcam 1"), myAprilTagProcessor);
    }

    @Override
    public void loop() {

    }
}
