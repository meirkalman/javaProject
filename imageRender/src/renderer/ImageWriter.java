package renderer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.imageio.stream.*;

/**
 * class for ImageWriter gets the values ​​of the view plane and Create an image
 * 
 * @author kalman & ravitz
 *
 */
public class ImageWriter {

	private double _imageWidth, _imageHeight;
	private int _nX, _nY;

	final String PROJECT_PATH = System.getProperty("user.dir");

	private BufferedImage _image;

	private String _imageName;

	// ***************** Constructors ********************** //
	/**
	 * 
	 * @param String imageName
	 * @param        double width
	 * @param        double height
	 * @param        int nX
	 * @param        int nY
	 */
	public ImageWriter(String imageName, double width, double height, int nX, int nY) {
		_imageName = imageName;
		_imageWidth = width;
		_imageHeight = height;
		_nX = nX;
		_nY = nY;

		_image = new BufferedImage(_nX, _nY, BufferedImage.TYPE_INT_RGB);
	}

	// copy constructor
	public ImageWriter(ImageWriter imageWriter) {
		this(imageWriter._imageName, imageWriter._imageWidth, imageWriter._imageHeight, imageWriter._nX,
				imageWriter._nY);
	}

	// ***************** Getters/Setters ********************** //
	/**
	 * get _imageWidth
	 * 
	 * @return double
	 */
	public double getWidth() {
		return _imageWidth;
	}

	/**
	 * get Height
	 * 
	 * @return double
	 */
	public double getHeight() {
		return _imageHeight;
	}

	/**
	 * get Ny
	 * 
	 * @return int
	 */
	public int getNy() {
		return _nY;
	}

	/**
	 * get Nx
	 * 
	 * @return int
	 */
	public int getNx() {
		return _nX;
	}

	/**
	 * set Ny
	 * 
	 * @param _Ny
	 */
	public void setNy(int _Ny) {
		this._nY = _Ny;
	}

	/**
	 * set Nx
	 * 
	 * @param _Nx
	 */
	public void setNx(int _Nx) {
		this._nX = _Nx;
	}

	// ***************** Operations ******************** //
	/**
	 * The function takes images and makes them into a file jpg
	 */
	public void writeToimage() {
		File ouFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");
		try {
			javax.imageio.ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
			ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
			jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			jpgWriteParam.setCompressionQuality(1f);
			jpgWriter.setOutput(new FileImageOutputStream(ouFile));
			jpgWriter.write(null, new IIOImage(_image, null, null), jpgWriteParam);
			// ImageIO.write(_image, "jpg", ouFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * The function takes all the data and makes them into a image
	 * 
	 * @param xIndex
	 * @param yIndex
	 * @param color
	 */
	public void writePixel(int xIndex, int yIndex, Color color) {
		_image.setRGB(xIndex, yIndex, color.getRGB());
	}

}