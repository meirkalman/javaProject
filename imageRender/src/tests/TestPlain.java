/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import geometries.Plain;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * TestPlain tests the class plain. The function checks for end points at points
 * on the plain
 * 
 * @author ravitz & kalaman
 */
public class TestPlain {

	/**
	 * testGetNormal test if the function GetNormal return the right normal Test
	 * method for {@link geometries.Plain#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Point3D p1 = new Point3D(2, 3, 1);
		Point3D p2 = new Point3D(8, 6, 5);
		Point3D p3 = new Point3D(10, 8, 9);
		Vector vector = new Vector(4, -16, 6).normalize();
		Plain plain = new Plain(new Color(), p1, p2, p3, new Material(0, 0, 0));
		assertEquals(vector, plain.getNormal(p2));

		// if all the point on the same line
		Point3D p4 = new Point3D(2, 0, 0);
		Point3D p5 = new Point3D(3, 0, 0);
		Point3D p6 = new Point3D(4, 0, 0);
		try {
			Plain plain1 = new Plain(new Color(), p4, p5, p6, new Material(0, 0, 0));
			fail("Do not throw the exception of the zero vector!");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

	}

	/**
	 * Tests to calculate whether in all cases (including BVA) the function
	 * "findIntersections" will correctly calculate the points
	 */
	@Test
	public void testIntersectionPoints() {

		Plain p1 = new Plain(new Color(), new Point3D(1, 1, 0), new Vector(0, 0, 1), new Material(0, 0, 0));
		Ray r1 = new Ray(new Point3D(2, 2, 2), new Vector(0, 0, -1));
		// the first test is checking the classic example of IntersectionPoints in the
		// plain.
		assertEquals(new Point3D(2, 2, 0), p1.findIntersections(r1).get(0).point);

		Ray r2 = new Ray(new Point3D(2, 2, 2), new Vector(0, 1, 0));
		// No intersections: the ray is parallel to the plane
		assertEquals("the ray is parallel to the plane", 0, p1.findIntersections(r2).size());

		Ray r3 = new Ray(new Point3D(0, 0, 0), new Vector(0, 1, 0));
		// No intersections: the ray is included in the plane
		assertEquals("the ray is included in the plane", 0, p1.findIntersections(r3).size());

		Ray r4 = new Ray(new Point3D(1, 0, 0), new Vector(0, 0, 1));
		// The intersection is the point of the ray point
		assertEquals("", new Point3D(1, 0, 0), p1.findIntersections(r4).get(0).point);

		Ray r5 = new Ray(new Point3D(0, 0, 2), new Vector(0, 0, 1));
		// The beam is aligned to the plane but starts after it and therefore there is
		// no cutting point
		assertEquals(0, p1.findIntersections(r5).size());

		Ray r6 = new Ray(new Point3D(0, 0, -2), new Vector(0, 0, 1));
		// The beam is aligned to the plane and starts before it and therefore there is
		// cutting point
		assertEquals(new Point3D(0, 0, 0), p1.findIntersections(r6).get(0).point);

		Ray r7 = new Ray(new Point3D(2, 2, 2), new Vector(1, 1, 1));
		// No intersections: the ray is tarts after the plane
		assertEquals("the ray is parallel to the plane", 0, p1.findIntersections(r7).size());

		Ray r8 = new Ray(new Point3D(1, 0, 0), new Vector(1, 1, 1));
		// The intersection is the point of the ray point
		assertEquals("", new Point3D(1, 0, 0), p1.findIntersections(r8).get(0).point);
		// the ray start on the point of the plain
		Plain ppPlain = new Plain(new Color(), new Point3D(0, 0, 0), new Point3D(5, 0, 0), new Point3D(0, 0, 5),
				new Material(0, 0, 0));
		Ray r9 = new Ray(new Point3D(0, 0, 0), new Vector(0, -1, 0));
		assertEquals(new Point3D(0, 0, 0), ppPlain.findIntersections(r9).get(0).point);
	}

}
