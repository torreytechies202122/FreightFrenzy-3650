package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.robot.Robot;

@TeleOp
public class TeleOpMode extends LinearOpMode {
    private DcMotor motorL;
    private DcMotor motorR;
    private CRServo feeder;

    @Override
    public void runOpMode() throws InterruptedException {
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        feeder = hardwareMap.get(CRServo.class, "feeder");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        double powerL = 0;
        double powerR = 0;
        double powerFeeder = 0;
        while(opModeIsActive()){
            powerL = gamepad1.left_stick_y - gamepad1.right_stick_x;
            powerR = gamepad1.left_stick_y - gamepad1.right_stick_x;

            if(gamepad1.right_trigger != 0) {powerFeeder = 1;} else {powerFeeder = 0;}

            feeder.setPower(powerFeeder);
            motorL.setPower(powerL);
            motorR.setPower(powerR);
            telemetry.update();
        }
    }
}
