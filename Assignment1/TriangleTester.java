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

        System.out.println("Square vertices:");
        for (int i = 0; i < triangle.xlocal.length; i++) {
            System.out.println("x = " + triangle.xlocal[i]+ " y = " + triangle.ylocal[i] + "\n");
        }
    }
}