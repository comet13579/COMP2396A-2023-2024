import java.awt.Color;

/**
 * Tester Class for Shape.java
 * @author Sonny Wong
 */
public class ShapeTester {

    /**
	 * Creates an instance of the Shape class and test the methods.
	 * 
	 * @param args not being used in this application.
	 */
    public static void main(String[] args) {
        Shape testShape = new Shape();

        testShape.setColor(Color.RED);
        testShape.setFilled(true);
        testShape.setTheta(Math.PI / 4);
        testShape.setXc(100);
        testShape.setYc(100);

        double[] xLocal = {-50, 50, 50, -50};
        double[] yLocal = {-50, -50, 50, 50};
        testShape.setXLocal(xLocal);
        testShape.setYLocal(yLocal);

        System.out.println("Color: " + testShape.getColor());
        System.out.println("Filled: " + testShape.getFilled());
        System.out.println("Theta: " + testShape.getTheta());
        System.out.println("Center X: " + testShape.getXc());
        System.out.println("Center Y: " + testShape.getYc());

        testShape.translate(50, 25);
        System.out.println("After translation - Center X: " + testShape.getXc() + ", Center Y: " + testShape.getYc());

        testShape.rotate(Math.PI / 2);
        System.out.println("After rotation - Theta: " + testShape.getTheta());

        int[] canvasX = testShape.getX();
        int[] canvasY = testShape.getY();
        System.out.println("Canvas coordinates:");
        for (int i = 0; i < canvasX.length; i++) {
            System.out.println("(" + canvasX[i] + ", " + canvasY[i] + ")");
        }

        //test getXLocal and getYLocal
        double[] xLocalTest = testShape.getXLocal();
        double[] yLocalTest = testShape.getYLocal();
        System.out.println("Local coordinates:");
        for (int i = 0; i < xLocalTest.length; i++) {

            System.out.println("(" + xLocalTest[i] + ", " + yLocalTest[i] + ")");
        }
    }
}

