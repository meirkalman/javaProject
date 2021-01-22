package geometries;

import java.util.List;

import primitives.*;
/**
 * The class represents a rectangle in space by 4 points. The class expands
 * the class Plane
 * 
 * @author ravitz & kalaman
 */
public class Rectangle extends Plain {
	private Point3D point2;
	private Point3D point3;
	private Point3D point4;

	/**
	 * Constructor
	 * 
	 * @param A        Point a
	 * @param B        Point b
	 * @param C        Point c
	 * @param color    the emision color
	 * @param material the material
	 */
	public Rectangle( Color color,Point3D a, Point3D b, Point3D c,Point3D d, Material material) {
		super(color, a, b, c, material);
		point2 = new Point3D(b);
		point3 = new Point3D(c);
		point4 = new Point3D(d);
		Vector ab = point2.subtract(point);
		Vector ad = point4.subtract(point);
		Vector ba = point.subtract(point2);
		Vector bc = point3.subtract(point2);
		//if (!primitives.Util.isZero(ab.dotProduct(ad))||!primitives.Util.isZero(ba.dotProduct(bc)))
		//	throw new IllegalArgumentException(" the rectangle not orthogonal ");
	}

	/**
	 * The function calculates the intersection of the rays with the rectangle and
	 * returns a list of the intersection point
	 * @return List<GeoPoint> of intersection
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// get the plane intersections
		List<GeoPoint> temp = super.findIntersections(ray);
		// if there arn't intersections point in the plain, and point2 is P
		if (temp.isEmpty())
			return EMPTY_LIST;
		//check if the point are on the corners
		if (temp.get(0).point.equals(point)|| temp.get(0).point.equals(point2)||temp.get(0).point.equals(point3)||temp.get(0).point.equals(point4) ) {
			return EMPTY_LIST;
		}
		
		
		Vector ab1 = point2.subtract(point).normalize();
		Vector ad1 = point4.subtract(point).normalize();
		Vector t1 = temp.get(0).point.subtract(point).normalize();
		Vector t2 = temp.get(0).point.subtract(point2).normalize();
		Vector t3 = temp.get(0).point.subtract(point3).normalize();
		Vector t4 = temp.get(0).point.subtract(point4).normalize();

		double a1 = t1.dotProduct(ab1);
		double b1 = t1.dotProduct(ad1);
		double a2 = t2.dotProduct(ab1);
		double b2 = t2.dotProduct(ad1);
		double a3 = t3.dotProduct(ab1);
		double b3 = t3.dotProduct(ad1);
		double a4 = t4.dotProduct(ab1);
		double b4 = t4.dotProduct(ad1);
		if (a1 == 0 || b1 == 0 || a2 == 0 || b2 == 0 ||a3 == 0 || b3 == 0 ||a4 == 0 || b4 == 0) {
			return EMPTY_LIST;
		}
		Vector ab = point2.subtract(point);
		Vector ad = point4.subtract(point);

		Vector t = temp.get(0).point.subtract(point);
		double a = t.dotProduct(ab);
		double b = t.dotProduct(ad);
		//check if the point are inside the rectangle
		// if (0 < t * ab < ab * ab) AND (0 < t * ad < ad * ad)
		if (!(a >= 0.0 && a <= ab.dotProduct(ab) && b >= 0.0 && b <= ad.dotProduct(ad)))
			return EMPTY_LIST;
		return temp;
	}

}
