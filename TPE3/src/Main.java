import java.util.Arrays;


public class Main {

	public static void main(String[] args) {
		
		
		Utils.toblackAndWhite("./TPE3/resources/circle1.png");
		
		int N = 3;
		int[][] patterns = new int[2][N];
		patterns[0] = new int[] {1, -1, 1};
		patterns[1] = new int[] {-1, 1, -1};
		
		HopfieldNet net = new HopfieldNet(N);
		net.storePatterns(patterns);
		
		System.out.println("Pesos: ");
		MatrixUtils.print(net.getWeights());
		
		int[] recognize = new int[] {-1, -1, -1};
		net.initialize(recognize);
		
		System.out.println("Estados iniciales");
		System.out.println(Arrays.toString(net.getStates()));
		
		int[] ans = net.iterateUntilConvergence();
		System.out.println("Patron devuelto");
		System.out.println(Arrays.toString(ans));
	}
	
	
	private static void printNet(HopfieldNet net) {
		
	}

}
