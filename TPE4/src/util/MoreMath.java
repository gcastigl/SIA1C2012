package util;

public class MoreMath {

	/**
	 * Returns a pseudo-random value in the interval (a,b) 
	 */
	public static float random(float a, float b) {
		return (float) Math.random() * (b - a) + a;
	}
}
