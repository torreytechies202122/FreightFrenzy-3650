//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import org.firstinspires.ftc.teamcode.Robot.Robot;
//
//@TeleOp
//public class FieldCentricMode extends LinearOpMode {
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        Robot robot = new Robot();
//        robot.init(hardwareMap);
//
//        telemetry.addData("Status", "Initialized");
//        telemetry.update();
//        waitForStart();
//
//        while(opModeIsActive()){
////            double cosA = Math.cos(-robot.getIMU().getAngle());
////            double sinA = Math.sin(-robot.getIMU().getAngle());
////            double x = gamepad1.left_stick_x * cosA - gamepad1.left_stick_y * sinA;
////            double y = gamepad1.left_stick_x * sinA + gamepad1.left_stick_y * cosA;
////            double robotAngle = Math.atan2(y, x);
//            double robotAngle = robot.getIMU().getAngle();
//            telemetry.addData("Robot Angle", Math.toDegrees(robotAngle));
//            telemetry.addData("Gyro Angle", Math.toDegrees(robot.getIMU().getAngle()));
//            double cosA = Math.cos(robotAngle);
//            double sinA = Math.sin(robotAngle);
//            double x = gamepad1.left_stick_x * cosA - gamepad1.left_stick_y*sinA;
//            double y = gamepad1.left_stick_x * sinA + gamepad1.left_stick_y*cosA;
//            double angle = Math.atan2(y, x);
//            double r = Math.hypot(x, y);
//
//            double fL = (Math.sin(angle + Math.PI/4))*r;
//            double fR = (Math.sin(angle - Math.PI/4))*r;
//            double bL = (Math.sin(angle - Math.PI/4))*r;
//            double bR = (Math.sin(angle + Math.PI/4))*r;
//
//            fL-=gamepad1.right_stick_x;
//            fR+=gamepad1.right_stick_x;
//            bL-=gamepad1.right_stick_x;
//            bR+=gamepad1.right_stick_x;
//
//            if(gamepad1.a){
//                robot.getSpinner().spin(1);
//            } else if(gamepad1.b){
//                robot.getSpinner().spin(-1);
//            } else {
//                robot.getSpinner().spin(0);
//            }
//
//            robot.getMecanumDrive().drive(fL, fR, bL, bR);
//            telemetry.update();
//        }
//    }
//}
