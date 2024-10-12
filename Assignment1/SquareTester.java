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
        for (int i = 0; i < square.xlocal.length; i++) {
            System.out.println("x = " + square.xlocal[i]+ " y = " + square.ylocal[i] + "\n");
        }
    }
}

