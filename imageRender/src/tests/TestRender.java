package tests;

import static java.lang.StrictMath.sqrt;
import static org.junit.Assert.assertEquals;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.*;
import elements.Camera;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * test the class Render In the next tests we will make scenes and cameras and
 * build pictures and check that the pictures are going out as expected
 * 
 * @author kalman & ravitz
 *
 */
public class TestRender {
	@Test
	public void basicRendering2() {

		Scene scene = new Scene("scene with sphere && triangles");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, -1)));
		scene.setDistance(100);
		scene.setAmbientLight(new AmbientLight(new Color(10, 10, 10), 0.10));
		scene.setBackground(Color.BLACK);
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);

		geometries.add(new Triangle(new Color(15, 15, 15), new Point3D(3500, 3500, -2000),
				new Point3D(-3500, -3500, -1000), new Point3D(3500, -3500, -2000), new Material(3, 2, 100)));
		geometries.add(new Triangle(new Color(15, 15, 15), new Point3D(3500, 3500, -2000),
				new Point3D(-3500, 3500, -1000), new Point3D(-3500, -3500, -1000), new Material(3, 2, 100)));
		geometries
				.add(new Sphere(new Color(0, 0, 100), 550, new Point3D(200, -200, -1000), new Material(1.1, 1.5, 25)));
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		PointLight PointLight = new PointLight(new Color(255, 100, 100), new Point3D(300, -200, -150), 0.000001,
				0.000001);
		SpotLight spotLight = new SpotLight(new Color(255, 100, 100), new Point3D(200, -200, -300), 0.0001, 0.000000001,
				new Vector(-200, 0, -700), 1);
		DirectionalLight directionalLight = new DirectionalLight(new Color(255, 255, 255), new Vector(-10, 5, 1));
