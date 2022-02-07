package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class MecanumDrive {
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;

    public MecanumDrive(DcMotorEx frontLeft, DcMotorEx frontRight, DcMotorEx backLeft, DcMotorEx backRight){
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(double powerfL, double powerfR, double powerbL, double powerbR){
        frontLeft.setPower(powerfL);
        frontRight.setPower(powerfR);
        backLeft.setPower(powerbL);
        backRight.setPower(powerbR);

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void driveLeft(double powerfL, double powerbL){
        frontLeft.setPower(powerfL);
        backLeft.setPower(powerbL);
    }

    public void driveRight(double powerfR, double powerbR){
        frontRight.setPower(powerfR);
        backRight.setPower(powerbR);
    }

    public void setMode(DcMotor.RunMode runMode){
        frontLeft.setMode(runMode);
        frontRight.setMode(runMode);
        backLeft.setMode(runMode);
        backRight.setMode(runMode);
    }

    public void stop(){
        drive(0, 0, 0, 0);
    }

    public boolean isStall(){
        if(frontLeft.getCurrent(CurrentUnit.AMPS) >= 9.2){
            return true;
        } else if (frontRight.getCurrent(CurrentUnit.AMPS) >= 9.2){
            return true;
        } else if (backLeft.getCurrent(CurrentUnit.AMPS) >= 9.2){
            return true;
        } else if (backRight.getCurrent(CurrentUnit.AMPS) >= 9.2){
            return true;
        } else {
            return false;
        }
    }
}