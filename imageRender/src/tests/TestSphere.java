/**
 * 
 */
package tests;

import static org.junit.Assert.*;
import static java.lang.StrictMath.sqrt;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import geometries.Sphere;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * TestSphere tests the class Sphere.
 * 
 * @author paluch
 *
 */
public class TestSphere {

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * The function makes a circle and a point on the circle and checks the normal
	 * to the point Test method for
	 * {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Vector vector = new Vector(1, 0, 0);
		Point3D point3d = new Point3D(5, 0, 0);
		Sphere a = new Sphere(new Color(), 5, new Point3D(0, 0, 0), new Material(0, 0, 0));
		Vector b = a.getNormal(point3d);
		assertEquals(vector, b);
	}

	/**
	 * Tests to calculate whether in all cases (including BVA) the function
	 * "findIntersections" will correctly calculate the points
	 * 
	 */
	@Test
	public void testIntersectionPoints() {
		Sphere sphere = new Sphere(new Color(), 5, new Point3D(0, 0, 0), new Material(0, 0, 0));
		Ray ray1 = new Ray(new Point3D(6, 0, -1), new Vector(-1, 0, 0));
		// test-1:
		// The ray starts before the sphere and cuts the sphere twice
		Point3D pTemp1 = new Point3D(sqrt(24), 0, -1);
		Point3D pTemp2 = new Point3D(-sqrt(24), 0, -1);
		assertEquals(pTemp1, sphere.findIntersections(ray1).get(0).point);
		assertEquals(pTemp2, sphere.findIntersections(ray1).get(1).point);

		// test-2:
		// The ray starts in the sphere and cuts the sphere once
		Ray ray2 = new Ray(new Point3D(0, 0, 3), new Vector(-2.836545463155382, -2.858758325474858, -0.03663861940856));
		Point3D pTemp3 = new Point3D(-2.836545463155382, -2.858758325474858, 2.96336138059144);
		assertEquals(pTemp3, sphere.findIntersections(ray2).get(0).point);
		assertEquals(1, sphere.findIntersections(ray2).size());

		// test-3:
		// The ray starts after the sphere and not cuts the sphere
		Ray ray3 = new Ray(new Point3D(6, 0, -1), new Vector(1, 0, 0));
		assertEquals(0, sphere.findIntersections(ray3).size());

		// test-4:
		// The ray starts not on the sphere and not cuts the sphere
		Ray ray4 = new Ray(new Point3D(6, 0, -1), new Vector(0, 1, 0));
		assertEquals(0, sphere.findIntersections(ray4).size());

		// test-5:
		// The ray starts on the sphere and continues in the opposite direction from
		// the sphere

		Ray ray5 = new Ray(new Point3D(-sqrt(24), 0, -1), new Vector(-1, 0, 0));
		assertEquals(new Point3D(-sqrt(24), 0, -1), sphere.findIntersections(ray5).get(0).point);
		assertEquals(1, sphere.findIntersections(ray5).size());

		// test-6:
		// The ray starts on the sphere and continues into the sphere and not cuts the
		// midpoint
		Ray ray6 = new Ray(new Point3D(sqrt(24), 0, -1), new Vector(-1, 0, 0));
		assertEquals(new Point3D(sqrt(24), 0, -1), sphere.findIntersections(ray6).get(0).point);
		assertEquals(new Point3D(-sqrt(24), 0, -1), sphere.findIntersections(ray6).get(1).point);

		// test-7:
		// The ray starts on the sphere and continues in the opposite direction from the
		// midpoint
		Ray ray7 = new Ray(new Point3D(5, 0, 0), new Vector(1, 0, 0));
		assertEquals(new Point3D(5, 0, 0), sphere.findIntersections(ray7).get(0).point);
		assertEquals(1, sphere.findIntersections(ray7).size());

		// test-8:
		// The ray starts on the sphere and continues into the sphere and cuts the
		// midpoint
		Ray ray8 = new Ray(new Point3D(5, 0, 0), new Vector(-1, 0, 0));
		assertEquals(new Point3D(5, 0, 0), sphere.findIntersections(ray8).get(0).point);
		assertEquals(new Point3D(-5, 0, 0), sphere.findIntersections(ray8).get(1).point);

		// test-9:
		// The ray starts after the sphere and continues in the opposite direction from
		// the midpoint
		Ray ray9 = new Ray(new Point3D(6, 0, 0), new Vector(1, 0, 0));
		assertEquals(0, sphere.findIntersections(ray9).size());

		// test-10:
		// The ray starts after the sphere and continues in the direction to
		// the midpoint
		Ray ray10 = new Ray(new Point3D(6, 0, 0), new Vector(-1, 0, 0));
		assertEquals(new Point3D(5, 0, 0), sphere.findIntersections(ray10).get(0).point);
		assertEquals(new Point3D(-5, 0, 0), sphere.findIntersections(ray10).get(1).point);

		// test-11:
		// The ray starts on the midpoint and cuts the sphere once
		Ray ray11 = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 1));
		assertEquals(new Point3D(5 / sqrt(3), 5 / sqrt(3), 5 / sqrt(3)), sphere.findIntersections(ray11).get(0).point);
		assertEquals(1, sphere.findIntersections(ray11).size());

		// test-12:
		// The ray starts on the sphere and this is a point of contact
		Ray ray12 = new Ray(new Point3D(5, 0, 0), new Vector(0, 1, 0));
		assertEquals(new Point3D(5, 0, 0), sphere.findIntersections(ray12).get(0).point);
		assertEquals(1, sphere.findIntersections(ray12).size());

		// test-13:
		// The ray starts before the sphere and there is a point of contact
		Ray ray13 = new Ray(new Point3D(5, -1, 0), new Vector(0, 1, 0));
		assertEquals(new Point3D(5, 0, 0), sphere.findIntersections(ray13).get(0).point);
		assertEquals(1, sphere.findIntersections(ray13).size());

		// test-14:
		// The ray starts after the sphere but if he had started before I would have a
		// point of contact
		Ray ray14 = new Ray(new Point3D(5, 1, 0), new Vector(0, 1, 0));
		assertEquals(0, sphere.findIntersections(ray14).size());

		// test-15:
		// The ray starts point does not touch the sphere but the vector is orthogonal
		// to the sphere
		Ray ray15 = new Ray(new Point3D(6, 0, 0), new Vector(0, 1, 0));
		assertEquals(0, sphere.findIntersections(ray15).size());
	}

}
