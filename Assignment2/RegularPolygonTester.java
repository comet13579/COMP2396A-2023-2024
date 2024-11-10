
/**
 * Tester Class for Shape.java
 * @author Sonny Wong
 */
public class RegularPolygonTester {

    /**
	 * Creates an instance of the RegularPolygon class and test the methods.
	 * 
	 * @param args not being used in this application.
	 */
    public static void main(String[] args) {
        RegularPolygon polygon1 = new RegularPolygon();
        System.out.println("Polygon 1 - Sides: " + polygon1.getNumOfSides() + ", Radius: " + polygon1.getRadius());

        RegularPolygon polygon2 = new RegularPolygon(5);
        System.out.println("Polygon 2 - Sides: " + polygon2.getNumOfSides() + ", Radius: " + polygon2.getRadius());

        RegularPolygon polygon3 = new RegularPolygon(6, 2.5);
        System.out.println("Polygon 3 - Sides: " + polygon3.getNumOfSides() + ", Radius: " + polygon3.getRadius());


        polygon3.setNumOfSides(3);
        polygon3.setRadius(3.0);
        System.out.println("Polygon 3 (after setters) - Sides: " + polygon3.getNumOfSides() + ", Radius: " + polygon3.getRadius());

        System.out.println("Polygon 3 contains (0, 0): " + polygon3.contains(0, 0));
        System.out.println("Polygon 3 contains (3, 3): " + polygon3.contains(3, 3));

        RegularPolygon polygon4 = new RegularPolygon(2, -1);
        System.out.println("Polygon 4 (edge case) - Sides: " + polygon4.getNumOfSides() + ", Radius: " + polygon4.getRadius());

        //test getxLocal and getyLocal
        double[] xLocalTest = polygon3.getXLocal();
        double[] yLocalTest = polygon3.getYLocal();
        System.out.println("Local coordinates:");
        for (int i = 0; i < xLocalTest.length; i++) {
            System.out.println("(" + xLocalTest[i] + ", " + yLocalTest[i] + ")");
        }
    }
}