// temoLightSources.add(directionalLight);
// temoLightSources.add(spotLight);
		temoLightSources.add(PointLight);
		scene.addLightSource(temoLightSources);
		ImageWriter imageWriter = new ImageWriter("משולשים וכדור בסיס 1.1444", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);
		render.renderImage();
		imageWriter.writeToimage();
	}

	/**
	 * The test of two triangles is slightly tilted in relation to the camera and
	 * there is point light on them
	 */
	@Test
	public void basicRendering3() {

		Scene scene = new Scene("Test scene1");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(100);
// add the background to the scene
		scene.setBackground(new Color());
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
// Makes the material of the triangles
		Material material = new Material(0.8, 15, 10);
// Makes the tow triangles
		geometries.add(new Triangle(new Color(), new Point3D(-50, -50, 30), new Point3D(-50, 50, 20),
				new Point3D(50, 50, 20), material));
		geometries.add(new Triangle(new Color(), new Point3D(-50, -50, 30), new Point3D(50, -50, 30),
				new Point3D(50, 50, 20), material));
// geometries.add(new Sphere(new Color(java.awt.Color.blue), 1, new Point3D(0,
// 0, 10), material));

		ImageWriter imageWriter = new ImageWriter("testPointLightBasic 1.31", 500, 500, 500, 500);
		PointLight pLight = new PointLight(new Color(100, 20, 20), new Point3D(0, 50, -150), 0.0001, 0.00002);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(pLight);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.5);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this test, two triangles are made and spot light is applied to them
	 */
	@Test
	public void basicRendering4() {

		Scene scene = new Scene("Test scene1");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(100);
// add the background to the scene
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(0.8, 20, 10);
		geometries.add(new Triangle(new Color(java.awt.Color.black), new Point3D(-50, -50, 30),
				new Point3D(-50, 50, 20), new Point3D(50, 50, 20), material));
		geometries.add(new Triangle(new Color(java.awt.Color.black), new Point3D(-50, -50, 30),
				new Point3D(50, -50, 30), new Point3D(50, 50, 20), material));

		ImageWriter imageWriter = new ImageWriter("testSpotLightBasic211444", 500, 500, 500, 500);
		SpotLight pLight = new SpotLight(new Color(60, 15, 15), new Point3D(10, 0, -100), 0.0001, 0.00002,
				new Vector(0, 1, 1), 1);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(pLight);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.5);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this test, a ball is made and spot light is applied to it
	 */
	@Test
	public void basicRendering5() {

		Scene scene = new Scene("Test scene1");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(700);
// add the background to the scene
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(5, 2, 20);
		geometries.add(new Sphere(new Color(0, 0, 150), 150, new Point3D(0, 0, 1000), material));
		geometries.add(new Triangle(new Color(0, 0, 150), new Point3D(180, 190, 890), new Point3D(180, 90, 890),
				new Point3D(90, 190, 890), material));

		ImageWriter imageWriter = new ImageWriter("testSpotLightSphere288444", 500, 500, 500, 500);
		PointLight pLight = new PointLight(new Color(100, 40, 40), new Point3D(170, 190, 820), 0.0001, 0.00002);
// SpotLight pLight2 = new SpotLight(new Color(60, 15, 15), new Point3D(-50, 50,
// -50), 0.0001, 0.00002,
// new Vector(1, -1, 1), 2);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(pLight);
// temoLightSources.add(pLight2);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.01);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this test, a ball is made and spot light and point light is applied to it
	 */
	@Test
	public void basicRendering6() {

		Scene scene = new Scene("Test scene1");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(100);
// add the background to the scene
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(2, 7, 10);
		geometries.add(new Sphere(new Color(java.awt.Color.blue), 20, new Point3D(0, 0, 30), material));

		ImageWriter imageWriter = new ImageWriter("testSpotpointLightSphere1111111111111444", 500, 500, 500, 500);
		PointLight pLight = new PointLight(new Color(100, 20, 20), new Point3D(50, 50, -50), 0.0001, 0.00002);
		SpotLight pLight1 = new SpotLight(new Color(100, 20, 20), new Point3D(0, 0, 100), 1, 10, new Vector(0, 0, -1),
				1);

		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(pLight);
		temoLightSources.add(pLight1);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.5);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this examination a house was built and directional light and point light
	 * was made on it
	 */
	@Test
	public void basicRendering7() {

		Scene scene = new Scene("Test scene1");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(100);
// add the background to the scene
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(2, 7, 25);
		geometries.add(new Triangle(new Color(150, 75, 0), new Point3D(-22, -17, 20), new Point3D(-22, 20, 20),
				new Point3D(22, 20, 20), material));
		geometries.add(new Triangle(new Color(150, 75, 0), new Point3D(-22, -17, 20), new Point3D(22, -17, 20),
				new Point3D(22, 20, 20), material));
		geometries.add(new Triangle(new Color(150, 0, 0), new Point3D(-22, 20, 20), new Point3D(22, 20, 20),
				new Point3D(0, 40, 20), material));

		ImageWriter imageWriter = new ImageWriter("testSpotpointLightSphere2444", 500, 500, 500, 500);
		PointLight pLight = new PointLight(new Color(100, 20, 20), new Point3D(50, 50, -50), 0.0001, 0.00002);
		DirectionalLight pLight2 = new DirectionalLight(new Color(20, 20, 0), new Vector(0, 0, 1));

		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(pLight);
		temoLightSources.add(pLight2);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.5);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this test three walls were made and point light was applied to it
	 */
	@Test
	public void basicRendering8() {

		Scene scene = new Scene("Test scene1");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 50, 0), new Vector(-1, 0, 0), new Vector(0, -1, 0)));
		scene.setDistance(150);
// add the background to the scene
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(2, 7, 25);
		geometries.add(new Triangle(new Color(20, 20, 20), new Point3D(50, -50, 50), new Point3D(50, -50, -50),
				new Point3D(-50, -50, 50), material));
		geometries.add(new Triangle(new Color(20, 20, 20), new Point3D(-50, -50, -50), new Point3D(50, -50, -50),
				new Point3D(-50, -50, 50), material));
		geometries.add(new Triangle(new Color(20, 20, 20), new Point3D(50, -50, 50), new Point3D(50, -50, -50),
				new Point3D(80, 10, 40), material));
		geometries.add(new Triangle(new Color(20, 20, 20), new Point3D(70, 10, -50), new Point3D(50, -50, -50),
				new Point3D(80, 10, 40), material));
		geometries.add(new Triangle(new Color(20, 20, 20), new Point3D(-50, -50, 50), new Point3D(-50, -50, -50),
				new Point3D(-70, 10, 40), material));
		geometries.add(new Triangle(new Color(20, 20, 20), new Point3D(-80, 10, -50), new Point3D(-50, -50, -50),
				new Point3D(-70, 10, 40), material));

		ImageWriter imageWriter = new ImageWriter("my room 1.0444", 500, 500, 500, 500);
		PointLight pLight = new PointLight(new Color(100, 20, 20), new Point3D(0, 0, 0), 0.0001, 0.00002);
