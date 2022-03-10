package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Robot.MecanumDrive;
import org.firstinspires.ftc.teamcode.Robot.Spinner;

@Autonomous
public class BlueAutonomous extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap.get(DcMotorEx.class, "frontLeft"),
                hardwareMap.get(DcMotorEx.class, "frontRight"),
                hardwareMap.get(DcMotorEx.class, "backLeft"),
                hardwareMap.get(DcMotorEx.class, "backRight"), false);
        Spinner spinner = new Spinner(hardwareMap.get(CRServo.class, "spinner"));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()){
            spinner.spin(1);
            mecanumDrive.drive(-0.5, -0.5, -0.5, -0.5);
            pause(150);
            mecanumDrive.drive(0.5, -0.5, -0.5, 0.5);
            pause(2700);
            mecanumDrive.drive(0.125, 0.125,0.125, 0.125);
            pause(1300);
            mecanumDrive.drive(0, 0, 0, 0);
            pause(3500);
            mecanumDrive.drive(-0.5, -0.5, -0.5, -0.5);
            pause(450);
            mecanumDrive.drive(0, 0, 0, 0);
            break;
        }
    }

    public void pause(long time){
        long startTime = System.currentTimeMillis();
        while(System.currentTimeMillis() < time + startTime){

        }
    }
}