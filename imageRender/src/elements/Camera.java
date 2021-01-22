package elements;

import java.util.*;

import geometries.Intersectable.GeoPoint;
import geometries.Plain;
import primitives.*;
import primitives.Vector;
//import sun.net.www.content.text.plain;
import renderer.Render;

import static primitives.Util.isZero;
import static java.lang.StrictMath.sqrt;

/**
 * The class represents a camera by center point and 3 vectors.
 * 
 * @author ravitz & kalaman
 *
 */
public class Camera {

	private Point3D p0;
	private primitives.Vector vUp;
	private primitives.Vector vTo;
	private primitives.Vector vRight;
	private double distancePlain;
	private double aperture;
	private int numOfRays;

	/**
	 * c-tor
	 * 
	 * @param Point3D p
	 * @param Vector  up
	 * @param Vector  to
	 */
	public Camera(Point3D p, Vector up, Vector to) {
		double temp = up.dotProduct(to);
// Checks whether the vectors are orthogonal to each other and throw exception
// if they not
		if (!isZero(temp)) {
			throw new IllegalArgumentException("the vector is not orthogonal");
		}
		p0 = p;
		vUp = up.normalize();
		vTo = to.normalize();
		vRight = vTo.crossProduct(vUp).normalize();
	}

	/**
	 * get the num of the rays
	 * 
	 * @return int
	 */
	public int getNumOfRays() {
		return numOfRays;
	}

	/**
	 * set num of rays
	 * 
	 * @param numOfRays int
	 */
	public void setNumOfRays(int numOfRays) {
		this.numOfRays = numOfRays;
	}

	/**
	 * get distance plain
	 * 
	 * @return the distance from view plain to the focal plain
	 */
	public double getDistancePlain() {
		return distancePlain;
	}

	/**
	 * set Ddistance plain
	 * 
	 * @param distancePlain
	 */
	public void setDistancePlain(double distancePlain) {
		// check if distance plain are negative number
		if (distancePlain <= 0) {
			throw new IllegalArgumentException("can't be negative number");
		}
		this.distancePlain = distancePlain;
	}

	/**
	 * get the size of the aperture
	 * 
	 * @return aperture size
	 */
	public double getAperture() {
		return aperture;
	}

	/**
	 * set aperture size
	 * 
	 * @param aperture size
	 */
	public void setAperture(double aperture) {
		if (aperture <= 0) {// stop condition cant be smaller than 0
			throw new IllegalArgumentException("cant be smaller than 0");
		}
		this.aperture = aperture;
	}

//	/**
//	 * The function allows you to select whether to activate the mode of
//	 * 
//	 * @return boolean ture of false
//	 */
//	public boolean isDof() {
//		return dof;
//	}
//
//	/**
//	 * set dof ture of false
//	 * 
//	 * @param dof
//	 */
//	public void setDof(boolean dof) {
//		this.dof = dof;
//	}

	/**
	 * The function receives a ray and builds a set of rays toward the focus
	 * 
	 * @param ray            the ray from the camera
	 * @param screenDistance The distance of the focus
	 * @return List<Ray>
	 */
	public List<Ray> groupRaysForFocus(Ray ray, double screenDistance) {

		Plain plain = setFocalPlain(screenDistance);// focal plain
		List<Ray> beamRays = new ArrayList<Ray>();// list for return beams of rays
		Point3D pointi = ray.getPoint();
		Point3D focalPoint = plain.findIntersections(ray).get(0).point;// focal point
		Point3D p;
		for (int k = 0; k < numOfRays; k++) {
			p = pointi;
			// Receiving 2 random numbers to move the point and create group of rays
			double randomX = Math.random() * aperture - aperture / 2;
			double randomY = Math.random() * aperture - aperture / 2;
			if (!isZero(randomX)) {
				p = p.addVector(vRight.scaling(randomX));
			}
			if (!isZero(randomY)) {
				p = p.addVector(vUp.scaling(randomY));
			}
			beamRays.add(new Ray(p, focalPoint.subtract(p)));
		}
		return beamRays;
	}

	/**
	 * return the center point
	 * 
	 * @return Point3D
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * The function accepts the distance that the focus plane should be and builds
	 * the focus plane
	 * 
	 * @param screenDistance focus plane distance
	 * @return Plain focus plane
	 */
	private Plain setFocalPlain(double screenDistance) {
		Point3D focalPlain = new Point3D(p0.addVector(vTo.scaling(screenDistance + distancePlain)));
		return new Plain(new Color(), focalPlain, focalPlain.subtract(p0), new Material(0, 0, 0));
	}

	/**
	 * return the Vector vUp
	 * 
	 * @return Vector
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * return the Vector vTo
	 * 
	 * @return Vector
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * return the Vector vRight
	 * 
	 * @return Vector
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * Construct the Rays from the Camera to the place in the view plane
	 * 
	 * @param Nx             num of rows
	 * @param Ny             num of columns
	 * @param i              index i
	 * @param j              index j
	 * @param screenDistance Distance between the camera and the view plane
	 * @param screenWidth    real width of the view plane
	 * @param screenHeight   real height of the view plane
	 * @return List of Rays from the Camera to the view plane
	 */
	public Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double screenWidth,
			double screenHeight) {
		Point3D pc = p0.addVector(vTo.scaling(screenDistance));// Pc = P0 +
																// d∙Vto////////////////////////////////////////////////////לבדוק
																// אם להוציא את זה החוצה

// Ratio (pixel width & height)
		double rx = screenHeight / Ny;// Ry = h/Ny
		double ry = screenWidth / Nx;// Rx = w/Nx

		double yj = ((j - Ny / 2.0) * ry) + (ry / 2.0);// yj = (j – Ny/2)∙Ry - Ry/2
		double xi = ((i - Nx / 2.0) * rx) + (rx / 2.0);// xi = (i – Nx/2)∙Rx - //Rx/2

//	pixel[i,j] center Pi,j = Pc + (xi∙vright – yj∙vup) 
		Point3D pixel = pc;
		if (!isZero(xi)) { // Prevents the problem of the zero vector
			pixel = pixel.addVector(vRight.scaling(xi));
		}
		if (!isZero(yj)) {// Prevents the problem of the zero vector
			pixel = pixel.addVector(vUp.scaling(-yj));
		}
		return new Ray(pixel, pixel.subtract(p0));
	}

}
