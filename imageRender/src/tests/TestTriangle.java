/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * TestTriangle tests the class triangle.
 * 
 * @author paluch
 *
 */
public class TestTriangle {

	/**
	 * Test method for {@link geometries.Plain#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(0, 0, 0);
		Point3D p3 = new Point3D(0, 1, 0);
		Vector p4 = new Vector(0, 0, -1);
		Triangle t1 = new Triangle(new Color(), p1, p2, p3, new Material(0, 0, 0));
		assertEquals(p4, t1.getNormal(p2));
	}

	/**
	 * test all the options that findIntersections function can return on triangle
	 */
	@Test
	public void testIntersectionPoints() {
		Triangle t1 = new Triangle(new Color(), new Point3D(0, 0, 0), new Point3D(5, 0, 0), new Point3D(0, 0, 5),
				new Material(0, 0, 0));
		// the ray intersection the plane but not the triangle
		Ray r1 = new Ray(new Point3D(-1, 1, 1), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r1).size());

		// The ray intersection the plane between the continuation of a pair of rays of
		// the triangle
		Ray r2 = new Ray(new Point3D(-1, 1, 10), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r2).size());

		// the ray start before the triangle and intersection inside the triangle
		Ray r3 = new Ray(new Point3D(1, 1, 1), new Vector(0, -1, 0));
		assertEquals(new Point3D(1, 0, 1), t1.findIntersections(r3).get(0).point);

		// the ray start on the corner point
		Ray r4 = new Ray(new Point3D(0, 0, 0), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r4).size());

		// the ray start before the corner point
		Ray r5 = new Ray(new Point3D(0, 1, 5), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r5).size());

		// the ray start on one the edges
		Ray r6 = new Ray(new Point3D(0, 0, 3), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r6).size());

		// the ray start before the triangle and intersections one the edges
		Ray r7 = new Ray(new Point3D(0, 1, 3), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r7).size());

		// the ray start before the triangle and intersections on the continued ray of
		// the triangle
		Ray r8 = new Ray(new Point3D(0, 0, 10), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r8).size());

		// the ray start on the continued ray of the triangle
		Ray r9 = new Ray(new Point3D(0, 1, 10), new Vector(0, -1, 0));
		assertEquals(0, t1.findIntersections(r9).size());
		
		
	}
}
