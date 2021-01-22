package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;

/**
 * The interface is designed inherit to everyone the function of
 * findIntersections
 * 
 * @author ravitz & kalaman
 *
 */
public interface Intersectable {
	/**
	 * inner class geo point to return the point and the geometry shape
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point3D point;

		/**
		 * constructor
		 * 
		 * @param Geometry geo
		 * @param Point3D  point1
		 */
		public GeoPoint(Geometry geo, Point3D point1) {
			geometry = geo;
			point = point1;
		}
	}

	// empty list for return
	public static final List<GeoPoint> EMPTY_LIST = new ArrayList<GeoPoint>();

	List<GeoPoint> findIntersections(Ray ray);
}
