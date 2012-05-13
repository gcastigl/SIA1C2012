package hopfield;

import java.util.Arrays;

import utils.MatrixUtils;

/**
 * Red de Hopfield segun el modelo de Little. (Red sincronica)
 */ 	
public class SynchHopfieldNet extends HopfieldNet {

	public SynchHopfieldNet(int N) {
		super(N);
	}

	@Override
	public int[] iterateUntilConvergence() {
		// FIXME: Note that we can fall into cycles with lenght 2 and we must prevent this pitfall.
		boolean finished = false;
		int[] nextStates = null;
		while (!finished) {
			nextStates = sgn(MatrixUtils.multiply(weights, states), states);
			if (Arrays.equals(states, nextStates)) {
				finished = true;
			} else {
				states = nextStates;
			}
		}
		// should never get here
		return nextStates;
	}
	
	protected int[] sgn(float[] vec, int[] prevStates) {
		int[] ans = new int[vec.length];
		for (int i = 0; i < vec.length; i++) {
			ans[i] = (vec[i] > 0) ? STATE_POSITIVE : 
				((vec[i] < 0) ? STATE_NEGATIVE : prevStates[i]);
		}
		return ans;
	}
}
