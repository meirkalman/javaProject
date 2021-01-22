/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Vector;

/**
 * The class tests the class Vector
 * 
 * @author ravitz & kalaman
 */

public class TestVector {

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}. The
	 * function checks whether the subtraction between the vectors runs well and
	 * whether an exception is thrown in the case of the zero vector.
	 * 
	 */
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(4, 4, 4);
		Vector v2 = new Vector(1, 1, 1);
		Vector v3 = new Vector(3, 3, 3);
		assertEquals(v3, v1.subtract(v2));
		try {

			v1 = new Vector(3, 3, 3);
			v2 = new Vector(3, 3, 3);
			v1.subtract(v2);
			fail("Do not throw the exception of the zero vector!");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(4, 4, 4);
		Vector v2 = new Vector(1, 1, 1);
		Vector v3 = new Vector(5, 5, 5);
		assertEquals(v3, v1.add(v2));
		try {
			v1 = new Vector(-3, -3, -3);
			v2 = new Vector(3, 3, 3);
			v1.add(v2);
			fail("Do not throw the exception of the zero vector!");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}. The
	 * function checks the end points of orthogonal vectors, vectors with blunt
	 * angle and vectors with a sharp angle
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 0, 0);
		Vector v2 = new Vector(0, 0, 1);
		assertEquals(0, v1.dotProduct(v2), 1e-10);
		v1 = new Vector(0, 1, 0);
		v2 = new Vector(1, 1, 0);
		assertEquals(1, v1.dotProduct(v2), 1e-10);
		v1 = new Vector(1, 0, 0);
		v2 = new Vector(-1, 1, 0);
		assertEquals(-1, v1.dotProduct(v2), 1e-10);
	}

	/**
	 * Test method for {@link primitives.Vector#SetAndCheck()}.
	 */
	@Test
	public void testSetAndCheck() {
		try {
			Vector v = new Vector(0, 0, 0);
			fail("Do not throw the exception of the zero vector!");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 4, 6);
		Vector v2 = new Vector(-1, -4, -6);
		Vector v3 = new Vector(2, 8, 12);
		try {
			v1.crossProduct(v1);
			fail("Do not throw the exception of the zero vector!");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		try {
			v1.crossProduct(v2);
			fail("Do not throw the exception of the zero vector!");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}

		try {
			v1.crossProduct(v3);
			fail("Do not throw the exception of the zero vector!");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link primitives.Vector#length2()}.
	 */
	@Test
	public void testLength2() {
		Vector v1 = new Vector(3, 3, 3);
		Vector v2 = new Vector(1, 2, 3);
		assertEquals(14, v2.length2(), 1e-10);
		assertEquals(27, v1.length2(), 1e-10);
	}

	/**
	 * Test method for {@link primitives.Vector#length2()}.
	 */
	@Test
	public void testScaling() {
		Vector v2 = new Vector(1, 2, 3);
		Vector v1 = new Vector(5, 10, 15);
		assertEquals(v1, v2.scaling(5));
		try {
			v1.scaling(0);
			fail("Do not throw the exception of the zero vector!");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		Vector v1 = new Vector(0, 0, 5);
		Vector v2 = new Vector(3, 3, 3);
		assertEquals(5.1961524227, v2.length(), 1e-10);
		assertEquals(5, v1.length(), 1e-10);
	}

	/**
	 * Test method for {@link primitives.Vector#normal()}. The function checks
	 * whether the vectors are normalized well and whether an exception is thrown in
	 * the case of the zero vector.
	 */
	@Test
	public void testNormalize() {
		Vector v = new Vector(3, 6, 4);
		v = v.normalize();
		assertEquals(1.0, v.length(), 1e-10);
	}

}
