
package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class light is abstract, and it holds the color for all types of light
 * 
 * @author ravitz & kalaman
 *
 */
public abstract class Light {
	private Color intensity;

	/**
	 * constructor
	 * 
	 * @param Color col
	 */
	public Light(Color col) {
		intensity = new Color(col);
	}

	/**
	 * get intensity return the intensity
	 * 
	 * @return Color
	 */
	public Color getIntensity() {
		return intensity;
	}

}
