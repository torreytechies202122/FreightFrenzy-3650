package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Robot.Lifter;
import org.firstinspires.ftc.teamcode.Robot.MecanumDrive;
import org.firstinspires.ftc.teamcode.Robot.Spinner;

@TeleOp
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap.get(DcMotorEx.class, "frontLeft"),
                hardwareMap.get(DcMotorEx.class, "frontRight"),
                hardwareMap.get(DcMotorEx.class, "backLeft"),
                hardwareMap.get(DcMotorEx.class, "backRight"));
        Spinner spinner = new Spinner(hardwareMap.get(CRServo.class, "spinner"));
        Lifter lifter = new Lifter(hardwareMap.get(DcMotor.class, "lifter"), hardwareMap.get(CRServo.class, "claw"));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()){
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double angle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI/4;
            double fL = -Math.cos(angle)*r;
            double fR = -Math.sin(angle)*r;
            double bL = -Math.sin(angle)*r;
            double bR = -Math.cos(angle)*r;
            double liftPower = 0;
            double clawPower = 0;

            if(gamepad1.right_bumper){
                clawPower = -1;
            } else if(gamepad1.left_bumper){
                clawPower = 1;
            } else {
                clawPower = 0;
            }

            if(gamepad1.left_trigger > 0){
                liftPower = -gamepad1.left_trigger;
            } else if(gamepad1.right_trigger > 0){
                liftPower = gamepad1.right_trigger;
            }

            fL -= gamepad1.right_stick_x;
            fR += gamepad1.right_stick_x;
            bL -= gamepad1.right_stick_x;
            bR += gamepad1.right_stick_x;

            if(gamepad1.a){
                spinner.spin(1);
            } else if(gamepad1.b){
                spinner.spin(-1);
            } else {
                spinner.spin(0);
            }

            lifter.setClawPower(clawPower);
            lifter.move(liftPower);
            mecanumDrive.drive(fL, fR, bL, bR);
            telemetry.update();
        }
    }
}
