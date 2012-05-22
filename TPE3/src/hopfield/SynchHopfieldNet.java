package hopfield;

import java.util.Arrays;

import utils.MatrixUtils;

/**
 * Red de Hopfield segun el modelo de Little. (Red sincronica)
 */ 	
public class SynchHopfieldNet extends HopfieldNet {

	@Override
	public int[] iterateUntilConvergence() {
		// FIXME: Note that we can fall into cycles with lenght 2 and we must prevent this pitfall.
		boolean finished = false;
		int[][] nextStates = new int[2][];
		int i = 0, iterations = 0;
		while (!finished) {
			int[] newState = sgn(MatrixUtils.multiply(weights, states), states);
			if (Arrays.equals(states, newState)) {
				nextStates[i] = newState;
				finished = true;
			} else {
				states = newState;
				if (iterations >= 2 && Arrays.equals(newState, nextStates[i])) {
					finished = true;
					break;
				}
				nextStates[i] = newState;
				i++; i %= 2;				
				iterations++;				
			}
		}
		return nextStates[i];
	}
	
	protected int[] sgn(float[] vec, int[] prevStates) {
		int[] ans = new int[vec.length];
		for (int i = 0; i < vec.length; i++) {
			ans[i] = sgn(vec[i], prevStates[i]);
		}
		return ans;
	}
}
