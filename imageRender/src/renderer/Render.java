package renderer;

import java.awt.Checkbox;
import java.awt.image.AreaAveragingScaleFilter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.imageio.event.IIOReadProgressListener;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import elements.Camera;

//import com.ibm.xtq.ast.nodes.ForEach;

import elements.LightSource;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;
import static geometries.Intersectable.GeoPoint;

/**
 * In this class we will get a scene and the information about the dimensions of
 * the image and build the image.
 * 
 * @author kalman & ravitz
 *
 */
public class Render {
	ImageWriter imageWriter;
	Scene scene;
	private static final double EPS = 0.1;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final int MAX_CALC_COLOR_LEVEL = 15;
	private static final int MAX_LEVEL = 4;

	/**
	 * Default constructor
	 */
	public Render() {
	}

	/**
	 * constructor
	 * 
	 * @param ImageWriter a
	 * @param Scene       b
	 */
	public Render(ImageWriter a, Scene b) {
		imageWriter = a;
		scene = b;
	}

	private static int counter = 0, prev = 0;

	/**
	 * The function goes over every pixel in the image and sends a ray from the
	 * camera. Then the function checks whether there is a Intersection point with
	 * any shape, if there is an Intersection then the pixel will color in the
	 * correlation color if not the pixel will color in the background color
	 */
	public void renderImage() {
		int nx = imageWriter.getNx();
		int ny = imageWriter.getNy();
		double width = imageWriter.getWidth();
		double height = imageWriter.getHeight();
		Color background = scene.getBackground();
		Camera camera = scene.getCamera();
		double distance = scene.getDistance();

		int total = nx * ny;
		int delta = total / 1000;
		System.out.println("0.0%");
		final DecimalFormat df = new DecimalFormat("##.#");
		ThreadPoolExecutor executor = null;
		if (scene.isMultiThread()) {
			int cores = Runtime.getRuntime().availableProcessors();
			executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(cores);
		}
		for (int i = 0; i < nx; i++)
			for (int j = 0; j < ny; j++) {
				final int i1 = i;
				final int j1 = j;
				Runnable worker = () -> {
					if ((++counter - prev) >= delta) {
						prev = counter;
						System.out.println(df.format((double) counter / total * 100) + "%");
					}
					// List of rays from the view plane
					Ray intersectionRay = camera.constructRayThroughPixel(nx, ny, i1, j1, distance, width, height);
					Color averageColor = Color.BLACK;
					Color temp = Color.BLACK;

					if (scene.isAdaptive()) {
						Color[][] matrix = new Color[9][9];
						matrix = adaptiveSupersampling(intersectionRay.getPoint(), height / ny, width / nx);
//						if (scene.isDof()) {
//							for (GeoRay g : adptRays) {
//								List<Ray> intersectionRays = camera.groupRaysForFocus(g.getRay(), distance);
//								for (Ray r : intersectionRays) {
//									GeoPoint closestPoint = findClosestIntersection(r);
//									if (closestPoint == null)// check if there arn't intersection points
//										temp = temp.add(background);
//									else
//										temp = temp.add(calcColor(closestPoint, r));
//								}
//								temp = temp.reduce(g.getNum() * intersectionRays.size());
//								averageColor = averageColor.add(temp);
//								temp = Color.BLACK;
//							}
//						} else {
						for (int r1 = 0; r1 < 9; r1++) {
							for (int r2 = 0; r2 < 9; r2++) {
								averageColor = averageColor.add(matrix[r1][r2].reduce(81));
							}
						}
					}
					// adptRays.clear();
					if (scene.isSupersampling()) {
						List<Ray> intersectionRays = supersampling(intersectionRay.getPoint(), height / ny, width / nx);
						if (scene.isDof()) {
							for (Ray r1 : intersectionRays) {
								List<Ray> intersectRays = camera.groupRaysForFocus(r1, distance);
								for (Ray r2 : intersectRays) {
									GeoPoint closestPoint = findClosestIntersection(r2);
									if (closestPoint == null)// check if there arn't intersection points
										temp = temp.add(background);
									else
										temp = temp.add(calcColor(closestPoint, r2));
								}
								averageColor = averageColor.add(temp.reduce(intersectRays.size()));
								temp = new Color();

							}
							averageColor = averageColor.reduce(intersectionRays.size());
						} else {
							for (Ray r : intersectionRays) {
								GeoPoint closestPoint = findClosestIntersection(r);
								if (closestPoint == null)// check if there arn't intersection points
									averageColor = averageColor.add(background);
								else
									averageColor = averageColor.add(calcColor(closestPoint, r));
							}
							// Divides the final color by the number of rays we have create
							averageColor = averageColor.reduce(intersectionRays.size());
						}
					}
					if (scene.isJustPicture()) {
						GeoPoint closestPoint = findClosestIntersection(intersectionRay);
						if (closestPoint == null)// check if there arn't intersection points
							averageColor = averageColor.add(background);
						else
							averageColor = averageColor.add(calcColor(closestPoint, intersectionRay));
					}
					imageWriter.writePixel(i1, j1, averageColor.getColor());
				};
				if (scene.isMultiThread())
					executor.execute(worker);
				else {
					worker.run();
				}
			}
		if (scene.isMultiThread()) {
			executor.shutdown();
			try {
				executor.awaitTermination(550, TimeUnit.MINUTES);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * private function to calculate the other parameters in private alone inside
	 * 
	 * @param GeoPoint geo Point of the intersection
	 * @param Ray      inRay
	 * @return Color the final color
	 */
	private Color calcColor(GeoPoint geoPoint, Ray inRay) {
		Color color1 = calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0);
		color1 = color1.add(scene.getAmbientLight().getIntensity());// add the Ambient Light to the final color
		return color1;
	} // remove Ambient Light from the below function

	/**
	 * The function calculates the color at the designated point while calculating
	 * the ambient light, emmission,ext... ip =
	 * ka*ia+ie+kd*|l*n|*il+ks*(max(0,-v*r))^nsh il ∙ si + kr ∙ ir + kt ∙ it
	 * 
	 * @param GeoPoint geoPoint of the intersection
	 * @return Ray inRay
	 * @param int level to calculate the recursion
	 * @param double k to reduce the parameters
	 * @return Color
	 */
	private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
		if (level == 0 || k < MIN_CALC_COLOR_K)// stop condition for the recursion
			return Color.BLACK;

		Color color = geoPoint.geometry.getEmission();// add the emission to the color

		Vector v = geoPoint.point.subtract(scene.getCamera().getP0()).normalize();
		Vector n = geoPoint.geometry.getNormal(geoPoint.point);// normal
// parameters to the material
		int nShininess = geoPoint.geometry.getMaterial().getnShininess();
		double kd = geoPoint.geometry.getMaterial().getKd();
		double ks = geoPoint.geometry.getMaterial().getKs();
		double kr = primitives.Util.alignZero(geoPoint.geometry.getMaterial().getKr());
		double kt = primitives.Util.alignZero(geoPoint.geometry.getMaterial().getKt());

		for (LightSource lightSource : scene.getLights()) {// Goes over all light sources and calculates their effect on
// // the object
			Vector l = lightSource.getL(geoPoint.point);
			if (n.dotProduct(l) * n.dotProduct(v) > 0) { // Checks whether the camera and the light source are on the
// opposite sides
				double ktr = transparency(l, n, geoPoint);// get the Shadow rays
				if (ktr * k > MIN_CALC_COLOR_K) {
					Color lightIntensity = lightSource.getIntensity(geoPoint.point).scale(ktr);// same side relative to
// the object
					color = color.add(calcDiffusive(kd, l, n, lightIntensity),
							calcSpecular(ks, l, n, v, nShininess, lightIntensity));// add to the color the diffusive and
// the spcular
				}
			}
		}
// calculate reflected ray
// check if there are'nt reflected ray
		Color reflectedLight = new Color();
		if (kr > 0) {

			Ray reflectedRay = constructReflectedRay(n, geoPoint.point, inRay);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			if (reflectedPoint != null) {// check if there are'nt reflected point
				reflectedLight = calcColor(reflectedPoint, reflectedRay, level - 1, k * kr).scale(kr);// recursive call
			}
		}
// calculate refracted ray
// check if there are'nt refracted ray
		Color refractedLight = new Color();
		if (kt > 0) {
			Ray refractedRay = constructRefractedRay(n, geoPoint.point, inRay);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			if (refractedPoint != null) {// check if there are'nt refracted point
				refractedLight = calcColor(refractedPoint, refractedRay, level - 1, k * kt).scale(kt);// recursive call
			}
		}
		color = color.add(reflectedLight, refractedLight);// add the reflected light and the refracted light to the
// color
		return color;
	}

	/**
	 * The function produces a rays from the shape towards the light source and
	 * checks whether it cuts in the middle of another shape, and if so, how
	 * transparent it is and gives shade accordingly
	 * 
	 * @param Vector   l from the light to the geometry
	 * @param GeoPoint geoPoint
	 * @return double number between 0,1 Which calculates the amount of shadow
	 *         available for transparent shapes
	 */
	private double transparency(Vector l, Vector n, GeoPoint geoPoint) {
		Vector lightDirection = l.scaling(-1); // from point to light source
		Point3D point = addEpsToPoint3d(n, geoPoint.point, lightDirection);// add epsilon or minus epsilon to
// the point
		Ray lightRay = new Ray(point, lightDirection);// build the shadows rays
		List<GeoPoint> intersections = scene.getModel3D().findIntersections(lightRay);
		double ktr = 1;
		for (GeoPoint gp : intersections)
			ktr *= gp.geometry.getMaterial().getKt();
		return ktr;
	}

	/**
	 * The function is for internal calculation of the spcular light
	 * 
	 * @param        double ks
	 * @param Vector l from the light to the geometry
	 * @param Vector n the normal to the geometry
	 * @param Vector v from the point to the camera
	 * @param        int nShininess
	 * @param Color  lightIntensity
	 * @return Color the spcular color
	 */
	private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
		double ln = l.dotProduct(n);
		Vector r = l.subtract(n.scaling(2 * ln));// the direction of the specular
		double vr = primitives.Util.alignZero(v.scaling(-1).dotProduct(r));
		double vrn = Math.pow(vr, nShininess);
		double ksvr = vr > 0 ? ks * vrn : 0; // the max from (0, -v*r)
		return lightIntensity.scale(ksvr);//
	}

