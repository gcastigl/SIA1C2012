package hopfield;

import java.util.Arrays;

import utils.MatrixUtils;

public class HopfieldNet {
	
	/**
	 * -1 or 1
	 */
	private int[] states;
	private float[][] weights;
	
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
	
	public int[] iterateUntilConvergence() {
		boolean finished = false;
		while (!finished) {
			int[] nextStates = sgn(MatrixUtils.multiply(weights, states), states);
			if (Arrays.equals(states, nextStates)) {
				finished = true;
				return nextStates;
			} else {
				states = nextStates;
			}
		}
		// should never get here
		return null;
	}

	/**
	 * Aplica la funcion signo al vector vec.
	 * En el caso que vec[i] sea 0, se guarda el valor que se encuentra en prevStates.
	 */
	private int[] sgn(float[] vec, int[] prevStates) {
		int[] ans = new int[vec.length];
		for (int i = 0; i < vec.length; i++) {
			ans[i] = (vec[i] > 0) ? 1 : (vec[i] < 0 ? -1 : prevStates[i]);
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
