/**
 * 
 */
package primitives;

/**
 * class GeoRay, The class has a beam with color 
 * @author paluch
 *
 */
public class GeoRay {
	protected Ray ray;
	protected Color color;
	
	/**
	 * constructor
	 * @param ray1 the ray
	 * @param color1 the color
	 */
	public GeoRay(Ray ray1, Color color1) {
		ray = ray1;
		color = color1;
	}
	
	/**
	 * copy constructor
	 * @param ray1
	 */
	public GeoRay(Ray ray1) {
		ray = ray1;
		color = new Color();
	}
	
	/**
	 * get color
	 * @return the color of the ray
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * set color
	 * @param color1
	 */
	public void setColor(Color color1) {
		color = color1;
	}
	
	/**
	 * get ray
	 * @return the ray
	 */
	public Ray getRay() {
		return ray;
	}
	
	/**
	 * set ray
	 * @param ray1
	 */
	public void setRay(Ray ray1) {
		ray = ray1;
	}
	
}
