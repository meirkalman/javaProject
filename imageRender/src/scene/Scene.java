package scene;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;
import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.*;
import primitives.Color;

/**
 * In this class there is information about the scene being built, there is the
 * scene name, background ambientLight all the shapes camera and the distance
 * from the camera.
 * 
 * @author kalman & ravitz
 *
 */
public class Scene {
	String sceneName;
	Color background;
	AmbientLight ambientLight;
	List<LightSource> lights;

	Geometries model3D;
	Camera camera;
	double distance;
	boolean adaptiveSupersampling;
	boolean dof;
	boolean supersampling;
	boolean multiThread;
	boolean justPicture;
	
	
	/**
	 * constructor
	 * 
	 * @param String name
	 */
	public Scene(String name) {
		sceneName = name;
		model3D = new Geometries();
		lights = new ArrayList<LightSource>();
	}
	/**
	 * constructor
	 * 
	 * @param String name
	 */
	public Scene(String name,boolean ad, boolean dof1,boolean sup) {
		sceneName = name;
		model3D = new Geometries();
		lights = new ArrayList<LightSource>();
		adaptiveSupersampling = ad;
		dof = dof1;
		supersampling = sup;
	}
	
	/**
	 * isAdaptive
	 * @return true or false if is adaptive
	 */
	public boolean isAdaptive() {
		return adaptiveSupersampling;
	}

	public void setAdaptive(boolean adaptive) {
		this.adaptiveSupersampling = adaptive;
	}
	
	/**
	 * isDof
	 *  
	 * @return true or false if is Dof
	 */
	public boolean isDof() {
		return dof;
	}

	/**
	 * set dof
	 * @param dof
	 */
	public void setDof(boolean dof) {
		this.dof = dof;
	}
	
	/**
	 * is supersampling
	 * 
	 * @return true or false if is supersampling
	 */
	public boolean isSupersampling() {
		return supersampling;
	}
	
	/**
	 * set supersampling
	 * @param supersampling
	 */
	public void setSupersampling(boolean supersampling) {
		this.supersampling = supersampling;
	}
	
	/**
	 * isMultiThread
	 * 
	 * @return true or false if is multiThread
	 */
	public boolean isMultiThread() {
		return multiThread;
	}
	
	/**
	 * set multiThread
	 * @param multiThread
	 */
	public void setMultiThread(boolean multiThread) {
		this.multiThread = multiThread;
	}
	
	/**
	 * is justPicture
	 * @return true or false if is just picture
	 */
	public boolean isJustPicture() {
		return justPicture;
	}
	
	/**
	 * set just picture
	 * @param justPicture
	 */
	public void setJustPicture(boolean justPicture) {
		this.justPicture = justPicture;
	}
	/**
	 * return the scene name
	 * 
	 * @return String
	 */
	public String getSceneName() {
		return sceneName;
	}

	/**
	 * return the background
	 * 
	 * @return Color
	 */
	public Color getBackground() {
		return background;
	}

	/**
	 * return the ambient light
	 * 
	 * @return AmbientLight
	 */
	public AmbientLight getAmbientLight() {
		return ambientLight;
	}

	/**
	 * return the Geometries
	 * 
	 * @return Geometries
	 */
	public Geometries getModel3D() {
		return model3D;
	}

	/**
	 * return the camera
	 * 
	 * @return Camera
	 */
	public Camera getCamera() {
		return camera;
	}

	/**
	 * return the List<LightSource>
	 * 
	 * @return List<LightSource>
	 */
	public List<LightSource> getLights() {
		return lights;
	}

	/**
	 * add light source
	 * 
	 * @param List<LightSource> a
	 */
	public void addLightSource(List<LightSource> a) {
		lights = a;
	}

	/**
	 * return the distance from the camera
	 * 
	 * @return double
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * set the background
	 * 
	 * @param a
	 */
	public void setBackground(Color a) {
		background = a;
	}

	/**
	 * set the ambient light
	 * 
	 * @param a
	 */
	public void setAmbientLight(AmbientLight a) {
		ambientLight = a;
	}

	/**
	 * set the camera
	 * 
	 * @param a
	 */
	public void setCamera(Camera a) {
		camera = a;
	}

	/**
	 * set the distance
	 * 
	 * @param b
	 */
	public void setDistance(double b) {
		distance = b;
	}

	/**
	 * set geometries
	 * 
	 * @param a
	 */
	public void setGeometries(Intersectable a) {
		model3D.add(a);
	}

}
