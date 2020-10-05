
package cmpt276.as1.cameradepthoffield.test;

import org.junit.jupiter.api.Test;

import cmpt276.as1.cameradepthoffield.model.Lens;
import cmpt276.as1.cameradepthoffield.model.LensManager;
import cmpt276.as1.cameradepthoffield.model.DepthOfFieldCalculator;

import static org.junit.jupiter.api.Assertions.*;


//JUNIT 5 test class for DepthOfFieldCalculator class

class DepthOfFieldCalculatorTest {
    @Test
    void getHyperfocalDistance() {
        LensManager manager = LensManager.getInstance();
        manager.addLenses(new Lens("Nikon", 4.8, 200));
        manager.addLenses(new Lens("Canon", 1.8, 50));
        manager.addLenses(new Lens("Sony", 2.4, 50));
        manager.addLenses(new Lens("Panasonic", 2.8, 90));

        DepthOfFieldCalculator calculator = new DepthOfFieldCalculator(manager);

        // TEST 1
        assertEquals("344.83", calculator.getHyperfocalDistance(0, 4));

        // TEST 2
        assertEquals("10.78", calculator.getHyperfocalDistance(1, 8));

        // TEST 3
        assertEquals("99.75", calculator.getHyperfocalDistance(3, 2.8));
    }

    @Test
    void getNearFocalPoint() {
        LensManager manager = LensManager.getInstance();
        manager.addLenses(new Lens("Nikon", 4.8, 200));
        manager.addLenses(new Lens("Canon", 1.8, 50));
        manager.addLenses(new Lens("Sony", 2.4, 50));
        manager.addLenses(new Lens("Panasonic", 2.8, 90));

        DepthOfFieldCalculator calculator = new DepthOfFieldCalculator(manager);

        // TEST 1:
        // getting value of hyperfocal distance to use to calculate near focal point
        calculator.getHyperfocalDistance(1, 1.8);
        assertEquals("1.08", calculator.getNearFocalPoint(1, 1.1));

        // TEST 2:
        // getting value of hyperfocal distance to use to calculate near focal point
        calculator.getHyperfocalDistance(2, 2.5);
        assertEquals("4.37", calculator.getNearFocalPoint(2, 5));

        // TEST 3:
        // getting value of hyperfocal distance to use to calculate near focal point
        calculator.getHyperfocalDistance(3, 2.8);
        assertEquals("1.96", calculator.getNearFocalPoint(3, 2));
    }

    @Test
    void getFarFocalPoint() {
        // have one test case to see when distanceInput > hyperfocalDistance
        LensManager manager = LensManager.getInstance();

        manager.addLenses(new Lens("Nikon", 4.8, 200));
        manager.addLenses(new Lens("Canon", 1.8, 50));
        manager.addLenses(new Lens("Sony", 2.4, 50));
        manager.addLenses(new Lens("Panasonic", 2.8, 90));

        DepthOfFieldCalculator calculator = new DepthOfFieldCalculator(manager);

        // TEST 1 (when distance input > hyperfocal distance, should result in infinity)
        // getting value of hyperfocal distance to use to calculate far focal point
        calculator.getHyperfocalDistance(1, 11);
        assertEquals(calculator.formatM(Double.POSITIVE_INFINITY), calculator.getFarFocalPoint(1, 15));

        // TEST 2
        // getting value of hyperfocal distance to use to calculate far focal point
        calculator.getHyperfocalDistance(2, 2.4);
        assertEquals("3.27", calculator.getFarFocalPoint(2, 3));

        // TEST 3
        // getting value of hyperfocal distance to use to calculate far focal point
        calculator.getHyperfocalDistance(3, 2.81);
        assertEquals("5.26", calculator.getFarFocalPoint(3, 5));
    }

    @Test
    void getDepthOfField() {
        LensManager manager = LensManager.getInstance();

        manager.addLenses(new Lens("Nikon", 4.8, 200));
        manager.addLenses(new Lens("Canon", 1.8, 50));
        manager.addLenses(new Lens("Sony", 2.4, 50));
        manager.addLenses(new Lens("Panasonic", 2.8, 90));

        DepthOfFieldCalculator calculator = new DepthOfFieldCalculator(manager);

        // TEST 1
        // getting values of hyperfocal distace, near focal and far focal values
        calculator.getHyperfocalDistance(1, 1.8);
        calculator.getNearFocalPoint(1, 1.1);
        calculator.getFarFocalPoint(1, 1.1);
        assertEquals("0.05", calculator.getDepthOfField());

        // TEST 2
        // getting values of hyperfocal distace, near focal and far focal values
        calculator.getHyperfocalDistance(2, 2.5);
        calculator.getNearFocalPoint(2, 5.3);
        calculator.getFarFocalPoint(2, 5.3);
        assertEquals("1.65", calculator.getDepthOfField());

        // TEST 3
        // getting values of hyperfocal distace, near focal and far focal values
        calculator.getHyperfocalDistance(0, 4.5);
        calculator.getNearFocalPoint(0, 4.98);
        calculator.getFarFocalPoint(0, 4.98);
        assertEquals("0.16", calculator.getDepthOfField());
    }
}