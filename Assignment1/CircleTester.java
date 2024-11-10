import java.awt.Color;

/**
 * Tester Class for Circle.java
 * @author Sonny Wong
 */
public class CircleTester {
    public static void main(String[] args) {
        Circle circle = new Circle();
        
        circle.setVertices(5.0);
        
        int[] xCoords = circle.getX();
        int[] yCoords = circle.getY();
        
        System.out.println("Circle Bounding Box:");
        System.out.println("Upper Left: (" + xCoords[0] + ", " + yCoords[0] + ")");
        System.out.println("Lower Right: (" + xCoords[1] + ", " + yCoords[1] + ")");

        // Test inherited methods
        circle.translate(10, 20);
        System.out.println("\nTesting translate method:");
        System.out.println("New center coordinates: (" + Math.round(circle.xc) + ", " + Math.round(circle.yc) + ")");
        
        circle.color = new Color(255, 0, 0);
        System.out.println("\nTesting color property:");
        System.out.println("Color RGB: " + circle.color.getRed() + "," + circle.color.getGreen() + "," + circle.color.getBlue());
        
        circle.filled = true;
        System.out.println("\nTesting filled property:");
        System.out.println("Is filled: " + circle.filled);
        
        circle.theta = Math.PI/4;
        System.out.println("\nTesting rotation:");
        System.out.println("Rotation angle (radians): " + circle.theta);
    }
}

