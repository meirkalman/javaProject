
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class representing light is similar to pointLight,we can also focus the
 * light, The class extends the class pointLight.
 * 
 * @author ravitz & kalaman
 *
 */
public class SpotLight extends PointLight {
	Vector direction;// the direction of the light
	int np;// the power to focus the spot

	/**
	 * c-tor
	 * @param Color   color
	 * @param Point3D position
	 * @param         double l
	 * @param         double q
	 * @param Vector  direction
	 * @param         double np1
	 */
	public SpotLight(Color col, Point3D pos, double l, double q, Vector dir, int np1) {
		super(col, pos, l, q);
		np = np1;
		direction = dir.normalize();
	}

	/**
	 * The function calculates the effect of light on an point by distance and data
	 * of the light itself
	 * 
	 * @param Point3D point
	 * @return Color
	 */
	@Override
	public Color getIntensity(Point3D point) {
	// 𝑰𝑳 = 𝑰𝟎*max(0,dir*l)^np / 𝒌𝒄 + 𝒌𝒍 ∙ 𝒅 + 𝒌𝒒 ∙ 𝒅𝟐
		double max = primitives.Util.alignZero(direction.dotProduct(getL(point)));// variable max to check whether this angle is sharp or dark
		return max <= 0 ? Color.BLACK
				: super.getIntensity(point).scale(Math.pow(max, np));
	}

	/**
	 * A function that returns a vector direction between the point and the light
	 * source
	 * 
	 * @param Point3D point
	 * @return Vector
	 */
	@Override
	public Vector getL(Point3D point) {
		return point.subtract(position).normalize();
	}

	/**
	 * A function that returns the vector direction of the light source
	 * 
	 * @param Point3D point
	 * @return Vector
	 */
	@Override
	public Vector getD(Point3D point) {
		return direction;
	}
}
