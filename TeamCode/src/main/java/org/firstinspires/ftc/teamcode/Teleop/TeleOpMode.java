package org.firstinspires.ftc.teamcode.Teleop;

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
                hardwareMap.get(DcMotorEx.class, "backRight"), false);
        Spinner spinner = new Spinner(hardwareMap.get(CRServo.class, "spinner"));
        Lifter lifter = new Lifter(hardwareMap.get(DcMotorEx.class, "lifter"),
                hardwareMap.get(CRServo.class, "claw"));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        while(opModeIsActive()){
            //Get the hypotenuse of the right triangle created by the position of the left gamepad stick on the x and y axis
            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);

            /*Get the angle that the left gamepad stick creates with the horizontal x axis.
            This angle is then offset by PI/4 so that when the stick is
            all the way in one direction the sin and cos are the same*/
            double angle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI/4;

            /*Set the variables for the power of each of the motors to the inverse of either the cos or sin of the angle above,
            then multiply by the hypotenuse to get speed. The negative sign is there because the output of the gamepad
            is inverted.*/
            double fL = -Math.cos(angle)*r;
            double fR = -Math.sin(angle)*r;
            double bL = -Math.sin(angle)*r;
            double bR = -Math.cos(angle)*r;

            //Add or subract the x value of the right stick to each of the motors so that the
            // robot can turn with the right stick.
            fL -= gamepad1.right_stick_x;
            fR += gamepad1.right_stick_x;
            bL -= gamepad1.right_stick_x;
            bR += gamepad1.right_stick_x;

            double liftPower = 0;
            double clawPower = 0;

            //Control for the claw using the right and left bumpers
            if(gamepad1.right_bumper){
                clawPower = -1;
            } else if(gamepad1.left_bumper){
                clawPower = 1;
            } else {
                clawPower = 0;
            }

            //Control for the lifting arm using the left and right triggers
            if(gamepad1.left_trigger > 0){
                liftPower = -gamepad1.left_trigger;
            } else if(gamepad1.right_trigger > 0){
                liftPower = gamepad1.right_trigger;
            }

            //Control for the spinner using the a and b buttons
            if(gamepad1.a){
                spinner.spin(1);
            } else if(gamepad1.b){
                spinner.spin(-1);
            } else {
                spinner.spin(0);
            }

            //Setting the power of the motors to the variables that were created earlier in this op mode.
            lifter.setClawPower(clawPower);
            lifter.move(liftPower);
            mecanumDrive.drive(fL, fR, bL, bR);
            telemetry.addData("Velocities", mecanumDrive.getVelocity());
            telemetry.update();
        }
    }
}
