import hopfield.AsynchHopfieldNet;
import hopfield.Config;
import hopfield.HopfieldNet;

import java.util.Arrays;

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
		int patronToRecognize = 2;
		int[] recognize = Config.getImageAsState(patronToRecognize);
		System.out.println("Pattern to be recognized: " + patronToRecognize);
//		int[] recognize = new int[] {1, -1, 1};
		net.initialize(recognize);
		int[] ans = net.iterateUntilConvergence(); // Evolve the states until converge
		
		System.out.println("Recongnized pattern: " + Arrays.toString(ans));
		int patternIndex = getMatchingPatternIndex(patterns, ans);
		if (patternIndex != -1) {
			System.out.println("returned patter matches pattern number: " + patternIndex);
		} else {
			System.out.println("El array devuelto no matchea con ningun patron");
		}
		Config.saveStateToImage(ans);
	}
	
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
			patterns[i] = Config.getImageAsState(i);
		}
		return patterns[0].length;
	}
	
	private static int getDummyPatterns(int[][] patterns) {
		patterns[0] = new int[] {1, -1, 1};
		patterns[1] = new int[] {-1, -1, 1};
		return patterns[0].length;
	}
	

}
