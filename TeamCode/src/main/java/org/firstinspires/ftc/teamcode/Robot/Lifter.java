package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Lifter {
    private DcMotorEx lifter;
    private CRServo claw;

    public Lifter(DcMotorEx lifter, CRServo claw) {
        this.lifter = lifter;
        this.claw = claw;
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void move(double power) {
        lifter.setPower(power);
    }

    public void setVelocity(double velocity){
        lifter.setVelocity(velocity*1040);
    }

    public void setClawPower(double power){
        claw.setPower(power);
    }

    public void setMode(DcMotor.RunMode runMode){
        lifter.setMode(runMode);
    }
}
