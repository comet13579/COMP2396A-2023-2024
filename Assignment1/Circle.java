/**
 * The Circle class is used to model circles. Like the Square class and the Triangle class, 
 * it is a subclass of the Shape class and it inherits all the instance variables and methods 
 * of the Shape class. The Circle class overrides the setVertices() method for setting the local 
 * coordinates of the upper left and lower right vertices of an axis-aligned bounding box of 
 * a standard circle, as well as the getX() and getY() methods for retrieving the canvas coordinates 
 * of the upper left and lower right vertices of this bounding box.
 * 
 * @author Sonny Wong
 * 
 */
public class Circle extends Shape{

    /** 
     * a method for setting the local coordinates of the upper left and lower right vertices 
     * of an axis-aligned bounding box of a standard circle. Here, a standard circle is a circle 
     * having its center located at (0, 0) of its local coordinate system.
     * 
     * @param d the radius of the circle
     */
    @Override
    public void setVertices(double d){
        xlocal = new double[]{-d,d};
        ylocal = new double[]{-d ,d};
    }

    /**
     * a method for retrieving the x-coordinates of the upper left and lower right vertices of 
     * an axis-aligned bounding box of the circle in the canvas coordinate system (rounded to 
     * nearest integers).
     * 
     * @return the x-coordinates of the vertices of the shape in the canvas coordinate system
     */
    @Override
    public int[] getX(){
        int[] temparray;
        temparray = new int[2];
        for (int i = 0; i < 2; i++){
            temparray[i] = (int) (xlocal[i] + xc + 0.5);
        }
        return temparray;
    }
    /**
     * a method for retrieving the y-coordinates of the upper left and lower right vertices of 
     * an axis-aligned bounding box of the circle in the canvas coordinate system (rounded to 
     * nearest integers).
     * 
     * @return the y-coordinates of the vertices of the shape in the canvas coordinate system
     */
    @Override
    public int[] getY(){
        int[] temparray;
        temparray = new int[2];
        for (int i = 0; i < 2; i++){
            temparray[i] = (int) (ylocal[i] + yc + 0.5);
        }
        return temparray;
    }
}
