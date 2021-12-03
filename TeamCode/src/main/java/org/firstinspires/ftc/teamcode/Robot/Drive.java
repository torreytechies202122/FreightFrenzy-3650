package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Drive {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    public Drive(DcMotor leftMotor, DcMotor rightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(double powerL, double powerR){
        leftMotor.setPower(powerL);
        rightMotor.setPower(powerR);
    }
}
