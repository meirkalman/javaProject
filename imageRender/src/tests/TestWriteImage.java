/**
 * 
 */
package tests;

import java.awt.image.AreaAveragingScaleFilter;

import org.junit.Test;

import elements.AmbientLight;
import primitives.Color;
import renderer.ImageWriter;

/**
 * test the class WriteImage In the next tests we will make grid and build
 * pictures and check that the pictures are going out as expected
 * 
 * @author kalman & ravitz
 *
 */
public class TestWriteImage {
	@Test
	public void test() {
		Color color = new Color(255, 255, 255);
		Color color2 = new Color(255, 0, 0);
		AmbientLight a = new AmbientLight(color, 50.0);
		AmbientLight b = new AmbientLight(color2, 200.0);
		ImageWriter imageWriter = new ImageWriter("ravitz&kalman4", 10, 10, 500, 500);
		// looping for background
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < 500; j++) {
				imageWriter.writePixel(i, j, b.getIntensity().getColor());
			}
		}
		// looping for grid color
		for (int i = 0; i < 500; i++) {
			for (int j = 0; j < 500; j++) {
				if (i % 50 == 0 || j % 50 == 0) {
					imageWriter.writePixel(i, j, a.getIntensity().getColor());

				}

			}
		}
		// making the file
		imageWriter.writeToimage();
	}
}
