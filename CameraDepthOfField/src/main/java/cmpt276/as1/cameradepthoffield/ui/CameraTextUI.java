package cmpt276.as1.cameradepthoffield.ui;

import java.util.Scanner;

import cmpt276.as1.cameradepthoffield.model.DepthOfFieldCalculator;
import cmpt276.as1.cameradepthoffield.model.Lens;
import cmpt276.as1.cameradepthoffield.model.LensManager;


//Class to interact with the user

public class CameraTextUI {
    private static final double COC = 0.029;    // "Circle of Confusion" for a "Full Frame" camera
    private LensManager manager;
    private Scanner in = new Scanner(System.in);// Read from keyboard

    // constructor method along with creating new Lens objects
    public CameraTextUI(LensManager manager) {
        this.manager = manager;

        // adding objects to the ArrayList
        manager.addLenses(new Lens("Nikon", 4.0, 200));
        manager.addLenses(new Lens("Canon", 1.8, 50));
        manager.addLenses(new Lens("Sony", 2.4, 50));
        manager.addLenses(new Lens("Panasonic", 2.8, 90));
    }

    // method to display to the user
    public void show() {
        boolean isDone = false;
        while (!isDone) {
            int i = 0;
            System.out.println("Please enter a number to select a lens: ");
            //  for each loop to iterate through ArrayList to
            //  display lenses and its stats to the user
            for (Lens l : manager) {
                System.out.println(i + ". " + l);
                i++;
            }
            System.out.println("(-1 to exit)");

            // user to input a value to either select a lens or exit program
            //Scanner in = new Scanner(System.in);
            int choice = in.nextInt();

            // cases for user inputs
            // first case: if user wishes to exit
            if (choice == -1) {
                isDone = true;
            }
            // second case: if user input is invalid
            else if ( (choice < -1) || (choice >= manager.getLength()) ) {
                System.out.println("Please enter a valid lens index");
            }
            // third case: user enters a valid input, does all the necessary callings
            else {
                // creating a new DepthOfFieldCalculator object
                DepthOfFieldCalculator calculator = new DepthOfFieldCalculator(manager);

                System.out.println("Enter an aperture number [F number]: ");
                double apertureInput = in.nextDouble();

                // checking if aperture input is invalid
                if (apertureInput < manager.getMaxAperture(choice)) {
                    System.out.println("Please input a valid aperture number.");
                }
                else {
                    // user inputs their distance to the subject
                    System.out.println("Enter distance to subject [m] :");
                    double distanceInput = in.nextDouble();

                    // getting results from calling methods
                    String hyperfocalDistance = calculator.getHyperfocalDistance(choice, apertureInput);
                    String nearFocalPoint = calculator.getNearFocalPoint(choice, distanceInput);
                    String farFocalPoint = calculator.getFarFocalPoint(choice, distanceInput);
                    String depthOfField = calculator.getDepthOfField();

                    System.out.println("In focus: " + nearFocalPoint + "m to " + farFocalPoint
                                        + "m [DoF = " + depthOfField + "m]");
                    System.out.println("Hyperfocal point: " + hyperfocalDistance + "m");
                }
            }
        }
    }
}
