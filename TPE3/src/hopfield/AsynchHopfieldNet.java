package hopfield;

import java.util.Arrays;

public class AsynchHopfieldNet extends HopfieldNet {

	public AsynchHopfieldNet(int N) {
		super(N);
	}

	@Override
	public int[] iterateUntilConvergence() {
		int[] prevStates = states.clone();
		do {
			int index = (int) (Math.random() * getNumNeurons());
			int state = states[index];
			float h = 0;
			for (int i = 0; i < getNumNeurons(); i++) {
				h += weights[index][i] * states[i];
			}
			states[index] = sgn(h, state);
		} while (!Arrays.equals(prevStates, states));
		return prevStates;
	}

	private int sgn(float h, int prevState) {
		if (h == 0) {
			return prevState;
		}
		return (h > 0) ? STATE_POSITIVE : STATE_NEGATIVE;
	}

}
