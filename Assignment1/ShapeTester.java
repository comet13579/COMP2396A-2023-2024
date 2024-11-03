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
        
        testShape.color = new java.awt.Color(255, 0, 0);
        System.out.println("Color set to: " + testShape.color);
        
        testShape.filled = true;
        System.out.println("Filled: " + testShape.filled);
        
        testShape.theta = Math.PI / 4;
        System.out.println("Theta set to: " + testShape.theta);
        
        testShape.xc = 100;
        testShape.yc = 100;
        System.out.println("Center set to: (" + testShape.xc + ", " + testShape.yc + ")");
        
        testShape.translate(50, 50);
        System.out.println("After translation, center is: (" + testShape.xc + ", " + testShape.yc + ")");
        
        double initialTheta = testShape.theta;
        testShape.rotate(Math.PI / 2);
        System.out.println("After rotation, theta changed from " + initialTheta + " to " + testShape.theta);
        
        testShape.setVertices(10);
        
        testShape.xLocal = new double[]{0, 10, 10, 0};
        testShape.yLocal = new double[]{0, 0, 10, 10};
        int[] xCoords = testShape.getX();
        int[] yCoords = testShape.getY();
        for (int i = 0; i < xCoords.length; i++) {
            System.out.println("x = " + xCoords[i]+ " y = " + yCoords[i] + "\n");
        }
    }
}
