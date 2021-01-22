package geometries;

import static java.lang.StrictMath.sqrt;
import java.util.ArrayList;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The class represents a sphere in space by a point and radius The class
 * extends the class RadialGeometry
 * 
 * @author ravitz & kalaman
 */
public class Sphere extends RadialGeometry {
	private Point3D point;

	// ********** Constructors ***********

	/**
	 * Constructor
	 * @param emish Color
	 * @param radius1 double
	 * @param point1 Point3D
	 * @param material
	 */
	public Sphere(Color emish, double radius1, Point3D point1, Material material) {
		super(emish, radius1, material);
		point = new Point3D(point1);
	}

	// ************** Getters *******
	/**
	 * get point
	 * 
	 * @return the point of the sphere
	 */
	public Point3D getPoint() {
		return point;
	}

	// *************** Administration *****************
	/**
	 * to string return the all details of the class
	 */
	@Override
	public String toString() {
		return "point" + point.toString() + super.toString();
	}

	/**
	 * get normal return the normal of the sphere
	 */
	@Override
	public Vector getNormal(Point3D point3d) {
		return point3d.subtract(point).normalize(); // Normal = normalize(P - O)

	}

	/**
	 * The function calculates all the intersection of the rays with the sphere and
	 * returns a list of the intersection points
	 * @param Ray
	 * @return List<Point3D>
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> temp = new ArrayList<GeoPoint>();
		// if the ray is start on the center of the sphere
		if (ray.getPoint().equals(point)) {
			Point3D p1 = ray.getPoint().addVector(ray.getVector().scaling(radius));
			temp.add(new GeoPoint(this, p1));
			return temp;
		}

		// 𝑢 = 𝑂 − 𝑃0
		Vector u = new Vector(point.subtract(ray.getPoint()));
		// 𝑡𝑚 = 𝑣 ∙ 𝑢
		double tm = u.dotProduct(ray.getVector());
		// 𝑑 = 𝑢 2 − 𝑡𝑚2
		double d = sqrt(u.length2() - tm * tm);
		// in this case there are no intersections
		if (d > radius) {
			return EMPTY_LIST;
		}
		// 𝑡ℎ = sqrt(𝑟2 − 𝑑2)
		double th = sqrt(radius * radius - d * d);

		double t1 = tm - th;
		double t2 = tm + th;

		// if t == 0 there are one intersection: the point of the ray
		if (primitives.Util.isZero(t1) || primitives.Util.isZero(t2)) {/////////////////////////////////////////////////////////////////////////////
			temp.add(new GeoPoint(this, ray.getPoint()));
			if (tm > 0) {
				Point3D p1 = ray.getPoint().addVector(ray.getVector().scaling(t2));
				temp.add(new GeoPoint(this, p1));
			}
			return temp;
		}

		// if t < 0 there are no intersections
		if (t1 >= 0) {
			// 𝑡1 = 𝑡𝑚 - 𝑡ℎ, 𝑃𝑖 = 𝑃0 + 𝑡1 ∙ 𝑣
			Point3D p1 = ray.getPoint().addVector(ray.getVector().scaling(t1));
			temp.add(new GeoPoint(this, p1));
		}
		// if t < 0 there are no intersections
		if (t2 >= 0 && t1 != t2) {
			//// 𝑡2 = 𝑡𝑚 + 𝑡ℎ, 𝑃𝑖 = 𝑃0 + 𝑡2 ∙ 𝑣
			Point3D p2 = ray.getPoint().addVector(ray.getVector().scaling(t2));
			temp.add(new GeoPoint(this, p2));
		}
		return temp;
	}
}
