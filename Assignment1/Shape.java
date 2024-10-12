import java.awt.Color;


/**
 * 
 * The Shape class is used to model general shapes. 
 * It has instance variables for storing color, fill-type, orientation, canvas coordinates of the center, 
 * and local coordinates of the vertices of a shape. 
 * It has methods for getting the canvas coordinates of the vertices of a shape.
 * Below is a detailed description for the Shape class.
 * 
 * @author Sonny Wong
 *
 */
public class Shape {
    /** a Color object specifying the color of the shape. */
    public Color color; 

    /** a boolean value specifying whether the shape is filled or not filled. */
    public boolean filled;

    /** a double value specifying the orientation (in radians) of the shape (with respect to its center) in the canvas coordinate system*/
    public double theta;

    /** a double value specifying the x-coordinate of the center of the shape in the canvas coordinate system.*/
    public double xc;
    
    /** a double value specifying the y-coordinate of the center of the shape in the canvas coordinate system. */
    public double yc; 

    /** xLocal – an array of double values specifying the x-coordinates of the vertices (in counter-clockwise order) of the shape in its local coordinate system. */
    public double[] xlocal; 

    /** yLocal – an array of double values specifying the y-coordinates of the vertices (in counter- clockwise order) of the shape in its local coordinate system. */
    public double[] ylocal;

    private int canvasCoordX(double x,double y){
        double tempX;
        tempX = x * Math.cos(theta) - y * Math.sin(theta) + xc;
        return (int) (tempX + 0.5);
    }

    private int canvasCoordY(double x,double y){
        double tempY;
        tempY = x * Math.sin(theta) + y * Math.cos(theta) + yc;
        return (int) (tempY + 0.5);
    }

    /**
	 * a method for setting the local coordinates of the vertices of a shape. 
     * This is a dummy method and is supposed to be overridden in the subclasses.
     * @param d the side length of the shape
	 */
    public void setVertices(double d){
    }

    /**
     * a method for translating the center of the shape by dx and dy, 
     * respectively, along the x and y directions of the canvas coordinate system 
     * (i.e., dx and dy should be added to xc and yc respectively).
     * @param dx offset cordinate of x
     * @param dy offset cordinate of x
     */
    public void translate(double dx,double dy){
        xc += dx;
        yc += dy;
    }

    /**
     * a method for rotating the shape about its center by an angle of dt (in radians) 
     * (i.e., dt should be added to theta).
     * @param dt angle of rotation
     */
    public void rotate(double dt){
        theta += dt;
    }

    /**
     * a method for retrieving the x-coordinates of the vertices (in counter- clockwise order) 
     * of the shape in the canvas coordinate system (rounded to nearest integers).
     * @return the x-coordinates of the vertices of the shape in the canvas coordinate system
     */
    public int[] getX(){
        int[] temparray;
        temparray = new int[xlocal.length];
        for (int i = 0; i < xlocal.length; i++){
            temparray[i] = canvasCoordX(xlocal[i], ylocal[i]);
        }
        return temparray;
    }

    /**
     * a method for retrieving the y-coordinates of the vertices (in counter- clockwise order) 
     * of the shape in the canvas coordinate system (rounded to nearest integers).
     * @return the y-coordinates of the vertices of the shape in the canvas coordinate system
     */
    public int[] getY(){
        int[] temparray;
        temparray = new int[ylocal.length];
        for (int i = 0; i < ylocal.length; i++){
            temparray[i] = canvasCoordY(xlocal[i], ylocal[i]);
        }
        return temparray;
    }
}