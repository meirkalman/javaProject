package geometries;

import static primitives.Util.isZero;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The class represents a triangle in space by three points. The class expands
 * the class Plane
 * 
 * @author ravitz & kalaman
 *
 */
public class Triangle extends Plain {

	private Point3D point2;
	private Point3D point3;

	// ********** Constructor ***********
	
	/**
	 * Constructor
	 * @param emish Color
	 * @param a Point3D 1
	 * @param b Point3D 2
	 * @param c Point3D 3
	 * @param material
	 */
	public Triangle( Color emish, Point3D a, Point3D b, Point3D c ,Material material) {
		super(emish, a, b, c, material);
		point2 = new Point3D(b);
		point3 = new Point3D(c);
	}

	// ************** Getters *******
	/**
	 * get point return p1
	 * 
	 * @return Point3D - point1
	 */
	public Point3D getP1() {
		return point;
	}

	/**
	 * get point return p2
	 * 
	 * @return Point3D - point2
	 */
	public Point3D getP2() {
		return point2;
	}

	/**
	 * get point return p3
	 * 
	 * @return Point3D - point3
	 */
	public Point3D getP3() {
		return point3;
	}

	// *************** Administration *****************
	/**
	 * return the details of the class
	 */
	@Override
	public String toString() {
		return "Triangle [p1=" + point.toString() + ", p2=" + point2.toString() + ", p3=" + point3.toString() + "]";
	}

	/**
	 * The function calculates the intersection of the rays with the triangle and
	 * returns a list of the intersection point
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// First, intersect ray with the plane.
		List<GeoPoint> temp = super.findIntersections(ray);
		if (temp.size() == 0) {
			return EMPTY_LIST;
		}
		// if the ray start on the corner points
		if (getP1().equals(temp.get(0).point) || point2.equals(temp.get(0).point) || point3.equals(temp.get(0).point)) {
			return EMPTY_LIST;
		}
		// 𝑣1 = 𝑃1 − 𝑃0
		Vector v1 = getP1().subtract(ray.getPoint());
		// 𝑣2 = 𝑃2 − 𝑃0
		Vector v2 = point2.subtract(ray.getPoint());
		// 𝑣3 = 𝑃3 − 𝑃0
		Vector v3 = point3.subtract(ray.getPoint());
		try {

			// 𝑁1 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 𝑣1 × 𝑣2
			Vector n1 = v1.crossProduct(v2).normalize();
			// 𝑁2 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 𝑣2 × 𝑣3
			Vector n2 = v2.crossProduct(v3).normalize();
			// 𝑁3 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 𝑣3 × 𝑣1
			Vector n3 = v3.crossProduct(v1).normalize();

			// 𝑷 − 𝑷𝟎
			Vector pResult = temp.get(0).point.subtract(ray.getPoint());
			// if one or more are 0.0 – no intersection
			if (isZero(pResult.dotProduct(n1)) || (isZero(pResult.dotProduct(n2)))
					|| (isZero(pResult.dotProduct(n3)))) {
				return EMPTY_LIST;
			}
			// if all 𝑷 − 𝑷𝟎 ∙ 𝑵𝒊 have the same sign (+)
			if (pResult.dotProduct(n1) > 0 && pResult.dotProduct(n2) > 0 && pResult.dotProduct(n3) > 0) {
				return temp;
			}
			// if all 𝑷 − 𝑷𝟎 ∙ 𝑵𝒊 have the same sign (-)
			if (pResult.dotProduct(n1) < 0 && pResult.dotProduct(n2) < 0 && pResult.dotProduct(n3) < 0) {
				return temp;
			}
			return EMPTY_LIST;

		} catch (IllegalArgumentException e) {
			// If the zero vector is created, it means that the ray passes through the edge
			// of the triangle
			return EMPTY_LIST;
		}
	}
}
