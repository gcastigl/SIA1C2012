package hopfield;


public abstract class HopfieldNet {
	
	/**
	 * Should always be either -1 or 1
	 */
	protected int[] states;
	protected float[][] weights;
	
	public HopfieldNet(int N) {
		weights = new float[N][N];
		states = new int[N];
	}
		
	public void storePatterns(int[][] patterns) {
		for (int i = 0; i < weights.length; i++) {
			for (int j = i; j < weights[0].length; j++) {
				if (i == j) {
					weights[i][j] = 0;
				} else {
					weights[i][j] = 0;
					for (int[] pattern: patterns) {
						weights[i][j] += pattern[i] * pattern[j]; 	
					}
					weights[i][j] /= weights[0].length;
					// mirror matrix
					weights[j][i] = weights[i][j];
				}
			}
		}
	}
	
	public void initialize(int[] recognize) {
		for (int i = 0; i < states.length; i++) {
			states[i] = recognize[i];
		}
	}
	
	public abstract int[] iterateUntilConvergence();
	
	public float[][] getWeights() {
		return weights;
	}
	
	public int[] getStates() {
		return states;
	}
}
