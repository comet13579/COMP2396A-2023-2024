
/**
 * Tester Class for Square.java
 * @author Sonny Wong
 */
public class SquareTester {
    /**
	 * Creates an instance of the Square class and test the methods.
	 * 
	 * @param args not being used in this application.
	 */
    public static void main(String[] args) {
        Square square = new Square();
        square.setVertices(5);

        System.out.println("Square vertices:");
        for (int i = 0; i < square.xLocal.length; i++) {
            System.out.println("x = " + square.xLocal[i]+ " y = " + square.yLocal[i] + "\n");
        }
        
        square.color = new java.awt.Color(0, 255, 0);  // Set color to green
        System.out.println("Color set to: " + square.color);
        
        square.filled = true;
        System.out.println("Filled: " + square.filled);
        
        square.xc = 50;
        square.yc = 50;
        System.out.println("Initial center: (" + square.xc + ", " + square.yc + ")");
        
        square.translate(25, 25);
        System.out.println("After translation - Center: (" + square.xc + ", " + square.yc + ")");
        
        square.theta = 0;
        System.out.println("Initial theta: " + square.theta);
        square.rotate(Math.PI / 4);  // Rotate 45 degrees
        System.out.println("After rotation - Theta: " + square.theta);
        
        int[] canvasX = square.getX();
        int[] canvasY = square.getY();
        System.out.println("\nCanvas coordinates after transformations:");
        for (int i = 0; i < canvasX.length; i++) {
            System.out.println("Point " + (i+1) + ": (" + canvasX[i] + ", " + canvasY[i] + ")");
        }

    }
}

