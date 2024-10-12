import java.awt.*;

/**
 * The Shape class is used to model general shapes. It has private instance variables 
 * for storing color, fill-type, orientation, canvas coordinates of the center, and the 
 * local coordinates of the vertices of a shape. It has public getters and setters for 
 * accessing its private instance variables. It also has public methods for translating 
 * and rotating a shape, and for getting the canvas coordinates of the vertices of a shape.
 * 
 * @author Sonny Wong
 *
 */
public class Shape {
    private Color color;
    private boolean filled;
    private double theta;
    private double xc;
    private double yc;
    private double[] xlocal;
    private double[] ylocal;

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
     * a method for retrieving the color of the shape.
     * 
     * @return the color of the shape
     */
    public Color getColor() {
        return color;
    }

    /**
     * a method for retrieving the fill-type of the shape.
     * 
     * @return the fill-type of the shape
     */
    public boolean getFilled() {
        return filled;
    }

    /**
     * a method for retrieving the orientation (in radians) of the shape 
     * (with respect to its center) in the canvas coordinate system.
     * 
     * @return the orientation (in radians) of the shape
     */
    public double getTheta() {
        return theta;
    }

    /**
     * a method for retrieving the x-coordinate of the center of the shape 
     * in the canvas coordinate system.
     * 
     * @return x-coordinate of the center of the shape 
     */
    public double getXc() {
        return xc;
    }

    /**
     * a method for retrieving the y-coordinate of the center of the shape in the canvas coordinate system.
     * 
     * @return y-coordinate of the center of the shape
     */
    public double getYc() {
        return yc;
    }

    /**
     * a method for retrieving the x-coordinates of the vertices (in counter-clockwise order) 
     * of the shape in its local coordinate system.
     *
     * @return an array of the x-coordinates of the vertices
     */
    public double[] getXLocal() {
        return xlocal;
    }

    /**
     * a method for retrieving the y-coordinates of the vertices (in counter-clockwise order) 
     * of the shape in its local coordinate system.
     * 
     * @return an array of the y-coordinates of the vertices
     */
    public double[] getYLocal() {
        return ylocal;
    }

    /**
     * a method for setting the color of the shape.
     * 
     * @param color the color of the shape
     */
    void setColor(Color color) {
        this.color = color;
    }

    /**
     * a method for setting the fill-type of the shape.
     * 
     * @param filled the fill-type of the shape
     */
    void setFilled(boolean filled) {
        this.filled = filled;
    }

    /**
     * a method for setting the orientation of the shape.
     * 
     * @param theta the orientation of the shape
     */
    void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     * a method for setting the x-coordinate of the center of the shape in the canvas coordinate system.
     * 
     * @param xc the x-coordinate of the center of the shape in the canvas coordinate system
     */
    void setXc(double xc) {
        this.xc = xc;
    }

    /**
     * a method for setting the y-coordinate of the center of the shape in the canvas coordinate system.
     * 
     * @param yc the y-coordinate of the center of the shape in the canvas coordinate system
     */
    void setYc(double yc) {
        this.yc = yc;
    }

    /**
     * a method for setting the x-coordinates of the vertices (in counter-clockwise order) 
     * of the shape in its local coordinate system.
     * 
     * @param xlocal an array of the x-coordinates of the vertices (in counter-clockwise order) 
     */
    void setXLocal(double[] xlocal) {
        this.xlocal = xlocal;
    }

    /**
     * a method for setting the y-coordinates of the vertices (in counter-clockwise order) 
     * of the shape in its local coordinate system.
     * 
     * @param ylocal an array of the y-coordinates of the vertices (in counter-clockwise order)
     */
    void setYLocal(double[] ylocal) {
        this.ylocal = ylocal;
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
