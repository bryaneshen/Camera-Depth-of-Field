package cmpt276.as1.cameradepthoffield.model;

/*
This class stores information about the lenses.
Has info on the make, maximum aperture, and the focal length.
 */
public class Lens {
    private String cameraName;
    private double maxAperture;
    private int focalLength;

    // constructor
    public Lens(String cameraName, double maxAperture, int focalLength) {
        this.cameraName = cameraName;
        this.maxAperture = maxAperture;
        this.focalLength = focalLength;
    }

    // method to get the make of the camera
    public String getCameraName() {
        return cameraName;
    }

    // method to get the max aperture in object
    public double getMaxAperture() {
        return maxAperture;
    }

    // method to get focal length in object
    public int getFocalLength() {
        return focalLength;
    }

    @Override
    public String toString() {
        return cameraName + " F" + maxAperture + " " + focalLength + "mm";
    }
}
