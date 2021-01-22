package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * The interface expanding other classes The interface has one normalization
 * function The interface extends the interface Intersectable
 * 
 * @author kalman & ravitz
 *
 */
public abstract class Geometry implements Intersectable {
	Color emission;
	Material material;

	/**
	 * constructor
	 * 
	 * @param Color    emmission1
	 * @param Material material1
	 */
	public Geometry(Color emmission1, Material material1) {

		emission = new Color(emmission1);
		material = new Material(material1);
	}

	/**
	 * get material
	 * 
	 * @return Material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * get emmission
	 * 
	 * @return Color
	 */
	public Color getEmission() {
		return emission;
	}

	/**
	 * The function returns the normal to the body
	 * 
	 * @param point3d
	 * @return Vector
	 */
	public Vector getNormal(Point3D point3d) {
		return null;
	}
}
