/**
 * The Triangle class is used to model triangles. Like the Square class, 
 * it is a subclass of the Shape class and it inherits all the instance 
 * variables and methods of the Shape class. The Triangle class overrides 
 * the setVertices() method for setting the local coordinates of the 3 
 * vertices of a standard triangle.
 * 
 * @author Sonny Wong
 */
public class Triangle extends Shape{
    /**
     * a method for setting the local coordinates of the 3 vertices of a 
     * standard triangle. Here, a standard triangle is an equilateral 
     * triangle having its center located at (0, 0) and one of its vertex 
     * on the positive x-axis of its local coordinate system.
     * 
     * @param d the distance from the center of the triangle to any of its vertices.
     */
    @Override
    public void setVertices(double d) {
        double xtemp = -d * Math.cos(Math.PI / 3);
        double ytemp = d * Math.sin(Math.PI / 3);
        xlocal = new double[]{d, xtemp,xtemp};
        ylocal = new double[]{0, -1 * ytemp, ytemp};
    }
}