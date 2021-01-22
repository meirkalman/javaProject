/**
 * 
 */
package tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import geometries.*;
import elements.Camera;
import static geometries.Intersectable.GeoPoint;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * test the camera class ,The function checks whether the rays are returned as
 * required and also checks the Intersections of the rays with the shapes
 * 
 * @author ravitz & kalaman
 */
public class TestCamera {
	@Test
	public void testRaysConstruction() {

		Point3D p2 = new Point3D(0, 0, 1);

		Vector vto = new Vector(0, 0, -1);
		Vector vup = new Vector(1, 0, 0);
		Camera cam = new Camera(p2, vup, vto);

		/**
		 * In all the following tests we send a ray to the matrix at a distance of 1
		 * from the point of view and check whether the ray that the function returns is
		 * the correct beam test 1: check in 3*3 pixels matrix, the ray intersection the
		 * (0,0)pixel.
		 */
		Vector v1 = new Vector(3, 3, -1);
		assertEquals(new Ray(p2, v1), cam.constructRayThroughPixel(3, 3, 0, 0, 1, 9, 9));

		/**
		 * test 2: check in 3*3 pixels matrix, the ray intersection the (0,1)pixel.
		 */
		Vector v2 = new Vector(0, 3, -1);
		assertEquals(new Ray(p2, v2), cam.constructRayThroughPixel(3, 3, 0, 1, 1, 9, 9));

		/**
		 * test 3: check in 3*3 pixels matrix, the ray intersection the (1,0)pixel.
		 */
		Vector v3 = new Vector(3, 0, -1);
		assertEquals(new Ray(p2, v3), cam.constructRayThroughPixel(3, 3, 1, 0, 1, 9, 9));

		/**
		 * test 4: check in 3*3 pixels matrix, the ray intersection the (1,1)pixel.
		 */
		Vector v4 = new Vector(0, 0, -1);
		assertEquals(new Ray(p2, v4), cam.constructRayThroughPixel(3, 3, 1, 1, 1, 9, 9));

		/**
		 * test 5: check in 4*4 pixels matrix, the ray intersection the (0,0)pixel.
		 */
		Vector v5 = new Vector(4.5, 4.5, -10);
		assertEquals(new Ray(p2, v5), cam.constructRayThroughPixel(4, 4, 0, 0, 10, 12, 12));

		/**
		 * test 6: check in 4*4 pixels matrix, the ray intersection the (0,1)pixel.
		 */
		Vector v6 = new Vector(1.5, 4.5, -10);
		assertEquals(new Ray(p2, v6), cam.constructRayThroughPixel(4, 4, 0, 1, 10, 12, 12));

		/**
		 * test 7: check in 4*4 pixels matrix, the ray intersection the (1,0)pixel.
		 */
		Vector v7 = new Vector(4.5, 1.5, -10);
		assertEquals(new Ray(p2, v7), cam.constructRayThroughPixel(4, 4, 1, 0, 10, 12, 12));

		/**
		 * test 8: check in 4*4 pixels matrix, the ray intersection the (1,1)pixel.
		 */
		Vector v8 = new Vector(1.5, 1.5, -10);
		assertEquals(new Ray(p2, v8), cam.constructRayThroughPixel(4, 4, 1, 1, 10, 12, 12));

		/**
		 * now we will test rays that Intersection the shapes and return the injury
		 * points in form
		 * 
		 * test 1: Sending rays to a 9 * 9 matrix to sphere cut only in the middle pixel
		 */
		List<Ray> rays = new ArrayList<Ray>();
		List<GeoPoint> point3ds = new ArrayList<GeoPoint>();
		int count = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				//rays.add(cam.constructRayThroughPixel(3, 3, i, j, 1, 9, 9));
			}
		}

		Sphere sphere1 = new Sphere(new Color(), 1, new Point3D(0, 0, -3), new Material(0, 0, 0));
		for (Ray ray : rays) {
			point3ds = (sphere1.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(2, count);

		/**
		 * test 2: Sending rays to a 9 * 9 matrix to sphere cut only in the middle pixel
		 * Therefore should be 2 Intersection points
		 */
		Sphere sphere2 = new Sphere(new Color(), 3.45, new Point3D(0, 0, -2.5), new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (sphere2.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(18, count);

		/**
		 * test 3: Sending rays to a 9 * 9 matrix to sphere cut in 5 pixels Therefore
		 * should be 10 Intersection points
		 */
		Sphere sphere3 = new Sphere(new Color(), 2.9, new Point3D(0, 0, -2), new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (sphere3.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(10, count);

		/**
		 * test 4: Sending rays to a 9 * 9 matrix to sphere and the ray starts out of
		 * the ball Therefore should be 9 Intersection points
		 */
		Sphere sphere4 = new Sphere(new Color(), 40, new Point3D(0, 0, -20), new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (sphere4.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(9, count);

		/**
		 * test 5: Sending rays to a 9 * 9 matrix to sphere and the ray starts after of
		 * the ball Therefore should be 0 Intersection points
		 */
		Sphere sphere5 = new Sphere(new Color(), 3, new Point3D(0, 0, 5), new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (sphere5.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(0, count);

		/**
		 * test 6: Sending rays to a 9 * 9 matrix to plain and the and the plane is
		 * perpendicular to the point of view Therefore should be 9 Intersection points
		 */
		Plain plain1 = new Plain(new Color(), new Point3D(0, 0, -1), new Point3D(0, 1, -1), new Point3D(1, 0, -1),
				new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (plain1.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(9, count);

		/**
		 * test 7: Sending rays to a 9 * 9 matrix to plain and the plane blocks all
		 * pixels Therefore should be 9 Intersection points
		 */
		Plain plain2 = new Plain(new Color(), new Point3D(0, 0, -1.1), new Point3D(0, 1, -1), new Point3D(1, 0, -1),
				new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (plain2.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(9, count);

		/**
		 * test 8: Sending rays to a 9 * 9 matrix to plain and the plane blocks all
		 * pixels Therefore should be 6 Intersection points
		 */
		Plain plain3 = new Plain(new Color(), new Point3D(0, 0, -10), new Point3D(0, 1, -1), new Point3D(1, 0, -1),
				new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (plain3.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(6, count);

		/**
		 * test 9: Sending rays to a 9 * 9 matrix to triangle and the ray cut only in
		 * the middle pixel Therefore should be 1 Intersection points
		 */
		Triangle triangle1 = new Triangle(new Color(), new Point3D(0, -1, -2), new Point3D(1, 1, -2),
				new Point3D(-1, 1, -2), new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (triangle1.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(1, count);

		/**
		 * test 10: Sending rays to a 9 * 9 matrix to triangle and the ray cut the
		 * triangle twice Therefore should be 2 Intersection points
		 */
		Triangle triangle2 = new Triangle(new Color(), new Point3D(0, -20, -2), new Point3D(1, 1, -2),
				new Point3D(-1, 1, -2), new Material(0, 0, 0));
		count = 0;
		for (Ray ray : rays) {
			point3ds = (triangle2.findIntersections(ray));
			count += point3ds.size();
		}
		assertEquals(2, count);
	}
}
