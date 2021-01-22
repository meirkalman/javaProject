/**
 * 
 */
package primitives;

/**
 * The class holds all data on the material type of the object
 * 
 * @author kalman & ravitz
 *
 */
public class Material {
	private double kd;
	private double ks;
	private int nShininess;
	private double kr;
	private double kt;

	
	/**
	 * copy constructor
	 * @param material
	 */
	public Material(Material material) {
		kd = material.kd;
		ks = material.ks;
		nShininess = material.nShininess;
		kr = material.kr;
		kt =material.kt;
	}

	/**
	 * Constructor
	 * @param kd1 diffusion factor
	 * @param ks1 specular factor
	 * @param ns  shininess
	 * @param kr1 reflection factor
	 * @param kt1 transparency factor
	 */
	public Material(double kd1, double ks1, int ns, double kr1, double kt1) {
		kd = kd1;
		ks = ks1;
		nShininess = ns;
		kr = kr1;
		kt = kt1;
	}

	
	/**
	 * c-tor
	 * @param kd1 diffusion factor
	 * @param ks1 specular factor
	 * @param ns shininess
	 */
	public Material(double kd1, double ks1, int ns) {
		kd = kd1;
		ks = ks1;
		nShininess = ns;
		kr = 0.5;
		kt = 0.5;
	}

	/**
	 * get Kd
	 * 
	 * @return double  diffusion factor
	 */
	public double getKd() {
		return kd;
	}

	/**
	 * get Ks
	 * 
	 * @return double specular factor
	 */
	public double getKs() {
		return ks;
	}

	/**
	 * get n shininess
	 * 
	 * @return int shininess
	 */
	public int getnShininess() {
		return nShininess;
	}

	/**
	 * get Kt Transparency coefficient
	 * 
	 * @return double transparency factor
	 */
	public double getKt() {
		return kt;
	}

	/**
	 * get kr The reflection coefficient
	 * @return double reflection factor
	 */
	public double getKr() {
		return kr;
	}
}
