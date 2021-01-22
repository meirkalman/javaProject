/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import geometries.Rectangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author paluch
 *
 */
public class TestRectangle {

	@Test
	public void testIntersectionPoints() {
		Rectangle rectangle = new Rectangle(new Color(),new Point3D(-5, 0, 5), new Point3D(5, 0, 5), new Point3D(5, 0, 0),new Point3D(-5, 0,0),  new Material(0, 0, 0));
		Ray ray1 = new Ray(new Point3D(0, 5, 1), new Vector(0, -1, 0));
		assertEquals(1, rectangle.findIntersections(ray1).size());
		assertEquals(new Point3D(0, 0, 1), rectangle.findIntersections(ray1).get(0).point);
		
		Ray ray2 = new Ray(new Point3D(0, 5, 0), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray2).size());
		
		
		Ray ray3 = new Ray(new Point3D(5, 5, 0), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray3).size());
		
		
		Ray ray4 = new Ray(new Point3D(5, 0, 0), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray4).size());
		
		
		Ray ray5 = new Ray(new Point3D(0, 5, 5), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray5).size());
		
		
		Ray ray6 = new Ray(new Point3D(-5, 5, 2), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray6).size());
		
		
		Ray ray7 = new Ray(new Point3D(5, 5, 2), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray7).size());
		
		Ray ray8 = new Ray(new Point3D(5, 0, 2), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray8).size());
		
		Ray ray9 = new Ray(new Point3D(6, 5, 2), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray9).size());
		
		Ray ray10 = new Ray(new Point3D(0, -1, 0), new Vector(0, -1, 0));
		assertEquals(0, rectangle.findIntersections(ray10).size());
	}
}
