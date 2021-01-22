//yitzchak ravitz - 204108609
//meir kalman - 312246606

package primitives;

import static java.lang.StrictMath.sqrt;

/**
 * The class represents a point in 3D by 3 Coordinates The class extends the
 * class Point2D
 * 
 * @author ravitz & kalaman
 *
 */
public class Point3D extends Point2D {

	final static Point3D ZERO = new Point3D(0, 0, 0); // Check whether the point is equal to zero

	private Coordinate z;

	// ********** Constructors ***********
	/**
	 * Constructor
	 * 
	 * @param double x1
	 * @param double y1
	 * @param double z1
	 */
	public Point3D(double x1, double y1, double z1) {
		super(x1, y1);
		z = new Coordinate(z1);
	}

	/**
	 * Constructor
	 * 
	 * @param Coordinate x1
	 * @param Coordinate y1
	 * @param Coordinate z1
	 */
	public Point3D(Coordinate x1, Coordinate y1, Coordinate z1) {
		super(x1, y1);
		setZ(z1);
	}

	/**
	 * Constructor
	 * 
	 * @param Point3D point3d
	 */
	public Point3D(Point3D point3d) {
		super(point3d.x, point3d.y);
		setZ(point3d.z);
	}

	// ************** Getters/Setters *******
	/**
	 * getZ
	 * 
	 * @return the z point
	 */
	public Coordinate getZ() {
		return z;
	}

	/**
	 * set z point
	 * 
	 * @param Coordinate z1
	 */
	private void setZ(Coordinate z1) {
		z = new Coordinate(z1);
	}

	// *************** Administration *****************
	/**
	 * Checks if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this == null)
			return false;
		if (!(obj instanceof Point3D))
			return false;
		Point3D point3D = (Point3D) obj;
		return super.equals(point3D) && z.equals(point3D.z);
	}

	/**
	 * @return the details of the class
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	// ************** Operations ***************
	/**
	 * calculates the distance between two points
	 * 
	 * @param Point3D p3D
	 * @return double
	 */
	public double distance(Point3D p3D) {
		double x1 = x.subtract(p3D.x)._coord * x.subtract(p3D.x)._coord;
		double y1 = y.subtract(p3D.y)._coord * y.subtract(p3D.y)._coord;
		double z1 = z.subtract(p3D.z)._coord * z.subtract(p3D.z)._coord;
		return sqrt(x1 + y1 + z1);
	}

	/**
	 * Return distance between two points
	 * 
	 * @param Point3D p3D
	 * @return double
	 */
	public double distance2(Point3D p3D) {
		double x1 = x.subtract(p3D.x)._coord * x.subtract(p3D.x)._coord;
		double y1 = y.subtract(p3D.y)._coord * y.subtract(p3D.y)._coord;
		double z1 = z.subtract(p3D.z)._coord * z.subtract(p3D.z)._coord;
		return x1 + y1 + z1;
	}

	/**
	 * Makes subtraction between points
	 * 
	 * @param Point3D point3d
	 * @return new vector
	 */
	public Vector subtract(Point3D point3d) {
		return new Vector(x.subtract(point3d.x)._coord, y.subtract(point3d.y)._coord, z.subtract(point3d.z)._coord);
	}

	/**
	 * Adds one Vector to the Point
	 * 
	 * @param Vector point3d
	 * @return new Point3D
	 */
	public Point3D addVector(Vector vec) {
		return new Point3D(x.add(vec.getHead().x)._coord, y.add(vec.getHead().y)._coord, z.add(vec.getHead().z)._coord);
	}

}
