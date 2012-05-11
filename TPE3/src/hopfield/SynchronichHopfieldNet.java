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
}
