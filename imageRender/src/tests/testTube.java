/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import geometries.Tube;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author paluch
 *
 */
public class testTube {

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Point3D p1 = new Point3D(-10, 0, 0);
		Vector v2 = new Vector(0, 0, 1);
		Point3D p3 = new Point3D(-5, 0, 1);
		Vector v1 = new Vector(1, 0, 0);
		Ray r1 = new Ray(p1, v1);
		Tube t1 = new Tube(new Color(), 1, r1, new Material(0, 0, 0));
		assertEquals(v2, t1.getNormal(p3));

	}

}
