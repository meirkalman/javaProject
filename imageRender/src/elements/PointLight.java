/**
 * 
 */
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class represents a light that resembles a light bulb. and extends Light,
 * and implements the interface LightSource The class contains a center of light
 * spot and light intensity data
 * 
 * @author ravitz & kalaman
 *
 */
public class PointLight extends Light implements LightSource {

	Point3D position;
	double kc, kl, kq;

	/**
	 * constructor
	 * 
	 * @param Color   col
	 * @param Point3D pos
	 * @param         double l
	 * @param         double q
	 */
	public PointLight(Color col, Point3D pos, double l, double q) {
		super(col);
		position = new Point3D(pos);
		kc = 1;
		kl = l;
		kq = q;
	}

	/**
	 * The function calculates the effect of light on an point by distance and data
	 * of the light itself
	 * 
	 * @param Point3D point
	 */
	@Override
	public Color getIntensity(Point3D point) {
		double distance = point.distance(position);
		// il = i0 / kc + kl ∙ d + kq ∙ d*d
		return getIntensity().reduce(kc + kl * distance + kq * (distance * distance));
	}

	/**
	 * A function that returns a vector direction between the point and the light
	 * source
	 * 
	 * @param Point3D point
	 * @return vector
	 */
	@Override
	public Vector getL(Point3D point) {
		return point.subtract(position).normalize();
	}

	/**
	 * A function that returns the vector direction of the light source
	 * 
	 * @param Point3D point
	 * @return vector
	 */
	@Override
	public Vector getD(Point3D point) {
		return this.getL(point);
	}
}
