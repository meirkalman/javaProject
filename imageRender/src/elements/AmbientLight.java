package elements;

import primitives.Color;

/**
 * The class represents the color of the shapes in the scene. The class gets
 * color and number and calculates the color of the shapes
 * 
 * @author kalman & ravitz
 *
 */
public class AmbientLight extends Light {
	/**
	 * constructor. The constructor gets color and number and calculates the color
	 * of the shapes
	 * 
	 * @param Color i
	 * @param       double k
	 */
	public AmbientLight(Color i, double k) {
		super(i.scale(k));
	}
}
