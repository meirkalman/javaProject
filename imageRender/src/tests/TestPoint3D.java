/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Point3D;
import primitives.Vector;

/**
 * TestPoint3D tests the class point3D. It checks all relevant class functions.
 * 
 * @author ravitz & kalaman
 */
public class TestPoint3D {

	/**
	 * The function checks whether the point we received after sending to distance
	 * function is the correct point Test method for
	 * {@link primitives.Point3D#distance(primitives.Point3D)}.
	 */
	@Test
	public void testDistancePoint3D() {
		Point3D p1 = new Point3D(0, 0, 10);
		Point3D p2 = new Point3D(0, 0, 5);
		assertEquals(5, p1.distance(p2), 1e-10);
	}

	/**
	 * The function checks whether the point we received after sending to distance
	 * function is the correct point. the function Distance2 is without root. Test
	 * method for {@link primitives.Point3D#distance2(primitives.Point3D)}.
	 */
	@Test
	public void testDistance2() {
		Point3D p1 = new Point3D(0, 0, 10);
		Point3D p2 = new Point3D(0, 0, 5);
		assertEquals(25, p1.distance2(p2), 1e-10);
	}

	/**
	 * The function checks whether the point we received after subtract two point is
	 * the correct point. Test method for
	 * {@link primitives.Point3D#subtract(primitives.Point3D)}.
	 */
	@Test
	public void testSubtract() {
		Point3D p1 = new Point3D(2, 5, 10);
		Point3D p2 = new Point3D(1, 3, 5);
		Vector p3 = new Vector(1, 2, 5);
		assertEquals(p3, p1.subtract(p2));
	}

	/**
	 * The function checks whether the point we received after add point to vector
	 * is the correct point. Test method for
	 * {@link primitives.Point3D#add(primitives.Point3D)}.
	 */
	@Test
	public void testAddVector() {
		Point3D p1 = new Point3D(2, 5, 10);
		Vector v1 = new Vector(1, 3, 5);
		Point3D v = new Point3D(3, 8, 15);
		assertEquals(v, p1.addVector(v1));
	}

}
