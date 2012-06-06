package util;

public class MoreMath {

	public static final float EPS = 0.00001f;
	
	/**
	 * Returns a pseudo-random value in the interval (a,b) 
	 */
	public static float random(float a, float b) {
		return (float) Math.random() * (b - a) + a;
	}
	
	public static float dotProduct(float[] v1, float[] v2) {
		validateDimation(v1, v2);
		float sum = 0;
		for (int j = 0; j < v1.length; j++) {
			sum += v1[j] * v2[j];
		}
		return sum;
	}
	
	private static void validateDimation(float[] v1, float[] v2) {
		if (v1.length != v2.length) {
			throw new IllegalArgumentException("Argument must match: " + v1.length + " != " + v2.length);
		}
	}

	public static double[] asDouble(float[] array) {
		double[] v = new double[array.length];
		for (int i = 0; i < v.length; i++) {
			v[i] = array[i];
		}
		return v;
	}
}