	/**
	 * The function is for internal calculation of the diffusive light
	 * 
	 * @param        double kd
	 * @param Vector l from the light to the geometry
	 * @param Vector n the normal to the geometry
	 * @param Color  lightIntensity
	 * @return Color the diffusive color
	 */
	private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
		double ln = primitives.Util.alignZero(l.dotProduct(n));
		double kdln = kd * (ln > 0 ? ln : -ln);
		return lightIntensity.scale(kdln);// culcDiffusive(kd, l, n, lightIntensity)
	}

	/**
	 * A special private function for calculating the intersection and the nearest
	 * point together
	 * 
	 * @param Ray ray
	 * @return GeoPoint of the closest intersection
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		List<GeoPoint> points = new ArrayList<GeoPoint>();
		points = scene.getModel3D().findIntersections(ray);// list of intersection point
		if (points.isEmpty()) // check if it is empty
			return null;
		else
			return getClosestPoint(points, ray);// call to the getClosestPoint with 2 parameters ( points,ray)
	}

	/**
	 * add epsilon or minus epsilon so that we do not calculate the head point
	 * 
	 * @param n     normal vector
	 * @param point intersection point
	 * @param v     vector
	 * @return Point3D plus eps vector
	 */
	private Point3D addEpsToPoint3d(Vector n, Point3D point, Vector v) {
		Vector epsVector = n.scaling(n.dotProduct(v) > 0 ? EPS : -EPS);// add epsilon or minus epsilon so that we do not
// calculate the head point
		return point.addVector(epsVector);
	}

	/**
	 * A private function for calculating the direction of the new ray of the
	 * reflection As well as for the calculation of the specular
	 * 
	 * @param Vector  n the normal to the geometry
	 * @param Point3D point the point on the geometry
	 * @param Ray     inRay
	 * @return Ray ray that intersect the geomtry
	 */
	private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
		double ln = inRay.getVector().dotProduct(n);
		Vector r = inRay.getVector().subtract(n.scaling(2 * ln)).normalize();// the direction of the reflection
		point = addEpsToPoint3d(n, point, r);
		return new Ray(point, r);
	}

	/**
	 * A private function for calculating the direction of the new ray of the
	 * refraction
	 * 
	 * @param Point3D point
	 * @param Ray     inRay
	 * @return Ray ray that intersect the geomtry
	 */
	private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
		Vector v = inRay.getVector();
		point = addEpsToPoint3d(n, point, v);
		return new Ray(point, v);
	}

	/**
	 * A function that added grid to the image. The user can choose any few pixels
	 * to make the line
	 * 
	 * @param interval
	 */
	public void printGrid(int interval) {
		int nx = imageWriter.getNx();
		int ny = imageWriter.getNy();
		for (int i = 0; i < ny; i++) {
			for (int j = 0; j < nx; j++) {
				if (i % interval == 0 || j % interval == 0) {
					imageWriter.writePixel(i, j, new java.awt.Color(255, 255, 255));
				}
			}
		}
		imageWriter.writeToimage();
	}

	/**
	 * The function receives a list of intersection points and returns the closest
	 * point to the ray point
	 * 
	 * @param intersectionPoints
	 * @param ray
	 * @return GeoPoint the closest point
	 */
	private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints, Ray ray) {
		GeoPoint closestPoint = intersectionPoints.get(0);
		for (GeoPoint geoPoint : intersectionPoints) {
			// checks which point is closer to the ray point
			if (ray.getPoint().distance2(geoPoint.point) < ray.getPoint().distance2(closestPoint.point)) {
				closestPoint = geoPoint;
			}
		}
		return closestPoint;
	}

	/**
	 * Adaptive super sampling shell function, the function creates a matrix and
	 * initializes it in black color, and then sends to the continuation function
	 * 
	 * @param pc The center of the pixel
	 * @param rx The length of the pixel
	 * @param ry The width of the pixel
	 * @return A matrix with all the colors of the pixel
	 */
	private Color[][] adaptiveSupersampling(Point3D pc, double rx, double ry) {
		List<GeoRay> adptRays = new ArrayList<>();
		Color[][] matrix = new Color[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				matrix[i][j] = new Color();
			}
		}
		adaptiveSupersampling(adptRays, matrix, pc, rx, ry, MAX_LEVEL, 0, 8, 0, 8);
		return matrix;
	}

	/**
	 * 
	 * @param adptRays A list of rays sent from the pixel
	 * @param matrix   Matrix with the colors of the pixel
	 * @param pc       The center of the pixel
	 * @param rx       The length of the pixel
	 * @param ry       The width of the pixel
	 * @param level    the level of the recursion
	 * @param minRaw
	 * @param maxRaw
	 * @param minCol
	 * @param maxCol
	 */
	private void adaptiveSupersampling(List<GeoRay> adptRays, Color[][] matrix, Point3D pc, double rx, double ry,
			int level, int minRaw, int maxRaw, int minCol, int maxCol) {

		Camera camera = scene.getCamera();
		Vector vup = camera.getvUp();
		Vector vright = camera.getvRight();
		Point3D p0 = camera.getP0();
		boolean flag = false;

		Point3D point = pc;
		point = point.addVector(vright.scaling(-rx / 2));
		point = point.addVector(vup.scaling(-ry / 2));

		List<Point3D> checkPoints = new ArrayList<>();// list for 4 new points
		List<GeoRay> corners = new ArrayList<>();// list for 4 corners

		// Initialize the four corners
		checkPoints.add(point);
		checkPoints.add(point = point.addVector(vup.scaling(ry)));
		checkPoints.add(point = point.addVector(vright.scaling(rx)));
		checkPoints.add(point = point.addVector(vup.scaling(-ry)));

		for (Point3D p : checkPoints) {// get the color of each ray
			for (int i = 0; i < adptRays.size(); i++) {
				if (p.equals(adptRays.get(i).getRay().getPoint())) {// If the fund already exists
					corners.add(adptRays.get(i));
					i = adptRays.size();
					flag = true;
				}
			}
			if (!flag) {// If the fund does not exist then create it
				GeoRay geoRay = new GeoRay(new Ray(p, p.subtract(p0)));
				geoRay.setColor(caclRay(geoRay.getRay()));
				corners.add(geoRay);
				adptRays.add(geoRay);
			}
			flag = false;
		}
		// Add the corners to the matrix
		matrix[maxRaw][minCol] = corners.get(0).getColor();
		matrix[minRaw][minCol] = corners.get(1).getColor();
		matrix[minRaw][maxCol] = corners.get(2).getColor();
		matrix[maxRaw][maxCol] = corners.get(3).getColor();

		// If we are in the final stage then we should not check whether the corners are
		// equal
		if (level == 1) {
			return;
		}

		// Checks if the corners are equal and if so paints the matrix
		if (equalsColor(matrix[maxRaw][minCol], matrix[minRaw][minCol])
				&& equalsColor(matrix[maxRaw][minCol], matrix[minRaw][maxCol])
				&& equalsColor(matrix[maxRaw][minCol], matrix[maxRaw][maxCol])) {

			for (int i = minRaw; i <= maxRaw; i++) {
				for (int j = minCol; j <= maxCol; j++) {
					if (equalsColor(matrix[i][j], new Color())) {
						matrix[i][j] = matrix[maxRaw][minCol];
					}
				}
			}
		} else {
			// If the corners are not equal sends in the function the four
			// centers of the pixel
			List<Point3D> points = moveToTheMiddles(pc, rx, ry, vup, vright);
			adaptiveSupersampling(adptRays, matrix, points.get(0), rx / 2, ry / 2, level - 1, (maxRaw + minRaw) / 2,
					maxRaw, minCol, maxCol / 2);
			adaptiveSupersampling(adptRays, matrix, points.get(1), rx / 2, ry / 2, level - 1, minRaw, maxRaw / 2,
					minCol, maxCol / 2);
			adaptiveSupersampling(adptRays, matrix, points.get(2), rx / 2, ry / 2, level - 1, minRaw, maxRaw / 2,
					(maxCol + minCol) / 2, maxCol);
			adaptiveSupersampling(adptRays, matrix, points.get(3), rx / 2, ry / 2, level - 1, (maxRaw + minRaw) / 2,
					maxRaw, (maxCol + minCol) / 2, maxCol);
		}
	}

	/**
	 * A function that checks whether the two colors are equal
	 * 
	 * @param a Color
	 * @param b Color
	 * @return True or false if the colors are equal
	 */
	private boolean equalsColor(Color a, Color b) {
		double ar = a.getRed(), ag = a.getGreen(), ab = a.getBlue();
		double br = b.getRed(), bg = b.getGreen(), bb = b.getBlue();
		if (Math.abs(ar - br) < 50 && Math.abs(ag - bg) < 50 && Math.abs(ab - bb) < 50) {
			return true;
		}
		return false;
	}

	/**
	 * A function that receives the center of the pixel and divides it into four
	 * parts and returns the four new centers
	 * 
	 * @param p      The center of the pixel
	 * @param rx     The length of the pixel
	 * @param ry     The width of the pixel
	 * @param vup    up vector of camera
	 * @param vright right vector of camera
	 * @return
	 */
	private List<Point3D> moveToTheMiddles(Point3D p, double rx, double ry, Vector vup, Vector vright) {
		List<Point3D> middlePoints = new ArrayList<>();
		
		//Moved to the middle of the four squares
		Point3D point3d = p.addVector(vup.scaling(-ry / 4));
		point3d = point3d.addVector(vright.scaling(-rx / 4));
		middlePoints.add(point3d);
		point3d = p.addVector(vup.scaling(ry / 4));
		point3d = point3d.addVector(vright.scaling(-rx / 4));
		middlePoints.add(point3d);
		point3d = p.addVector(vup.scaling(ry / 4));
		point3d = point3d.addVector(vright.scaling(rx / 4));
		middlePoints.add(point3d);
		point3d = p.addVector(vup.scaling(-ry / 4));
		point3d = point3d.addVector(vright.scaling(rx / 4));
		middlePoints.add(point3d);

		return middlePoints;
	}

	/**
	 * The function receives a beam. It checks the point of intersection closest to
	 * the camera and returns its color
	 * 
	 * @param ray
	 * @return New Color
	 */
	public Color caclRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		if (closestPoint == null)// check if there arn't intersection point
			return scene.getBackground();
		else
			return calcColor(closestPoint, ray);

	}

	/**
	 * The function executes supersampling,The function sends 81 rays
	 * through the pixel
	 * 
	 * @param pixel  The center of the pixel
	 * @param rx    The length of the pixel
	 * @param ry    The width of the pixel
	 * @return  A list of the rays that passed through the pixel
	 */
	private List<Ray> supersampling(Point3D pixel, double rx, double ry) {
		Vector vright = scene.getCamera().getvRight();
		Vector vup = scene.getCamera().getvUp();
		Point3D p0 = scene.getCamera().getP0();

		List<Ray> rays = new ArrayList<>();// list for return beams of rays
		pixel = pixel.addVector(vright.scaling(-rx / 2));
		pixel = pixel.addVector(vup.scaling(-ry / 2));

		for (int k = 0; k < 9; k++) {
			for (int k2 = 0; k2 < 9; k2++) {
				rays.add(new Ray(pixel, pixel.subtract(p0)));//add a new ray
				pixel = pixel.addVector(vup.scaling(ry / 8));
			}
			pixel = pixel.addVector(vup.scaling(-ry * 1.125));//Moves the columns to the beginning
			pixel = pixel.addVector(vright.scaling(rx / 8));//Moved to next row
		}
		return rays;
	}
}
