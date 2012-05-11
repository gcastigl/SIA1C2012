package hopfield;


public abstract class HopfieldNet {
	
	public static final int STATE_POSITIVE = 1;
	public static final int STATE_NEGATIVE = -1;
	
	/**
	 * Should always be either STATE_POSITIVE or STATE_NEGATIVE
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
	
	/**
	 * Aplica la funcion signo al vector vec.
	 * En el caso que vec[i] sea 0, se guarda el valor que se encuentra en prevStates.
	 */
	protected int[] sgn(float[] vec, int[] prevStates) {
		int[] ans = new int[vec.length];
		for (int i = 0; i < vec.length; i++) {
			ans[i] = (vec[i] > 0) ? STATE_POSITIVE : 
				((vec[i] < 0) ? STATE_NEGATIVE : prevStates[i]);
		}
		return ans;
	}
	
	public float[][] getWeights() {
		return weights;
	}
	
	public int[] getStates() {
		return states;
	}
}