// DirectionalLight pLight2 =new DirectionalLight(new Color(20,20,0), new
// Vector(0, 0, 1));

		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(pLight);
// temoLightSources.add(pLight2);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.5);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this test we will create a ball inside a ball, the inner ball is red and
	 * the outer is blue
	 */
	@Test
	public void basicRendering10() {

		Scene scene = new Scene("Test spere whitin spere");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(500);
// add the background to the scene
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(0.8, 0.9, 70, 0.3, 0.3);
		geometries.add(new Sphere(new Color(0, 0, 70), 50, new Point3D(0, 0, 200), material));
		geometries.add(new Sphere(new Color(70, 0, 0), 20, new Point3D(0, 0, 200), material));

// geometries.add(new Triangle(new Color(0,0,150), new Point3D(180, 190,
// 890),new Point3D(180, 90, 890), new Point3D(90, 190, 890), material));

		ImageWriter imageWriter = new ImageWriter("spere whitin spere444", 500, 500, 500, 500);
		PointLight pLight = new PointLight(new Color(200, 80, 80), new Point3D(50, 50, 20), 0.0001, 0.00002);
// SpotLight pLight2 = new SpotLight(new Color(60, 15, 15), new Point3D(-50, 50,
// -50), 0.0001, 0.00002,
// new Vector(1, -1, 1), 2);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(pLight);
// temoLightSources.add(pLight2);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.1);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this test we will create a sphere and a surface and the light creates a
	 * shadow on the surface
	 */
	@Test
	public void basicShadowRender() {

		Scene scene = new Scene("Test shadow");
		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, 0, -1), new Vector(0, -1, 0)));
		scene.setDistance(100);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientLight(new AmbientLight(new Color(30, 30, 30), 0.5));
		Geometries geometries = new Geometries();
		geometries.add(
				new Sphere(new Color(java.awt.Color.blue), 5, new Point3D(0, -10, 0), new Material(0.9, 0.8, 100)));
		geometries.add(new Triangle(new Color(java.awt.Color.blue), new Point3D(35, 90, -20), new Point3D(20, 90, -20),
				new Point3D(20, 90, -10), new Material(0.9, 0.8, 100)));

		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(-250, -250, 120), new Point3D(-250, 250, 120),
				new Point3D(250, -250, 120), new Material(0.9, 0.8, 100)));
		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(250, 250, 120), new Point3D(-250, 250, 120),
				new Point3D(250, -250, 120), new Material(0.9, 0.8, 100)));
		scene.setGeometries(geometries);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(new PointLight(new Color(255, 150, 150), new Point3D(30, 132, -20), 0, 0));
		scene.addLightSource(temoLightSources);
		ImageWriter imageWriter = new ImageWriter("basicShadow3444", 500, 500, 500, 500);
		Render testRender = new Render(imageWriter, scene);

		testRender.renderImage();
		imageWriter.writeToimage();

	}

	/**
	 * temp test
	 */
	@Test
	public void basicRendering11() {
		Scene scene = new Scene("Test shadow");
		scene.setCamera(new Camera(new Point3D(0, 1000, 0), new Vector(1, 0, 0), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientLight(new AmbientLight(new Color(5, 5, 5), 10));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(new Color(java.awt.Color.blue), 50, new Point3D(0, -15, 0),
				new Material(1.2, 1, 100, 0.5, 0.3)));
		geometries.add(new Sphere(new Color(java.awt.Color.red), 25, new Point3D(0, -15, 0),
				new Material(1, 1, 100, 0.5, 0.5)));

		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(-300, -500, -300), new Point3D(300, -500, -300),
				new Point3D(-300, -500, 300), new Material(0.5, 0.5, 100, 0.5, 0.5)));
		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(110, 0, 110), new Point3D(300, -500, -300),
				new Point3D(-300, -500, 300), new Material(0.5, 0.5, 100, 0.5, 0.5)));

		scene.setGeometries(geometries);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(new PointLight(new Color(80, 50, 50), new Point3D(-100, 500, -200), 0, 0));
		scene.addLightSource(temoLightSources);
		ImageWriter imageWriter = new ImageWriter("mirror353", 500, 500, 500, 500);
		Render testRender = new Render(imageWriter, scene);

		testRender.renderImage();
		imageWriter.writeToimage();
	}

	@Test
	public void basicRendering16() {
		Scene scene = new Scene("Test shadow");
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		cam.setAperture(50);
		cam.setDistancePlain(1000);
		cam.setNumOfRays(15);
		scene.setCamera(cam);
		scene.setDistance(100);

		scene.setBackground(new Color());
		scene.setAmbientLight(new AmbientLight(new Color(5, 5, 5), 1));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(new Color(java.awt.Color.blue), 700, new Point3D(0, -1210, 0),
				new Material(2, 2, 20, 0.2, 0.5)));

		scene.setGeometries(geometries);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(new PointLight(new Color(255, 100, 100), new Point3D(400, 0, -200), 0, 0));
		scene.addLightSource(temoLightSources);
		ImageWriter imageWriter = new ImageWriter("עם צמצם 501", 500, 500, 500, 500);
		Render testRender = new Render(imageWriter, scene);

		testRender.renderImage();
		imageWriter.writeToimage();
	}

	/**
	 * temp test
	 */
	@Test
	public void basicRendering15() {
		Scene scene = new Scene("Test scene2");
// add the camera to the scene
		scene.setCamera(new Camera(new Point3D(0, 70, 30), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(150);
// add the background to the scene
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(0.8, 0.9, 80, 0.1, 0.9);

		geometries.add(new Triangle(new Color(20, 10, 10), new Point3D(0.8, 25, 50), new Point3D(0.8, 25, 40),
				new Point3D(-0.8, 25, 50), new Material(0.8, 0.9, 80, 0.2, 0.3)));
		geometries.add(new Triangle(new Color(20, 10, 10), new Point3D(-0.8, 25, 40), new Point3D(0.8, 25, 40),
				new Point3D(-0.8, 25, 50), new Material(0.8, 0.9, 80, 0.2, 0.3)));

		geometries.add(new Triangle(new Color(10, 50, 10), new Point3D(50, 0, 0), new Point3D(50, 51, 0),
				new Point3D(-51, 0, 0), new Material(0.2, 0.1, 80, 0.1, 0.6)));
		geometries.add(new Triangle(new Color(10, 50, 10), new Point3D(-50, 50, 0), new Point3D(50, 50, 0),
				new Point3D(-50, 0, 0), new Material(0.2, 0.1, 80, 0.1, 0.6)));
		// wall
		geometries.add(new Triangle(new Color(10, 10, 50), new Point3D(30, -1, 0), new Point3D(30, 0, 51),
				new Point3D(30, 50, 0), new Material(0.1, 0.1, 80, 1, 0)));
		geometries.add(new Triangle(new Color(10, 10, 50), new Point3D(30, 50, 0), new Point3D(30, 0, 50),
				new Point3D(30, 50, 50), new Material(0.1, 0.1, 80, 1, 0)));
		// wall
		geometries.add(new Triangle(new Color(50, 50, 10), new Point3D(-30, -1, 0), new Point3D(-30, 50, 0),
				new Point3D(-30, 0, 51), new Material(0.1, 0.1, 80, 1, 0)));
		geometries.add(new Triangle(new Color(50, 50, 10), new Point3D(-30, 0, 50), new Point3D(-30, 50, 50),
				new Point3D(-30, 50, 0), new Material(0.1, 0.1, 80, 1, 0)));

		geometries.add(new Triangle(new Color(10, 50, 50), new Point3D(50, 0, 50), new Point3D(-50, 0, 50),
				new Point3D(-50, 50, 50), new Material(0.1, 0.1, 80, 0.1, 0.7)));
		geometries.add(new Triangle(new Color(10, 50, 50), new Point3D(50, 0, 50), new Point3D(50, 50, 50),
				new Point3D(-50, 50, 50), new Material(0.1, 0.1, 80, 0.1, 0.7)));

		geometries.add(new Triangle(new Color(50, 10, 50), new Point3D(50, 0, 0), new Point3D(50, 0, 50),
				new Point3D(-50, 0, 0), new Material(0.1, 0.1, 80, 0.1, 0.7)));
		geometries.add(new Triangle(new Color(50, 10, 50), new Point3D(-50, 0, 0), new Point3D(-50, 0, 50),
				new Point3D(50, 0, 50), new Material(0.1, 0.1, 80, 0.1, 0.7)));

		geometries.add(new Sphere(new Color(0, 0, 25), 5, new Point3D(0, 25, 10), new Material(0.5, 0.2, 80, 0, 0.3)));
		geometries.add(
				new Sphere(new Color(0, 25, 0), 3.5, new Point3D(4.8, 25, 14.8), new Material(0.5, 0.2, 80, 0, 0.3)));
		geometries.add(
				new Sphere(new Color(25, 0, 0), 3.5, new Point3D(-4.8, 25, 14.8), new Material(0.5, 0.2, 80, 0, 0.3)));
		geometries
				.add(new Sphere(new Color(20, 20, 20), 3, new Point3D(0, 25, 37), new Material(0.5, 0.2, 80, 0, 0.3)));

		ImageWriter imageWriter = new ImageWriter("my room 2.232", 500, 500, 500, 500);
		SpotLight pLight = new SpotLight(new Color(85, 80, 80), new Point3D(0, 25, 10), 0.0001, 0.00002,
				new Vector(0, 0, -1), 1);
		PointLight pLight2 = new PointLight(new Color(200, 200, 200), new Point3D(11, -11, 25), 0.0001, 0.00002);
		SpotLight spotLight = new SpotLight(new Color(200, 200, 200), new Point3D(0, 25, 37), 0, 0,
				new Vector(0, 0, -1), 1);
		PointLight pLight3 = new PointLight(new Color(150, 150, 150), new Point3D(0, 25, 37), 0, 0);

		List<LightSource> temoLightSources = new ArrayList<LightSource>();
// temoLightSources.add(pLight);
		temoLightSources.add(spotLight);
//temoLightSources.add(pLight3);
		scene.addLightSource(temoLightSources);
// add the image writer and scene to the render
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.5);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * temp test
	 */
	@Test
	public void basicRendering13() {

		Scene scene = new Scene("image");
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(100, 100, 149), new Point3D(100, -100, 70),
				new Point3D(-100, -100, 149), new Material(0.3, 0.3, 50, 0.8, 0)));
		geometries.add(new Triangle(new Color(0, 0, 0), new Point3D(100, 100, 149), new Point3D(-100, 100, 150),
				new Point3D(-100, -100, 149), new Material(0.1, 0.1, 100, 0, 1)));
		geometries.add(
				new Sphere(new Color(0, 0, 200), 20, new Point3D(-50, 40, 70), new Material(0.1, 0.1, 120, 0, 0.3)));
		geometries.add(
				new Sphere(new Color(200, 0, 0), 5, new Point3D(-50, 40, 70), new Material(0.1, 0.1, 120, 0, 0.3)));
		AmbientLight a = new AmbientLight(new primitives.Color(0, 0, 0), 3);
		scene.setAmbientLight(a);

		scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistance(200);

		SpotLight pLight3 = new SpotLight(new Color(500, 0, 0), new Point3D(-50, 0, 70), 0.000001, 0.0000005,
				new Vector(0, 1, 0), 15);
		PointLight pLight4 = new PointLight(new Color(500, 700, 500), new Point3D(0, 0, 0), 0.000001, 0.000005);

		List<LightSource> temoLightSources = new ArrayList<LightSource>();

		temoLightSources.add(pLight3);
		temoLightSources.add(pLight4);
		scene.addLightSource(temoLightSources);

		ImageWriter imageWriter = new ImageWriter(" uuuuuuu", 500, 500, 500, 500);

		Render render = new Render(imageWriter, scene);

// add the ambient light to the scene

// Builds the image
		render.renderImage();
// render.printGrid(50);
		imageWriter.writeToimage();
	}

	/**
	 * In this test we will create a ball inside a ball and two mirrors that reflect
	 * the reflection of the two balls
	 */
	@Test
	public void test9() {

		Scene scene = new Scene("test 8");
		scene.setCamera(new Camera(new Point3D(0, 0, -10), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
		scene.setDistance(100);
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);

		geometries.add(
				new Sphere(new Color(0, 10, 100), 16, new Point3D(55, 55, 34), new Material(0.1, 0.05, 100, 0, 0.8)));
		geometries
				.add(new Sphere(new Color(160, 10, 10), 8, new Point3D(55, 55, 34), new Material(0.4, 0.6, 100, 0, 0)));
		geometries.add(new Triangle(new Color(Color.BLACK), new Point3D(125, -125, 50), new Point3D(-80, -80, -700),
				new Point3D(-100, 100, 40), new Material(0, 0.8, 100, 1, 0)));
		geometries.add(new Triangle(new Color(Color.BLACK), new Point3D(125, -125, 50), new Point3D(-125, 125, 50),
				new Point3D(100, 100, 40), new Material(0, 0.8, 100, 1, 0)));
		ImageWriter imageWriter = new ImageWriter("Test 9511", 500, 500, 500, 500);
		SpotLight spotLight = new SpotLight(new Color(200, 200, 10), new Point3D(100, 130, -400), 0.0001, 0.00002,
				new Vector(1, 1, 40), 1);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();

		temoLightSources.add(spotLight);
		scene.addLightSource(temoLightSources);
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.5);
		scene.setAmbientLight(a);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		imageWriter.writeToimage();

	}

	/**
	* In this test we will create a snake of bullets and a plain of focus found in
	* the middle of the snake
	*/
	@Test
	public void dof_4() {
	Scene scene = new Scene("Test shadow");
	Camera cam = new Camera(new Point3D(0, 1300, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
	cam.setAperture(120);
	cam.setDistancePlain(6000);

	cam.setNumOfRays(20);
	scene.setCamera(cam);
	scene.setDistance(500);
	scene.setAdaptive(true);
	scene.setSupersampling(false);
	scene.setDof(false);
	scene.setMultiThread(true);
	scene.setJustPicture(false);
	scene.setBackground(new Color(200, 200, 200));
	scene.setAmbientLight(new AmbientLight(new Color(5, 5, 5), 10));
	Geometries geometries = new Geometries();
	geometries.add(new Sphere(new Color(0, 0, 255), 500, new Point3D(-1000, -1110, -900),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(30, 0, 230), 500, new Point3D(-600, -2000, -1050),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(60, 0, 200), 500, new Point3D(-200, -3100, -1200),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(90, 0, 190), 500, new Point3D(350, -4200, -1250),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(120, 0, 160), 500, new Point3D(990, -5300, -1200),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(150, 0, 130), 500, new Point3D(1700, -6400, -1000),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(180, 0, 100), 500, new Point3D(2500, -7500, -650),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(210, 0, 70), 500, new Point3D(3200, -8600, -250),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(240, 0, 40), 500, new Point3D(3900, -9700, 300),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(255, 0, 10), 500, new Point3D(4500, -10800, 900),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(240, 30, 0), 500, new Point3D(4900, -12000, 1650),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(220, 60, 0), 500, new Point3D(5000, -13100, 2450),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(190, 90, 0), 500, new Point3D(5000, -14200, 3200),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(160, 120, 0), 500, new Point3D(4900, -15300, 3800),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(130, 150, 0), 500, new Point3D(4700, -16500, 4400),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(70, 180, 0), 500, new Point3D(4400, -17700, 4700),
	new Material(0.5, 0.5, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(40, 210, 0), 500, new Point3D(4500, -21000, 5200),
	new Material(0.4, 0.4, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(10, 240, 0), 500, new Point3D(5200, -27000, 5900),
	new Material(0.4, 0.4, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(40, 255, 40), 500, new Point3D(7500, -38000, 7300),
	new Material(0.1, 0.1, 100, 0, 0.2)));

	geometries.add(new Sphere(new Color(70, 230, 70), 500, new Point3D(10000, -48000, 8600),
	new Material(0.1, 0.1, 100, 0, 0.1)));



	for (int i = 0; i < 10; i++) {
	for (int j = 0; j < 10; j++) {
	double randomR = Math.random() * 150;
	double randomG = Math.random() * 150;
	double randomB = Math.random() * 150;
	geometries.add(new Sphere(new Color(randomR, randomG, randomB), 2000, new Point3D(j*4500-19000, -49000, i*4500-19000),
	new Material(0.1, 0.1, 10, 0.6, 0)));
	}
	}

	scene.setGeometries(geometries);
	List<LightSource> temoLightSources = new ArrayList<LightSource>();
	//temoLightSources.add(new PointLight(new Color(150, 150, 150), new Point3D(-300, 0, -300), 0, 0));
	//temoLightSources.add(new PointLight(new Color(150, 150, 150), new Point3D(-1300, 0, 1500), 0, 0));

	scene.addLightSource(temoLightSources);
	ImageWriter imageWriter = new ImageWriter("Test222adaptive", 500, 500, 500, 500);
	Render testRender = new Render(imageWriter, scene);

	testRender.renderImage();
	imageWriter.writeToimage();
	}

	
	/**
	 * In this test we will create three balls at different distances and a focus
	 * plane on the nearest ball
	 */
	@Test
	public void dof_1() {

		Scene scene = new Scene("Test dof1.02");
		Camera cam = new Camera(new Point3D(0, -200, -705), new Vector(0, -1, 0), new Vector(0, 0, 1));
		cam.setAperture(50);
		cam.setDistancePlain(80);
		cam.setNumOfRays(20);
		scene.setCamera(cam);
		scene.setDistance(700);
		AmbientLight a = new AmbientLight(new Color(50, 50, 50), 0.1);
		scene.setAmbientLight(a);
		scene.setAdaptive(true);
		scene.setSupersampling(false);
		scene.setDof(false);
		scene.setMultiThread(true);
		scene.setJustPicture(false);
		scene.setBackground(new Color(0, 0, 0));
		Geometries geometries = new Geometries();
		scene.setGeometries(geometries);
		Material material = new Material(1, 1, 120, 0.1, 0);
		// light

		geometries.add(new geometries.Rectangle(new Color(70, 50, 60), new Point3D(-500, 0, 250),
				new Point3D(-500, -500, 250), new Point3D(500, 0, 250), new Point3D(500, -500, 250), material));
		geometries.add(new geometries.Rectangle(new Color(70, 50, 60), new Point3D(-500, 0, 0),
				new Point3D(-500, 0, 250), new Point3D(500, 0, 0), new Point3D(500, 0, 250), material));

		geometries.add(new Sphere(new Color(255, 0, 0), 75, new Point3D(-170, -78, 80), new Material(3, 1, 100, 0, 0)));
		geometries.add(new Sphere(new Color(0, 255, 0), 75, new Point3D(0, -78, 180), new Material(5, 5, 100, 0, 0)));
		geometries
				.add(new Sphere(new Color(0, 0, 255), 75, new Point3D(170, -78, 280), new Material(10, 3, 100, 0, 0)));

		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(
				new SpotLight(new Color(0, 0, 200), new Point3D(-170, -90, -30), 0, 0.001, new Vector(0, 0, 1), 1));
		temoLightSources
				.add(new SpotLight(new Color(0, 200, 0), new Point3D(170, -90, 100), 0, 0.001, new Vector(0, 0, 1), 1));
		temoLightSources
				.add(new SpotLight(new Color(200, 0, 0), new Point3D(0, -90, 50), 0, 0.001, new Vector(0, 0, 1), 1));

		//scene.addLightSource(temoLightSources);
		ImageWriter imageWriter = new ImageWriter("Test adpt1.9000011", 500, 500, 500, 500);
		Render render = new Render(imageWriter, scene);
		render.renderImage();
		imageWriter.writeToimage();
	}

	/**
	 * In this test we will create a snake of bullets and a plain of focus found in
	 * the middle of the snake
	 */
	@Test
	public void dof_2() {
		Scene scene = new Scene("Test shadow");
		Camera cam = new Camera(new Point3D(0, 1300, 0), new Vector(0, 0, 1), new Vector(0, -1, 0));
		cam.setAperture(120);
		cam.setDistancePlain(6000);
		
		cam.setNumOfRays(20);
		scene.setCamera(cam);
		scene.setDistance(500);
		scene.setAdaptive(true);
		scene.setSupersampling(false);
		scene.setDof(false);
		scene.setMultiThread(true);
		scene.setJustPicture(false);
		scene.setBackground(new Color(200, 200, 200));
		scene.setAmbientLight(new AmbientLight(new Color(5, 5, 5), 10));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(new Color(0, 0, 255), 500, new Point3D(-1000, -1110, -900),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(30, 0, 230), 500, new Point3D(-600, -2000, -1050),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(60, 0, 200), 500, new Point3D(-200, -3100, -1200),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(90, 0, 190), 500, new Point3D(350, -4200, -1250),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(120, 0, 160), 500, new Point3D(990, -5300, -1200),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(150, 0, 130), 500, new Point3D(1700, -6400, -1000),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(180, 0, 100), 500, new Point3D(2500, -7500, -650),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(210, 0, 70), 500, new Point3D(3200, -8600, -250),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(240, 0, 40), 500, new Point3D(3900, -9700, 300),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(255, 0, 10), 500, new Point3D(4500, -10800, 900),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(240, 30, 0), 500, new Point3D(4900, -12000, 1650),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(220, 60, 0), 500, new Point3D(5000, -13100, 2450),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(190, 90, 0), 500, new Point3D(5000, -14200, 3200),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(160, 120, 0), 500, new Point3D(4900, -15300, 3800),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(130, 150, 0), 500, new Point3D(4700, -16500, 4400),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(70, 180, 0), 500, new Point3D(4400, -17700, 4700),
				new Material(0.5, 0.5, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(40, 210, 0), 500, new Point3D(4500, -21000, 5200),
				new Material(0.4, 0.4, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(10, 240, 0), 500, new Point3D(5200, -27000, 5900),
				new Material(0.4, 0.4, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(40, 255, 40), 500, new Point3D(7500, -38000, 7300),
				new Material(0.1, 0.1, 100, 0, 0.2)));

		geometries.add(new Sphere(new Color(70, 230, 70), 500, new Point3D(10000, -48000, 8600),
				new Material(0.1, 0.1, 100, 0, 0.1)));

		scene.setGeometries(geometries);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(new PointLight(new Color(180, 150, 150), new Point3D(-300, 0, -300), 0, 0));
		scene.addLightSource(temoLightSources);
		ImageWriter imageWriter = new ImageWriter("Test_dof_2_adpt9005", 500, 500, 500, 500);
		Render testRender = new Render(imageWriter, scene);

		testRender.renderImage();
		imageWriter.writeToimage();
	}
	
	@Test
	public void dof_3() {
		Scene scene = new Scene("Test shadow");
		Camera cam = new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1));
		cam.setAperture(120);
		cam.setDistancePlain(6000);
		cam.setNumOfRays(20);
		scene.setCamera(cam);
		scene.setDistance(1);
		scene.setAdaptive(true);
		scene.setSupersampling(false);
		scene.setDof(false);
		
		scene.setBackground(new Color(200, 0, 0));
		scene.setAmbientLight(new AmbientLight(new Color(5, 5, 5), 10));
		Geometries geometries = new Geometries();
		geometries.add(new Triangle(new Color(0, 0, 200), new Point3D(-0.6666,-0.6666,1.1), new Point3D(-0.6666, 0.2, 1.1), new Point3D(-0.2, -0.66666, 1.1), new Material(0.1, 0.1, 100, 0, 0.1)));
		

		scene.setGeometries(geometries);
		List<LightSource> temoLightSources = new ArrayList<LightSource>();
		temoLightSources.add(new PointLight(new Color(180, 150, 150), new Point3D(-300, 0, -300), 0, 0));
		scene.addLightSource(temoLightSources);
		ImageWriter imageWriter = new ImageWriter("Test 1.1", 1, 1, 1, 1);
		Render testRender = new Render(imageWriter, scene);

		testRender.renderImage();
		imageWriter.writeToimage();

	}
}