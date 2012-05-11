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
	
	/**
	 *
	 * Applies the SGN() function to the input array (vec).
	 * In the case that vec[i] = 0 then the previous value is stored
	 * in that possition.
	 *
	 * @vec: States for each unit of the network
	 * @prevStates: States for each unit of the network. 
	 * The prevStates are used because the original states are beign modified 
	 * and we need the original states in the case that vec[i] = 0
	 * 
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
	
	public int getNumNeurons() {
		return states.length;
	}
}
