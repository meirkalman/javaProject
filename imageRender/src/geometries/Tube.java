package geometries;

import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The class represents a tube in space by a radius and ray The class extends
 * the class RadialGeometry
 * 
 * @author ravitz & kalaman
 */
public class Tube extends RadialGeometry {
	protected Ray axisRay;

	// ********** Constructors ***********

	/**
	 * Constructor
	 * 
	 * @param Color    emish
	 * @param          double radius1
	 * @param Ray      axisRay1
	 * @param Material material
	 */
	public Tube(Color emish, double radius1, Ray axisRay1, Material material) {
		super(emish, radius1, material);
		this.axisRay = axisRay1;
	}

	// ************** Getters *******
	/**
	 * get axis ray return the ray of the tube
	 * 
	 * @return Ray
	 */
	public Ray getAxisRay() {
		return axisRay;
	}

	// *************** Administration *****************
	/**
	 * to string return the all details of the class
	 */
	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay + "]";
	}

	/**
	 * get normal to the Tube
	 *
	 * @param point on the Tube
	 * @return The normal Vector
	 */
	@Override
	public Vector getNormal(Point3D point3d) {
		Point3D p0 = axisRay.getPoint();
		Vector vector = axisRay.getVector();
		// t = (point-POO)*rayDirection
		double t = (point3d.subtract(p0)).dotProduct(vector);
		// if (primitives.Util.isZero(t))
		// return point3d.subtract(p0).normalize();
		// POO + t*rayDirection = O
		Point3D o = p0.addVector(vector.scaling(t));
		return point3d.subtract(o).normalize();

	}

	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
