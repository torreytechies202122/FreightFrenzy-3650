package org.firstinspires.ftc.teamcode.Autonomous;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class ShippingElementDetector extends OpenCvPipeline {
    Mat mat = new Mat();
    Rect leftRect = new Rect(new Point(0, 200), new Point(630, 720));
    Rect rightRect = new Rect(new Point(650, 200), new Point(1270, 720));
    double percentColorThreshold = 0.10;
    private int level;
//    private double maxArea = 0;
//    private Rect maxRect;

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(38, 100, 40);
        Scalar highHSV = new Scalar(81, 255, 255);

        Core.inRange(mat, lowHSV, highHSV, mat);

        Imgproc.rectangle(mat, leftRect, new Scalar(255, 0, 0));
        Imgproc.rectangle(mat, rightRect, new Scalar(255, 0, 0));

        Mat left = mat.submat(leftRect);
        Mat right = mat.submat(rightRect);

//        List<MatOfPoint> contours = new ArrayList<>();
//        Imgproc.findContours(mat, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
//
//        Imgproc.drawContours(mat, contours, -1, new Scalar(255, 0, 0));
//
//        for(MatOfPoint contour : contours){
//            Point[] contourArray = contour.toArray();
//
//            if(contourArray.length >= 15){
//                MatOfPoint areaPoints = new MatOfPoint(contourArray);
//                Rect rect = Imgproc.boundingRect(areaPoints);
//
//                if(rect.area() > maxArea){
//                    maxArea = rect.area();
//                    maxRect = rect;
//                }
//            }
//        }
//
//        if(maxRect.x < 320){
//            level = 1;
//        } else if(maxRect.x > 320){
//            level = 2;
//        } else {
//            level = 3;
//        }

        double leftValue = Core.sumElems(left).val[0] / leftRect.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / rightRect.area() / 255;

        left.release();
        right.release();

        boolean positionLeft = leftValue > percentColorThreshold;
        boolean positionRight = rightValue > percentColorThreshold;

        if(positionLeft){
            level = 1;
        } else if(positionRight){
            level = 2;
        } else {
            level = 3;
        }

        return mat;
    }

    public int getLevel(){
        return level;
    }
}
