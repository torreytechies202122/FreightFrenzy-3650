package org.firstinspires.ftc.teamcode.Robot;

public class Robot {
    private Drive drive;
    private Feeder feeder;
    private Lifter lifter;

    public Robot(Drive drive, Feeder feeder, Lifter lifter){
        this.drive = drive;
        this.feeder = feeder;
        this.lifter = lifter;
    }

    public Drive getDrive(){
        return drive;
    }

    public Feeder getFeeder(){
        return feeder;
    }

    public Lifter getLifter(){
        return lifter;
    }
}
