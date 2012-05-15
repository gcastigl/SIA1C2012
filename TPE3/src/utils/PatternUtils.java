package utils;

import hopfield.HopfieldNet;

public class PatternUtils {

	public static void invert(int[] pattern) {
		for (int i = 0; i < pattern.length; i++) {
			pattern[i] = (pattern[i] == HopfieldNet.STATE_NEGATIVE) ? 
				HopfieldNet.STATE_POSITIVE : HopfieldNet.STATE_NEGATIVE;
		}
	}
	
	/**
	 * Borra el 50% de los primeros valores del patron pasado por parametro
	 */
	public static void addNoise1(int[] pattern) {
		for (int i = 0; i < pattern.length / 2; i++) {
			pattern[i] = HopfieldNet.STATE_NEGATIVE;
		}
	}
}
