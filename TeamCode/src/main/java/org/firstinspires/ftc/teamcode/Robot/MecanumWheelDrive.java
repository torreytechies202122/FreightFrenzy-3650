package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MecanumWheelDrive {
    private DcMotor bR;
    private DcMotor bL;
    private DcMotor fR;
    private DcMotor fL;

    public MecanumWheelDrive(DcMotor bR, DcMotor bL, DcMotor fR, DcMotor fL){
        this.bR = bR;
        this.bL = bL;
        this.fR = fR;
        this.fL = fL;
    }

    public void drive(double powerbR, double powerbL, double powerfR, double powerfL){
        bR.setPower(powerbR);
        bL.setPower(powerbL);
        fR.setPower(powerfR);
        fL.setPower(powerfL);
    }
}
