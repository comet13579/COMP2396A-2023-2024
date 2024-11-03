import java.awt.Color;

/**
 * Tester Class for Triangle.java
 * @author Sonny Wong
 */
public class TriangleTester {
    /**
     * Creates an instance of the Triangle class and test the methods.
     * 
     * @param args not being used in this application.
     */
    public static void main(String[] args) {
        Triangle triangle = new Triangle();
        triangle.setVertices(5);

        System.out.println("Triangle vertices:");
        for (int i = 0; i < triangle.xLocal.length; i++) {
            System.out.println("x = " + triangle.xLocal[i]+ " y = " + triangle.yLocal[i]);
        }

        triangle.translate(3, 4);
        System.out.println("\nAfter translation by (3,4):");
        System.out.println("Center coordinates: (" + triangle.xc + ", " + triangle.yc + ")");

        triangle.theta = Math.PI/4;
        System.out.println("\nAfter rotation by 45 degrees:");
        System.out.println("Theta = " + triangle.theta + " radians");

        triangle.color = Color.BLUE;
        triangle.filled = true;
        System.out.println("\nTriangle properties:");
        System.out.println("Color: " + triangle.color);
        System.out.println("Filled: " + triangle.filled);

        System.out.println("\nCanvas coordinates after all transformations:");
        int[] xPoints = triangle.getX();
        int[] yPoints = triangle.getY();
        for (int i = 0; i < xPoints.length; i++) {
            System.out.println("Vertex " + (i+1) + ": (" + xPoints[i] + ", " + yPoints[i] + ")");
        }
    }
}
