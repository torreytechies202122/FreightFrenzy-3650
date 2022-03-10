package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Robot.Lifter;
import org.firstinspires.ftc.teamcode.Robot.MecanumDrive;
import org.firstinspires.ftc.teamcode.Robot.Spinner;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous
public class OpenCVAutonomousRed extends LinearOpMode {
    OpenCvWebcam webcam;
    private ShippingElementDetector detector;
    private int level;

    @Override
    public void runOpMode() {
        detector = new ShippingElementDetector();
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap.get(DcMotorEx.class, "frontLeft"),
                hardwareMap.get(DcMotorEx.class, "frontRight"),
                hardwareMap.get(DcMotorEx.class, "backLeft"),
                hardwareMap.get(DcMotorEx.class, "backRight"), false);
        Spinner spinner = new Spinner(hardwareMap.get(CRServo.class, "spinner"));
        Lifter lifter = new Lifter(hardwareMap.get(DcMotorEx.class, "lifter"),
                hardwareMap.get(CRServo.class, "claw"));
        int cameraMonitorViewId = hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.setPipeline(detector);

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){
            @Override
            public void onOpened() {
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        lifter.setClawPower(1);
        mecanumDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        telemetry.addLine("Waiting for Start");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()) {
            level = detector.getLevel();
            telemetry.addData("level", level);
            telemetry.update();
            lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            mecanumDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lifter.setClawPower(0);
            switch (level) {
                case 1:
                    //lift arm up
                    lifter.setVelocity(1);
                    sleep(450);
                    lifter.setVelocity(0);

                    //Strafe right
                    mecanumDrive.drive(-1, 1, 1, -1);
                    sleep(750);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //Put arm into drop off position
                    lifter.setVelocity(1);
                    sleep(1000);
                    lifter.setVelocity(0);

                    //Drive to shipping hub
                    mecanumDrive.driveVelocity(1, 1, 1, 1);
                    sleep(350);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //release claw
                    lifter.setClawPower(-1);
                    sleep(400);
                    lifter.setClawPower(0);

                    //drive backwards
                    mecanumDrive.driveVelocity(-1, -1, -1, -1);
                    sleep(250);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //move lifter back
                    lifter.setVelocity(-1);
                    sleep(700);
                    lifter.setVelocity(0);
                    break;
                case 2:
                    //lift arm up
                    lifter.setVelocity(1);
                    sleep(450);
                    lifter.setVelocity(0);

                    //Strafe right
                    mecanumDrive.drive(-1, 1, 1, -1);
                    sleep(750);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //Put arm into drop off position
                    lifter.setVelocity(1);
                    sleep(800);
                    lifter.setVelocity(0);

                    //Drive to shipping hub
                    mecanumDrive.driveVelocity(1, 1, 1, 1);
                    sleep(350);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //release claw
                    lifter.setClawPower(-1);
                    sleep(400);
                    lifter.setClawPower(0);

                    //drive backwards
                    mecanumDrive.driveVelocity(-1, -1, -1, -1);
                    sleep(250);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //move lifter back
                    lifter.setVelocity(-1);
                    sleep(700);
                    lifter.setVelocity(0);
                    break;
                case 3:
                    //lift arm up
                    lifter.setVelocity(1);
                    sleep(450);
                    lifter.setVelocity(0);

                    //Strafe right
                    mecanumDrive.drive(-1, 1, 1, -1);
                    sleep(750);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //Put arm into drop off position
                    lifter.setVelocity(1);
                    sleep(500);
                    lifter.setVelocity(0);

                    //Drive to shipping hub
                    mecanumDrive.driveVelocity(1, 1, 1, 1);
                    sleep(350);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //release claw
                    lifter.setClawPower(-1);
                    sleep(400);
                    lifter.setClawPower(0);

                    //drive backwards
                    mecanumDrive.driveVelocity(-1, -1, -1, -1);
                    sleep(250);
                    mecanumDrive.driveVelocity(0, 0, 0, 0);

                    //move lifter back
                    lifter.setVelocity(-1);
                    sleep(700);
                    lifter.setVelocity(0);
                    break;
            }
            if(level != 0){
                break;
            }
        }
    }
}