package geometries;

import java.util.ArrayList;
import java.util.List;
import static primitives.Util.isZero;

import primitives.Color;
import primitives.Coordinate;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The class represents a plane by a point in space and vector The class
 * implements the class Geometry
 * 
 * @author ravitz & kalaman
 *
 */
public class Plain extends Geometry {
	protected Point3D point;
	protected Vector normal;

	// ********** Constructors ***********
	
	/**
	 * Constructor + Normal Calculation
	 * @param emish color 
	 * @param p1 Point3D 1
	 * @param p2 Point3D 2
	 * @param p3 Point3D 3
	 * @param material
	 */
	public Plain(Color emish, Point3D p1, Point3D p2, Point3D p3, Material material) {
		super(emish, material);
		point = new Point3D(p1);
		// calculating the formula (p3-p1)x(p2-p1)
		Vector v1 = p3.subtract(p2);
		Vector v2 = p1.subtract(p2);
		normal = v1.crossProduct(v2).normalize();
	}

	/**
	 * Constructor
	 * @param emish  Color
	 * @param point1 Point3D
	 * @param vector1 normal vector
	 * @param material
	 */
	public Plain(Color emish, Point3D point1, Vector vector1, Material material) {
		super(emish, material);
		point = new Point3D(point1);
		normal = vector1.normalize();
	}

	// ************** Getters *******
	/**
	 * Get point return the point
	 * 
	 * @return point1
	 */
	public Point3D getPoint() {
		return point;
	}

	/**
	 * Get vector return the vector
	 * 
	 * @return normal
	 */
	public Vector getVector() {
		return normal;
	}

	// *************** Administration *****************
	/**
	 * to string return the all details of the class
	 */
	@Override
	public String toString() {
		return "Plane [point=" + point.toString() + ", vector=" + normal.toString() + "]";
	}

	/**
	 * Get normal return the normal of the plane
	 */
	@Override
	public Vector getNormal(Point3D point3d) {
		return normal;
	}

	/**
	 * The function calculates the intersection of the rays with the plain and
	 * returns a list of the intersection points
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> temp = new ArrayList<GeoPoint>();
		// if the ray start on the plane point
		if (point.equals(ray.getPoint()) && !isZero(ray.getVector().dotProduct(normal))) {
			temp.add(new GeoPoint(this, ray.getPoint()));
			return temp;
		}
		// t = 𝑁 ∙ (𝑄0 − 𝑃0 )/ N ∙ v
		double t = (normal.dotProduct(point.subtract(ray.getPoint()))) / (normal.dotProduct(ray.getVector()));
		// check if the ray is parallel to the plane or included in the plane
		if (isZero(ray.getVector().dotProduct(normal))) {
			return temp;
		}
		// check if the ray is in the opposite direction from the plane
		if (t < 0) {
			return EMPTY_LIST;
		}
		// check if the ray is start on the plane
		if (isZero(t)) {
			temp.add(0, new GeoPoint(this, ray.getPoint()));
			return temp;
		}

		// 𝑃0 + 𝑡 ∙ 𝑣
		temp.add(0, new GeoPoint(this, ray.getPoint().addVector(ray.getVector().scaling(t))));

		return temp;
	}

}
