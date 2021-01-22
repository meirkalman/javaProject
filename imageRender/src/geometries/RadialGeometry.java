package geometries;

import primitives.Color;
import primitives.Material;

/**
 * The abstract class is to inherit a radius
 * 
 * @author ravitz & kalaman
 *
 */
public abstract class RadialGeometry extends Geometry {
	protected double radius;

	// ***************** Constructors ********************** //

	/**
	 * Constructor
	 * 
	 * @param color    emission color
	 * @param radius   of the Geometry
	 * @param material of the Geometry
	 */
	public RadialGeometry(Color color, double radius1, Material material) {
		super(color, material);
		if (radius1 < 0 || primitives.Util.isZero(radius1)) {
			throw new ArithmeticException("You can not make such a radius!");
		}
		radius = radius1;
	}

	// ************** Getters *******
	/**
	 * get radius
	 * 
	 * @return double - the radius
	 */
	public double get_radius() {
		return radius;
	}

	// *************** Administration *****************
	/**
	 * to string return the all details of the class
	 */
	@Override
	public String toString() {
		return " radius " + radius;
	}

}
