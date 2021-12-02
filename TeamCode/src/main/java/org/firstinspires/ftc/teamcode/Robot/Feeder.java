package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;

public class Feeder {
    private CRServo spinner;
    private CRServo lifter;

    public Feeder(CRServo spinner, CRServo lifter){
        this.spinner = spinner;
        this.lifter = lifter;
    }

    public void start(int direction){
        spinner.setPower(direction);
    }

    public void stop(){
        spinner.setPower(0);
    }

    public void lift(String direction){
        switch (direction){
            case ("up"):
                lifter.setPower(1);
                break;
            case ("down"):
                lifter.setPower(-1);
                break;
            case ("stop"):
                lifter.setPower(0);
                break;
        }
    }
}
