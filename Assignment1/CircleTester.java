/**
 * Tester Class for Circle.java
 * @author Sonny Wong
 */
public class CircleTester {
	/**
	 * Creates an instance of the Circle class and test the methods.
	 * 
	 * @param args not being used in this application.
	 */
    public static void main(String[] args) {
        Circle circle = new Circle();
        
        circle.setVertices(5.0);
        
        int[] xCoords = circle.getX();
        int[] yCoords = circle.getY();
        
        System.out.println("Circle Bounding Box:");
        System.out.println("Upper Left: (" + xCoords[0] + ", " + yCoords[0] + ")");
        System.out.println("Lower Right: (" + xCoords[1] + ", " + yCoords[1] + ")");
    }
}
