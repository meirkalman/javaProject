package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;

/**
 * The class represents a list of Geometries shapes The class implements the
 * interface Intersectable
 * 
 * @author kalman & ravitz
 *
 */
public class Geometries implements Intersectable {
	List<Intersectable> _geometries;

	/**
	 * constructor add shape to the list
	 * 
	 * @param Intersectable shape
	 */
	public Geometries(Intersectable... shapes) {
		_geometries = new ArrayList<Intersectable>();
		for (Intersectable shape : shapes)
			_geometries.add(shape);
	}

	/**
	 * add one shapes to _geometries
	 * 
	 * @param shapes
	 */
	public void add(Intersectable shapes) {
		_geometries.add(shapes);
	}

	/**
	 * the function runs the function on all the shapes it accepts and return list
	 * of all the Intersection points
	 * 
	 */
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> pointsList = new ArrayList<GeoPoint>();
		for (Intersectable g : _geometries) {
			pointsList.addAll(g.findIntersections(ray));
		}
		// _geometries.forEach(g -> pointsList.addAll(g.findIntersections(ray)));
		return pointsList;
	}

}
