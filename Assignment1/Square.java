/**
 * The Square class is used to model squares. It is a subclass of the Shape class 
 * and it inherits all the instance variables and methods of the Shape class. 
 * The Square class overrides the setVertices() method for setting the local 
 * coordinates of the 4 vertices of a standard square.
 * 
 * @author Sonny Wong
 */
public class Square extends Shape{

    /** 
     * a method for setting the local coordinates of the 4 vertices of a standard square.
     *  Here, a standard square has its center located at (0, 0) and its sides being 
     * parallel to the x- and y-axes of its local coordinate system.
     * 
     * @param d half-the-length of a side of the square
     */
    @Override
    public void setVertices(double d) {
        xlocal = new double[]{d, d, -d, -d};
        ylocal = new double[]{d, -d, -d, d};
    }
}
