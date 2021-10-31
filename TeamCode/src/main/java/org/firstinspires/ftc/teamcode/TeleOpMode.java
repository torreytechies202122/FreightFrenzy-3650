package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleOpMode extends LinearOpMode {
    private DcMotor motorL;
    private DcMotor motorR;
    //private DcMotor feeder;
//    private DcMotor lifter1;
//    private DcMotor lifter2;
    private CRServo lifter;

    @Override
    public void runOpMode() throws InterruptedException {
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        //feeder = hardwareMap.get(DcMotor.class, "feeder");
        motorR.setDirection(DcMotorSimple.Direction.REVERSE);
        //feeder.setDirection(DcMotorSimple.Direction.REVERSE);
//        lifter1 = hardwareMap.get(DcMotor.class, "lifter1");
//        lifter2 = hardwareMap.get(DcMotor.class, "lifter2");
//        setLifterPosition(lifter1.getCurrentPosition());
//        lifter1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        lifter2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        lifter1.setPower(1);
//        lifter2.setPower(1);
        lifter = hardwareMap.get(CRServo.class, "lifter");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();

        double rotation = 0;
        double powerL = 0;
        double powerR = 0;
        double powerFeeder = 0;
        double lifterPower = 0;
        while(opModeIsActive()){
            rotation = gamepad1.right_stick_x;
            powerL = gamepad1.left_stick_y - rotation;
            powerR = gamepad1.left_stick_y + rotation;

            if(gamepad1.right_trigger != 0){
                powerFeeder = 1;
            } else {
                powerFeeder = 0;
            }


            if(gamepad1.y){
                lifterPower = 1;
            }
            if(gamepad1.x){
                lifterPower = 0;
            }
            if(!gamepad1.x && !gamepad1.y){
                lifterPower = 0.;
            }

            lifter.setPower(lifterPower);
            telemetry.addData("Servo power", lifter.getPower());
            motorL.setPower(powerL);
            motorR.setPower(powerR);
            //feeder.setPower(pF);
            telemetry.update();
        }
    }

//    public void setLifterPosition(int p){
//        lifter1.setTargetPosition(p);
//        lifter2.setTargetPosition(p);
//    }
}
