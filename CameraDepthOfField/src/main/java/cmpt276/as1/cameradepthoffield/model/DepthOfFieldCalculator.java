package cmpt276.as1.cameradepthoffield.model;

/*
Class to do calculations for desired outputs
 */

import java.text.DecimalFormat;

import cmpt276.as1.cameradepthoffield.model.LensManager;

public class DepthOfFieldCalculator {
    private LensManager manager;

    // data needed for some calculations
    private static final double COC = 0.029;
    private double hyperfocalDistance; // in mm
    private double nearFocalPoint; // in mm
    private double farFocalPoint; // in mm

    // constructor
    public DepthOfFieldCalculator(LensManager manager) {
        this.manager = manager;
    }

    // taken off course website, method to round answer to 2 decimal places
    public String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    // method to compute hyper focal distance
    public String getHyperfocalDistance(int index, double apertureInput) {
        int focalLength =  manager.getFocalLength(index); // getting focal length @ index
        int numerator = focalLength * focalLength;
        double denominator = apertureInput * COC;

        double answerInMM = numerator / denominator;
        hyperfocalDistance = answerInMM;

        // conversion from mm to m
        double answerInMeters = answerInMM * 0.001;

        // returning answer that's rounded to 2 decimal places
        return formatM(answerInMeters);
    }

    // method to calculate the near focal point
    public String getNearFocalPoint(int index, double distanceInput) {
        int focalLength = manager.getFocalLength(index); // getting focal length @ index
        double distanceInputMM = distanceInput * 1000; // convert distanceInput to mm

        double numerator = hyperfocalDistance * distanceInputMM;
        double denominator = distanceInputMM - focalLength;
        denominator = hyperfocalDistance + denominator;

        double answerInMM = numerator / denominator;
        nearFocalPoint = answerInMM;

        // conversion from mm to m
        double answerInMeters = answerInMM * 0.001;

        // return answer that's rounded to 2 decimal places
        return formatM(answerInMeters);
    }

    // method to calculate far focal point
    public String getFarFocalPoint(int index, double distanceInput) {
        int focalLength = manager.getFocalLength(index); // getting focal length @ index
        double distanceInputMM = distanceInput * 1000; // convert distanceInput to mm

        // return positive infinity if input > hyper focal distance
        if (distanceInputMM > hyperfocalDistance) {
            farFocalPoint = Double.POSITIVE_INFINITY;
            return formatM(farFocalPoint);
        }
        else {
            double numerator = hyperfocalDistance * distanceInputMM;
            double denominator = distanceInputMM - focalLength;
            denominator = hyperfocalDistance - denominator;

            double answerInMM = numerator / denominator;
            farFocalPoint = answerInMM;

            // convert from mm to m
            double answerInMeters = answerInMM * 0.001;

            // return answer rounded to 2 decimal places
            return formatM(answerInMeters);
        }
    }

    // method for calculating the depth of field
    public String getDepthOfField() {
        double depthOfFieldMM = farFocalPoint - nearFocalPoint;

        // convert from mm to m
        double depthOfFieldMeters = depthOfFieldMM * 0.001;

        // return answer rounded to 2 decimal places
        return formatM(depthOfFieldMeters);
    }

}
