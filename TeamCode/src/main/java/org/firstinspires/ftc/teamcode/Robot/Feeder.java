package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.CRServo;

public class Feeder {
    private CRServo topSpinner;
    private CRServo bottomSpinner;
    private CRServo lifter;


    public Feeder(CRServo topSpinner, CRServo bottomSpinner, CRServo lifter){
        this.topSpinner = topSpinner;
        this.lifter = lifter;
        this.bottomSpinner = bottomSpinner;
    }

    public void start(int direction){
        topSpinner.setPower(direction);
        bottomSpinner.setPower(direction);
    }

    public void stop(){
        topSpinner.setPower(0);
        bottomSpinner.setPower(0);
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
