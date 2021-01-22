package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class represents a direct light similar to sunlight and extends the
 * abstract class light, and implements the interface LightSource
 * 
 * @author ravitz & kalaman
 *
 */
public class DirectionalLight extends Light implements LightSource {

	Vector direction;// the direction of the light

	/**
	 * c-tor
	 * 
	 * @param Color  col
	 * @param Vector dir
	 */
	public DirectionalLight(Color col, Vector dir) {
		super(col);
		direction = dir.normalize();
	}

	/**
	 * The function calculates the effect of light on an point by distance and data
	 * of the light itself
	 */

	@Override
	public Color getIntensity(Point3D point) {
		return getIntensity();// in this case i0 = ip
	}

	/**
	 * A function that returns a vector direction between the point and the light
	 * source
	 */
	@Override
	public Vector getL(Point3D point) {
		return direction;
	}

	/**
	 * A function that returns the vector direction of the light source
	 */
	@Override
	public Vector getD(Point3D point) {
		return direction;
	}

}
