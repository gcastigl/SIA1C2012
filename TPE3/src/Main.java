import hopfield.HopfieldNet;

import java.util.Arrays;

import utils.MatrixUtils;
import utils.ImageUtils;


public class Main {

	public static void main(String[] args) {
		
		int TOTAL_PATTERNS = 2;
		int[][] patterns = new int[TOTAL_PATTERNS][];
		patterns[0] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line1.png");
		patterns[1] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line2.png");
//		patterns[2] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line3.png");
//		patterns[3] = ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line4.png");
		int N = (int) Math.sqrt(patterns[0].length);
				
		HopfieldNet net = new HopfieldNet(N);
		net.storePatterns(patterns);
		
		System.out.println("Pesos: ");
		MatrixUtils.print(net.getWeights());
		
		int[] recognize =  ImageUtils.loadBlackAndWhiteImage("./TPE3/resources/line1.png");
		net.initialize(recognize);
		
		System.out.println("Estados iniciales");
		System.out.println(Arrays.toString(net.getStates()));
		
		int[] ans = net.iterateUntilConvergence();
		System.out.println("Patron devuelto");
		System.out.println(Arrays.toString(ans));
	}
}
