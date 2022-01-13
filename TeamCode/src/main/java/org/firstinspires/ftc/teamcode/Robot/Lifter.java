package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Lifter {
    private DcMotorEx lifter;
    private CRServo extender;
    private CRServo boxRotator;

    public Lifter(DcMotorEx lifter, CRServo extender, CRServo boxRotator) {
        this.lifter = lifter;
        this.extender = extender;
        this.boxRotator = boxRotator;
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void move(double power) {
        lifter.setPower(power);
    }

    public void extend() {
        extender.setPower(1);
    }

    public void retract() {
        extender.setPower(-1);
    }

    public void stopExtender() {
        extender.setPower(0);
    }

    public void rotateBox(double power) {
        boxRotator.setPower(power);
    }

    public void liftToLevel(int level){
        if(level == 1){
        }
    }

}
