//yitzchak ravitz - 204108609
//meir kalman - 312246606

package primitives;

import static java.lang.StrictMath.sqrt;

/**
 * The class represents a vector by point3d
 * 
 * @author ravitz & kalaman
 */
public class Vector {
	private Point3D head;

	// ********* Constructors ***********

	/**
	 * Constructor
	 * 
	 * @param Point3D head1
	 */
	public Vector(Point3D head1) {
		this.setAndCheck(head1);
	}

	/**
	 * Constructor
	 * 
	 * @param double x
	 * @param double y
	 * @param double z
	 */
	public Vector(double x, double y, double z) {
		setAndCheck(new Point3D(x, y, z));
	}

	/**
	 * Constructor
	 * 
	 * @param Coordinate x
	 * @param Coordinate y
	 * @param Coordinate z
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		setAndCheck(new Point3D(x, y, z));
	}

	/**
	 * Constructor
	 * 
	 * @param Vector h
	 */
	public Vector(Vector h) {
		this.setAndCheck(h.head);
	}

	// ************** Getters/Setters *******
	/**
	 * get head
	 * 
	 * @return the head of the vector type Point3D
	 */
	public Point3D getHead() {
		return head;
	}

	/**
	 * set the head of the vector and checks if it is equal to the zero vector
	 * 
	 * @param Point3D h1
	 * @throws IllegalArgumentException in case of zero vector
	 */
	private void setAndCheck(Point3D h1) {
		if (Point3D.ZERO.equals(h1))
			throw new IllegalArgumentException("Creating zero vector");
		head = h1;
	}

	// *************** Administration *****************
	/**
	 * Checks if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vector))
			return false;
		Vector vector = (Vector) obj;
		return head.equals(vector.head);
	}

	/**
	 * to string
	 * 
	 * @return the details of the class
	 */
	@Override
	public String toString() {
		return head.toString();
	}

	// ************** Operations ***************
	/**
	 * Makes subtraction between vectors
	 * 
	 * @param vec
	 * @return new Vector
	 */
	public Vector subtract(Vector vec) {
		return new Vector(head.subtract(vec.head));
	}

	/**
	 * Adds one vector to the other
	 * 
	 * @param Vector vec
	 * @return new Vector
	 */
	public Vector add(Vector vec) {
		return new Vector(head.addVector(vec));
	}

	/**
	 * Makes a scalar product between two vectors
	 * 
	 * @param vec
	 * @return double
	 */
	public double dotProduct(Vector vec) {
		double x1 = head.x._coord * vec.head.x._coord;
		double y1 = head.y._coord * vec.head.y._coord;
		double z1 = head.getZ()._coord * vec.head.getZ()._coord;
		return (x1 + y1 + z1);
	}

	/**
	 * Makes a vector product between two vectors
	 * 
	 * @param vec
	 * @return new Vector
	 */
	public Vector crossProduct(Vector vec) {
		double x1 = head.y._coord * vec.head.getZ()._coord - head.getZ()._coord * vec.head.y._coord;
		double y1 = head.getZ()._coord * vec.head.x._coord - head.x._coord * vec.head.getZ()._coord;
		double z1 = head.x._coord * vec.head.y._coord - head.y._coord * vec.head.x._coord;
		return new Vector(x1, y1, z1);
	}

	/**
	 * Calculates the squared length of the vector
	 * 
	 * @return double the squared length
	 */
	public double length2() {
		double x1 = head.x._coord * head.x._coord;
		double y1 = head.y._coord * head.y._coord;
		double z1 = head.getZ()._coord * head.getZ()._coord;
		return x1 + y1 + z1;
	}

	/**
	 * Scaling The function calculates multiplication of a vector in number
	 * 
	 * @return new vector
	 */
	public Vector scaling(double a) {
		return new Vector(head.x._coord * a, head.y._coord * a, head.getZ()._coord * a);
	}

	/**
	 * Calculates the length of the vector
	 * 
	 * @return the length
	 */
	public double length() {
		double x1 = head.x._coord * head.x._coord;
		double y1 = head.y._coord * head.y._coord;
		double z1 = head.getZ()._coord * head.getZ()._coord;
		return sqrt(x1 + y1 + z1);
	}

	/**
	 * Normalizes the vector
	 * 
	 * @return new vector which is unit vector in the same direction
	 */
	public Vector normalize() {
		double scaling = 1 / length();
		double x1 = head.x._coord * scaling;
		double y1 = head.y._coord * scaling;
		double z1 = head.getZ()._coord * scaling;
		return new Vector(x1, y1, z1);
	}

}
