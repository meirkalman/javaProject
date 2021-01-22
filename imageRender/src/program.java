
//yitzchak ravitz - 204108609
//meir kalman - 312246606

import static java.lang.StrictMath.sqrt;
import static org.junit.Assert.assertEquals;

import java.util.List;

import geometries.Plain;
import geometries.Sphere;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class program {

	public static void main(String[] args) {
		try {

			// TODO Auto-generated method stub
			Coordinate coor1 = new Coordinate(1.5);
			Coordinate coor2 = new Coordinate(coor1);
			Coordinate coor3 = new Coordinate(8.5);

			Point3D point3 = new Point3D(1, 0, 0);
			Point3D point4 = new Point3D(4, 5, 6);
			Point3D point1 = new Point3D(coor1, coor2, coor3);
			Point3D point2 = new Point3D(point3);
			Point3D point5 = new Point3D(0, 0, 1);
			Point3D point6 = new Point3D(2, 2, 0);
			Point3D point7 = new Point3D(2, 2, 2);

			Vector v8 = point7.subtract(point6).normalize();
			Vector vec1 = new Vector(point1);
			Vector vec2 = new Vector(0, 0, 1);
			Vector vec3 = new Vector(vec1);
			Vector vec4 = new Vector(point6);
			Vector vec5 = new Vector(point7);
			Vector vec7 = new Vector(1, 0, 0);
			Vector vec8 = new Vector(0, 0, 1);

			Ray r1 = new Ray(point3, vec7);
			Ray r2 = new Ray(point2, vec2);

//			Sphere s1 = new Sphere(2.5, point2);

//			System.out.println(point1.subtract(point2).toString());
//			System.out.println(point4.distance(point3));
//			System.out.println(point4.equals(point1));
//			System.out.println(point2.equals(point3));
//
//			System.out.println(vec2.normalize().toString());
//			System.out.println(vec2.add(vec3));
//			System.out.println(vec2.subtract(vec3));
//			System.out.println(vec5.dotProduct(vec4));
//			
//			System.out.println(vec7.length());
//
//			System.out.println(r1.equals(r2));
//			System.out.println(r1.toString());
//			System.out.println(r1.getPoint().toString());
//			System.out.println(vec2.normalize());
//			System.out.println(vec7.toString());
//			
//     		System.out.println(vec8.crossProduct(vec7));
//			
//			Plain plain = new Plain(point3, vec2);
//			
//			Ray ray6 = new Ray(new Point3D(sqrt(24), 0, -1), new Vector(-1, 0, 0));
//			Sphere sphere = new Sphere(5, new Point3D(0, 0, 0));
//			
//			System.out.println( sphere.findIntersections(ray6).get(0));
//			//assertEquals(new Point3D(-sqrt(24), 0, -1), sphere.findIntersections(ray6).get(1));
			double a = 72.98562457;
			double b = 73.0352147;
			double c = 72.5454879;
			double d = 73.45458795;
			System.out.println( (int)a);
			System.out.println((int)b);
			System.out.println( (int)c);
			System.out.println((int)d);

		} catch (NullPointerException e) {
			System.out.println(e);
		}

	}

}
