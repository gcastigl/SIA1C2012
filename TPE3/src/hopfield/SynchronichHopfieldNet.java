package hopfield;

import java.util.Arrays;

import utils.MatrixUtils;

public class SynchronichHopfieldNet extends HopfieldNet {

	public SynchronichHopfieldNet(int N) {
		super(N);
	}
	
	@Override
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
			ans[i] = (vec[i] > 0) ? STATE_POSITIVE : 
				((vec[i] < 0) ? STATE_NEGATIVE : prevStates[i]);
		}
		return ans;
	}
}
