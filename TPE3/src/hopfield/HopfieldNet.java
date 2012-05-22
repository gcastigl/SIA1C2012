package hopfield;


public abstract class HopfieldNet {
	
	public static final int STATE_POSITIVE = 1;
	public static final int STATE_NEGATIVE = -1;
	
	/**
	 * Should always be either STATE_POSITIVE or STATE_NEGATIVE
	 * because this are BIPOLAR units.
	 */
	protected int[] states;
	protected float[][] weights;	

	/**
	 * Initializes the weights matrix given all the patterns
	 * that the network will have to memorize.
	 * This represents the "learning" of a Hopfield network and
	 * it is important to remark that the weights matrix will not
	 * be modified again after the execution of this method.
	 *
	 * @patterns: Matrix of patterns that the network should 
	 * memorize; one pattern per row.
	 */ 	
	public void storePatterns(int[][] patterns) {
		int N = patterns[0].length;
		weights = new float[N][N];
		states = new int[N];
		for (int i = 0; i < weights.length; i++) {
			for (int j = i; j < weights[0].length; j++) {
				setWeight(i, j, patterns);
			}
		}
	}
	
	protected void setWeight(int i, int j, int[][] patterns) {
		weights[i][j] = 0;
		if (i != j) {
			for (int[] pattern: patterns) {
				weights[i][j] += pattern[i] * pattern[j];
			}
			weights[i][j] /= weights[0].length;
			// mirrored matrix!
			weights[j][i] = weights[i][j];
		}
	}
	
	public void initialize(int[] recognize) {
		validateDimention(recognize);
		for (int i = 0; i < states.length; i++) {
			states[i] = recognize[i];
		}
	}
	
	private void validateDimention(int[] vec) {
		if (vec.length != states.length) {
			throw new IllegalArgumentException("Array must have lenght = " + states.length);
		}
	}
	
	/**
	 * <pre>
	 * Initially "states" will contain the pattern which we want the network
	 * to recognize. We will multiply the weights matrix (final) by the states
	 * vector assigning this answer to "states" until we detect a fixed point
	 * (no changes between the previous step and the current step in the vec).
	 * </pre>
	 */ 
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
	
	public int sgn(float h, int prevState) {
		if (h == 0) {
			return prevState;
		}
		return (h > 0) ? STATE_POSITIVE : STATE_NEGATIVE;
	}
	
	
	public float E(int[] s) {
		float e = 0;
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {
				e += weights[i][j] * s[i] * s[j];
			}
		}
		return e / -2;
	}
	
	/*
	 * Calcula el vector crossTalk para el patron v del vector patterns
	 */
	public float[] crossTalk(int v, int[][] patterns) {
		int N = patterns[0].length;
		float[] crossTalk = new float[N];
		for (int i = 0; i < N; i++) {
			float k = 0;
			for (int j = 0; j < N; j++) {
				for (int u = 0; u < patterns.length; u++) {
					if (u != v) {
						k += patterns[u][i] * patterns[u][j] * patterns[v][j];
					}
				}
			}
			crossTalk[i] = patterns[v][i] + (k / N);
		}
		return crossTalk;
	}
}
