package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * The class represents a cylinder by ray height and radius the class extends
 * the class tube
 * 
 * @author ravitz & kalaman
 */
public class Cylinder extends Tube {
	protected double height;

	// ********** Constructors ***********
	/**
	 * Constructor
	 * 
	 * @param       double radius1
	 * @param       double axisRay1
	 * @param       double height1
	 * @param Color emish
	 */
	public Cylinder(Color emish, double radius1, Ray axisRay1, double height1, Material material) {

		super(emish, radius1, axisRay1, material);
		if (height1 < 0 || primitives.Util.isZero(height1)) {
			throw new ArithmeticException("You can not make such a height!");
		}
		this.height = height1;
	}

	// *************** Administration *****************
	/**
	 * to string return the all details of the class
	 */
	@Override
	public String toString() {
		return "height=" + height + super.toString();
	}

	// ************** Getters/Setters *******
	/**
	 * get return the height
	 * 
	 * @return height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Testing an end point where the scalar is equal to zero or the height of the
	 * cylinder
	 */
	@Override
	public Vector getNormal(Point3D point3d) {
		double t = (point3d.subtract(axisRay.getPoint())).dotProduct(axisRay.getVector());
		if (primitives.Util.isZero(t)) {
			return axisRay.getVector().scaling(-1);
		}
		if (t == height) {
			return axisRay.getVector();
		} else {
			return super.getNormal(point3d);
		}
	}
}
