package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Robot.Drive;
import org.firstinspires.ftc.teamcode.Robot.Feeder;
import org.firstinspires.ftc.teamcode.Robot.Lifter;
import org.firstinspires.ftc.teamcode.Robot.MecanumWheelDrive;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp
public class MecanumWheelMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        Robot robot = new Robot(hardwareMap, gamepad1, gamepad2);
        robot.init();
        waitForStart();

        while(opModeIsActive()){
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            double bR = r * Math.cos(robotAngle) + rightX;
            double bL = r * Math.sin(robotAngle) - rightX;
            double fR = r * Math.sin(robotAngle) + rightX;
            double fL = r * Math.cos(robotAngle) - rightX;
            robot.drive(bR, bL, fR, fL);
        }
    }
}
