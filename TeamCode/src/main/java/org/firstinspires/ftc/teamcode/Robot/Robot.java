package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    HardwareMap hardwareMap;

    private Drive drive;
    private MecanumWheelDrive mecanumDrive;
    private Feeder feeder;
    private Lifter lifter;

    public Robot(HardwareMap hardwaremap, Gamepad gamepad1, Gamepad gamepad2){
        this.hardwareMap = hardwareMap;
    }

    public void drive(double bR, double bL, double fR, double fL){
        mecanumDrive.drive(bR, bL, fR, fL);
    }

    public void drive(double l, double r){
        drive.drive(l, r);
    }

    public Feeder getFeeder(){
        return feeder;
    }

    public Lifter getLifter(){
        return lifter;
    }

    public void init(){
        mecanumDrive = new MecanumWheelDrive(hardwareMap.get(DcMotor.class, "bR"), hardwareMap.get(DcMotor.class, "bL"), hardwareMap.get(DcMotor.class, "fR"), hardwareMap.get(DcMotor.class, "fR"));
    }
}