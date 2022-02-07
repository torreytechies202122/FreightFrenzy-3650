package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Lifter {
    private DcMotor lifter;
    private CRServo claw;

    public Lifter(DcMotor lifter, CRServo claw) {
        this.lifter = lifter;
        this.claw = claw;
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void move(double power) {
        lifter.setPower(power);
    }

    public void setClawPower(double power){
        claw.setPower(power);
    }
}
