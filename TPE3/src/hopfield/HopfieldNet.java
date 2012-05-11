package hopfield;


public abstract class HopfieldNet {
	
	public static final int STATE_POSITIVE = 1;
	public static final int STATE_NEGATIVE = -1;
	
	/**
	 * Should always be either STATE_POSITIVE or STATE_NEGATIVE
	 * because we use BIPOLAR units.
	 */
	protected int[] states;
	protected float[][] weights;
	
	public HopfieldNet(int N) {
		weights = new float[N][N];
		states = new int[N];
	}
	

	/**
	 *
	 * Initializes the wheigths matrix given all the patterns
	 * that the network will have to memorize.
	 * This represents the "learning" of a Hopfield network and
	 * it is important to remark that the wheights matrix will not
	 * be modified again after the execution of this method.
	 *
	 * @patterns: Matrix of patterns that the network shold 
	 * memorize; one pattern per row.
	 *
	 */ 	
	public void storePatterns(int[][] patterns) {
		for (int i = 0; i < weights.length; i++) {
			for (int j = i; j < weights[0].length; j++) {
				setWeight(i, j, patterns);
			}
		}
	}
	
	private void setWeight(int i, int j, int[][] patterns) {
		if (i == j) {
			weights[i][j] = 0;
			return;
		}
		weights[i][j] = 0;
		for (int[] pattern: patterns) {
			weights[i][j] += pattern[i] * pattern[j];
		}
		weights[i][j] /= weights[0].length;
		// mirrored matrix!
		weights[j][i] = weights[i][j];
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
	
	public int getNumNeurons() {
		return states.length;
	}
}
