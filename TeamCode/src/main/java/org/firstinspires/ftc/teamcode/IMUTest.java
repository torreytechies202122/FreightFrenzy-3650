package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import java.util.Locale;

@TeleOp
public class IMUTest extends LinearOpMode {
    private Orientation angles;
    private Acceleration gravity;
    private boolean test = true;
    private DcMotor motorL;
    private DcMotor motorR;
    private CRServo feeder;
    private BNO055IMU imu;
    private DcMotor lifter;

    @Override
    public void runOpMode() throws InterruptedException {
        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        feeder = hardwareMap.get(CRServo.class, "feeder");
        lifter = hardwareMap.get(DcMotor.class, "lifter");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);
        angles = imu.getAngularOrientation();
        angles.angleUnit = AngleUnit.DEGREES;
        angles.axesOrder = AxesOrder.XYZ;
        angles.axesReference = AxesReference.EXTRINSIC;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        composeTelemetry();
        waitForStart();

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);
        while(opModeIsActive()){
            angles = imu.getAngularOrientation();

            if(gamepad1.a){
                if(test){
                    turnToAngle(45);
                    test = false;
                }
            }
            if(gamepad1.b){
                telemetry.addData("Angle", angles.thirdAngle);
                turn("right", 0.75);
            } else {
                motorL.setPower(0);
                motorR.setPower(0);
            }

            telemetry.update();
        }
    }

    private void turnToAngle(float angle){
        angles = imu.getAngularOrientation();
        double t1 = 0;
        double t2 = 0;
        boolean asdf = true;
        double loopTime = 0;
        while(!(angles.thirdAngle < angle+5 && angles.thirdAngle > angle-5)){
            if(asdf){
                t1 = System.currentTimeMillis();
            }
            if(!asdf){
                t2 = System.currentTimeMillis();
                loopTime = t2-t1;
            }
            asdf = false;
            angles = imu.getAngularOrientation();
            double error = angle - angles.thirdAngle;
            double P = -(0.25 + Math.abs((0.75/360)));
            if(error < 0){
                turn("right", P*error);
            }
            if(error > 0){
                turn("left", P*error);
            }
        }
        motorL.setPower(0);
        motorR.setPower(0);
        telemetry.addData("loop time", loopTime);
    }

    private void turn(String direction, double power) {
        if (direction.equals("left")) {
            motorL.setPower(-1 * power);
            motorR.setPower(-1 * power);
        } else if (direction.equals("right")) {
            motorL.setPower(1 * power);
            motorR.setPower(1 * power);
        }
    }

    private void composeTelemetry() {

        // At the beginning of each telemetry update, grab a bunch of data
        // from the IMU that we will then display in separate lines.
        telemetry.addAction(new Runnable() { @Override public void run()
        {
            // Acquiring the angles is relatively expensive; we don't want
            // to do that in each of the three items that need that info, as that's
            // three times the necessary expense.
            angles   = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            gravity  = imu.getGravity();
        }
        });

        telemetry.addLine()
                .addData("status", new Func<String>() {
                    @Override public String value() {
                        return imu.getSystemStatus().toShortString();
                    }
                })
                .addData("calib", new Func<String>() {
                    @Override public String value() {
                        return imu.getCalibrationStatus().toString();
                    }
                });

        telemetry.addLine()
                .addData("heading", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.firstAngle);
                    }
                })
                .addData("roll", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.secondAngle);
                    }
                })
                .addData("pitch", new Func<String>() {
                    @Override public String value() {
                        return formatAngle(angles.angleUnit, angles.thirdAngle);
                    }
                });

        telemetry.addLine()
                .addData("gravity", new Func<String>() {
                    @Override public String value() {
                        return gravity.toString();
                    }
                })
                .addData("mag", new Func<String>() {
                    @Override public String value() {
                        return String.format(Locale.getDefault(), "%.3f",
                                Math.sqrt(gravity.xAccel*gravity.xAccel
                                        + gravity.yAccel*gravity.yAccel
                                        + gravity.zAccel*gravity.zAccel));
                    }
                });
    }

    //----------------------------------------------------------------------------------------------
    // Formatting
    //----------------------------------------------------------------------------------------------

    String formatAngle(AngleUnit angleUnit, double angle) {
        return formatDegrees(AngleUnit.DEGREES.fromUnit(angleUnit, angle));
    }

    String formatDegrees(double degrees){
        return String.format(Locale.getDefault(), "%.1f", AngleUnit.DEGREES.normalize(degrees));
    }
}
