import hopfield.HopfieldNet;
import hopfield.SynchronichHopfieldNet;

import java.util.Arrays;

import utils.ImageUtils;

public class Main {

	public static void main(String[] args) {
		
		int[][] patterns = new int[2][];
		int N = getDummyPatterns(patterns);

		HopfieldNet net = new SynchronichHopfieldNet(N);
		net.storePatterns(patterns);
		
		// For debugging purposes
//		System.out.println("Pesos: ");
//		MatrixUtils.print(net.getWeights());
		
		// Set here what you want the net to recongnize
//		int[] recognize = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line1.png");
		int[] recognize = new int[] {1, -1, 1};
		net.initialize(recognize);
		int[] ans = net.iterateUntilConvergence();
		
		System.out.println("Patron devuelto");
		System.out.println(Arrays.toString(ans));
		int patternIndex = getMatchingPatternIndex(patterns, ans);
		if (patternIndex != -1) {
			System.out.println("El patron introducido se parece al " + patternIndex);
		} else {
			System.out.println("El array devuelto no matchea con ningun patron");
		}
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
		patterns[0] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line1.png");
		patterns[1] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line2.png");
//		patterns[2] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line3.png");
//		patterns[3] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line4.png");
		return (int) Math.sqrt(patterns[0].length);	// should always be 64
	}
	
	private static int getDummyPatterns(int[][] patterns) {
		patterns[0] = new int[] {1, -1, 1};
		patterns[1] = new int[] {-1, -1, 1};
		return patterns[0].length;
	}
}
