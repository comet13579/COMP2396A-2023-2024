/**
 * The RegularPolygon class is a subclass of the Shape class and is used to model 
 * regular n- sided polygons. Besides the properties it inherited from the Shape class, 
 * the RegularPolygon class also declares a number of private instance variables for 
 * storing the number of sides and the radius of a polygon. It has public getters and 
 * setters for accessing its private instance variables. It also has public methods 
 * for setting the local coordinates of the vertices of a polygon and for checking if 
 * a point (in the canvas coordinate system) is contained by a polygon.
 * 
 * @author Sonny Wong
 *
 */
public final class RegularPolygon extends Shape{

    /**
     * a constructor for building a regular n-sided polygon with a radius of r.
     * 
     * @param n number of sides
     * @param r radius of the polygon
     */
    public RegularPolygon(int n, double r) {
        super();
        if (n < 3) {
            n = 3;
        }
        if (r < 0) {
            r = 0;
        }
        this.numOfSides = n;
        this.radius = r;
        setVertices();
    }

    /**
     * a constructor for building a regular n-sided polygon with a radius of 1.0.
     * @param n number of sides
     */
    public RegularPolygon(int n) {
        this(n, 1.0);
    }

    /**
     * a constructor for building a regular 3-sided polygon with a radius of 1.0.
     */
    public RegularPolygon() {
        this(3,1.0);
    }

    private int numOfSides;
    private double radius;

    /**
     * a method for retrieving the number of sides of the regular polygon.
     * 
     * @return the number of sides of the regular polygon
     */
    public int getNumOfSides() {
        return numOfSides;
    }

    /**
     * a method for retrieving the radius of the regular polygon.
     * 
     * @return the radius of the regular polygon
     */
    public double getRadius() {
        return radius;
    }

    /**
     * a method for setting the number of sides of the regular n-sided polygon. 
     * This method should also reset the local coordinates of the vertices of 
     * the regular n-sided polygon.
     * 
     * @param numOfSides the number of sides of the regular n-sided polygon
     */
    public void setNumOfSides(int numOfSides) {
        if (numOfSides < 3){
            this.numOfSides = 3;
        }
        else{
            this.numOfSides = numOfSides;
        }
        setVertices();
    }

    /**
     * a method for setting the radius of the regular n-sided polygon. 
     * This method should also reset the local coordinates of the vertices 
     * of the regular n-sided polygon.
     * 
     * @param radius the radius of the regular n-sided polygon
     */
    public void setRadius(double radius) {
        if (radius < 0){
            this.radius = 0;
        }
        else{
            this.radius = radius;
        }
        setVertices();
    }

    private void setVertices(){
        double[] tempXlist = new double[numOfSides];
        double[] tempYlist = new double[numOfSides];
        for (int i = 0;i < numOfSides;i++){
            tempXlist[i] = radius * Math.cos((i+1) * 2 * Math.PI / numOfSides);
            tempYlist[i] = radius * Math.sin((i+1) * 2 * Math.PI / numOfSides);
        }
        this.setXLocal(tempXlist);
        this.setYLocal(tempYlist);
    }

    /**
     * a method for determining if a point (x, y) in the canvas coordinate 
     * system is contained by the regular n-sided polygon. A point is 
     * considered to be contained by a polygon if it lies either completely 
     * inside the polygon, or on any of the sides or vertices of the polygon
     * 
     * @param x x coordinate of the point on the canvas coordinate system
     * @param y y coordinate of the point on the canvas coordinate system
     * @return true if the point is contained by the regular n-sided polygon, false otherwise
     */
    public boolean contains(double x, double y){
        double tempX = getXc() - x;
        double tempY = getYc() - y;
        return (Math.sqrt((tempX) * (tempX) + (tempY) * (tempY)) <= radius);
    }
}
