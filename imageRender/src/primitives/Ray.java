//yitzchak ravitz - 204108609
//meir kalman - 312246606

package primitives;

/**
 * The class represents a ray in space by a point and vector
 * 
 * @author paluch
 *
 */
public class Ray {
	protected Point3D point;
	protected Vector vector;

	/********** Constructors ***********/
	/**
	 * Constructor
	 * 
	 * @param Point3D point1
	 * @param Vector  vector1
	 */
	public Ray(Point3D point1, Vector vector1) {
		point = new Point3D(point1);
		vector = vector1.normalize();
	}

	/************** Getters/Setters *******/
	/**
	 * get point return the point of the ray
	 * 
	 * @return Point3D
	 */
	public Point3D getPoint() {
		return new Point3D(point);
	}

	/**
	 * set the point
	 * 
	 * @param point
	 */
	public void setPoint(Point3D point) {
		this.point = new Point3D(point);
	}

	
	/**
	 * get vector return teh vector of the ray
	 * 
	 * @return
	 */
	public Vector getVector() {
		return new Vector(vector);
	}

	/**
	 * set the vector
	 * 
	 * @param vector1
	 */
	public void setVector(Vector vector1) {
		this.vector = new Vector(vector1);
	}

	// *************** Administration *****************
	/**
	 * to string return the details of the class
	 */
	@Override
	public String toString() {
		return "point " + point.toString() + " vector " + vector.toString();
	}

	/**
	 * Checks if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this == null)
			return false;
		if (!(obj instanceof Ray))
			return false;
		Ray ray = (Ray) obj;
		if (ray.getPoint().equals(point) && ray.getVector().equals(vector)) {
			return true;
		}
		return false;
	}

}
