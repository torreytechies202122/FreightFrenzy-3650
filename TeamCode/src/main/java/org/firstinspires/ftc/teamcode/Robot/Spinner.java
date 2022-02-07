package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Spinner {
    private CRServo spinner;
    public Spinner(CRServo spinner){
        this.spinner = spinner;
    }

    public void spin(double power){
        spinner.setPower(power);
    }
}
