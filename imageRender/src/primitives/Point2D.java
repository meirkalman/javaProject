//yitzchak ravitz - 204108609
//meir kalman - 312246606

package primitives;

import static java.lang.StrictMath.sqrt;

/**
 * The class represents a point in 2D by two Coordinate
 * 
 * @author paluch
 *
 */
public class Point2D {

	protected Coordinate x;
	protected Coordinate y;

	// ********** Constructors ***********
	/**
	 * constructor
	 * 
	 * @param x1
	 * @param y1
	 */
	public Point2D(double x1, double y1) {
		this.x = new Coordinate(x1);
		this.y = new Coordinate(y1);
	}

	/**
	 * constructor
	 * 
	 * @param x1
	 * @param y1
	 */
	public Point2D(Coordinate x1, Coordinate y1) {
		x = new Coordinate(x1);
		y = new Coordinate(y1);
	}

	// ************** Getters *******
	/**
	 * get x
	 * 
	 * @return x
	 */
	public Coordinate getX() {
		return x;
	}

	/**
	 * get y
	 * 
	 * @return
	 */
	public Coordinate getY() {
		return y;
	}

	// *************** Administration *****************
	/**
	 * Checks if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point2D))
			return false;
		Point2D p2D = (Point2D) obj;
		return getX().equals(p2D.x) && y.equals(p2D.y);
	}

	/**
	 * return the details
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	// ************** Operations ***************
	/**
	 * Return distance between two points
	 * 
	 * @param p2D
	 * @return
	 */
	public double distance(Point2D p2D) {
		double x1 = x.subtract(p2D.x)._coord * x.subtract(p2D.x)._coord;
		double y1 = y.subtract(p2D.y)._coord * y.subtract(p2D.y)._coord;
		return sqrt(x1 + y1);
	}

}