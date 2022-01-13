package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Robot.Drive;
import org.firstinspires.ftc.teamcode.Robot.Feeder;
import org.firstinspires.ftc.teamcode.Robot.Lifter;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@TeleOp
public class NormalWheelMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Robot robot = new Robot(hardwareMap, gamepad1, gamepad2);
        robot.init();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()){

            if(gamepad1.right_trigger != 0) {
                robot.getFeeder().start(1);
            } else if(gamepad1.left_trigger != 0){
                robot.getFeeder().start(-1);
            } else {
                robot.getFeeder().stop();
            }

            if(gamepad1.dpad_up){
                robot.getFeeder().lift("up");
            } else if(gamepad1.dpad_down){
                robot.getFeeder().lift("down");
            } else {
                robot.getFeeder().lift("stop");
            }

            if(gamepad2.right_stick_x > 0){
                robot.getLifter().extend();
            } else if(gamepad2.right_stick_x < 0){
                robot.getLifter().retract();
            } else {
                robot.getLifter().stopExtender();
            }

            if(gamepad2.right_bumper){
                robot.getLifter().rotateBox(1);
            } else if(gamepad2.left_bumper){
                robot.getLifter().rotateBox(-1);
            } else {
                robot.getLifter().rotateBox(-0.05);
            }

            robot.getLifter().move(-gamepad2.left_stick_y);
            robot.drive(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
            telemetry.update();
        }
    }
}