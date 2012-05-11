import hopfield.AsynchHopfieldNet;
import hopfield.Config;
import hopfield.HopfieldNet;

import java.util.Arrays;

import utils.ImageUtils;

public class Main {
	public static void main(String[] args) {
		System.out.println("Network will memorize " + Config.NUMBER_OF_PATTERNS + " patterns.");
		int[][] patterns = new int[Config.NUMBER_OF_PATTERNS][];
		int N = getImagePatterns(patterns);
//		int N = getDummyPatterns(patterns);

		HopfieldNet net = new AsynchHopfieldNet(N);
		net.storePatterns(patterns);
		
		// For debugging purposes
//		System.out.println("Pesos: ");
//		MatrixUtils.print(net.getWeights());
		
		// Set here what you want the net to recongnize
		int[] recognize = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/" + Config.pictures[0]);
//		int[] recognize = new int[] {1, -1, 1};
		net.initialize(recognize);
		int[] ans = net.iterateUntilConvergence(); // Evolve the states until converge
		
		System.out.println("Patron devuelto");
		System.out.println(Arrays.toString(ans));
		int patternIndex = getMatchingPatternIndex(patterns, ans);
		if (patternIndex != -1) {
			System.out.println("El patron introducido se parece al " + patternIndex);
		} else {
			System.out.println("El array devuelto no matchea con ningun patron");
		}
	}
	
	/**
	 *
	 * Check if any of the original memorized patterns if equal to the
	 * the given pattern (usually the network output).
	 * 
	 * @patterns: Original patterns matrix.
	 * @vec: Pattern we want to check for equality.
	 *
	 */ 
	private static int getMatchingPatternIndex(int[][] patterns, int[] vec) {
		int index = 0;
		for (int[] pattern: patterns) {
			if (Arrays.equals(pattern, vec)) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	private static int getImagePatterns(int[][] patterns) {
		for (int i = 0; i < Config.NUMBER_OF_PATTERNS; i++) {
			patterns[i] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/" + Config.pictures[i]);
		}
		return patterns[0].length;
	}
	
	private static int getDummyPatterns(int[][] patterns) {
		patterns[0] = new int[] {1, -1, 1};
		patterns[1] = new int[] {-1, -1, 1};
		return patterns[0].length;
	}
	

}
