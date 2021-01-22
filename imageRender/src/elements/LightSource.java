package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * The class light is interface and is designed to give three functions that all the descendants will be fond of exercising
 * @author ravitz & kalaman
 *
 */
public interface LightSource {

	/**
	 * Three statements on functions
	 * @param Point3D point
	 * @return Color
	 */
	Color getIntensity(Point3D point);//return the finel color on the point
	Vector getL(Point3D point);//return the direction from  the light to the point
	Vector getD(Point3D point);//return the direction of the light
}
