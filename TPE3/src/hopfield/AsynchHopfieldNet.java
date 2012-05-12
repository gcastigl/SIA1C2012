package hopfield;

import java.util.Arrays;

public class AsynchHopfieldNet extends HopfieldNet {

	public AsynchHopfieldNet(int N) {
		super(N);
	}

	@Override
	public int[] iterateUntilConvergence() {
		int maxIterationsWithoutChanges = 10;
		int iterationsWithoutChanges = 0;
		do {
			int index = (int) (Math.random() * getNumNeurons());
			int prevState = states[index];
			updateUnit(index);
			if (states[index] == prevState) {
				iterationsWithoutChanges++;
			} else {
				iterationsWithoutChanges = 0;
			}
		// Repetir la iteración hasta que el vector de estados permanezca sin cambiar
		} while (iterationsWithoutChanges < maxIterationsWithoutChanges);
		return states;
	}
	
	private void updateUnit(int index) {
		float h = 0;
		for (int i = 0; i < getNumNeurons(); i++) {
			h += weights[index][i] * states[i];
		}
		states[index] = sgn(h, states[index]);
	}

	private int sgn(float h, int prevState) {
		if (h == 0) {
			return prevState;
		}
		return (h > 0) ? STATE_POSITIVE : STATE_NEGATIVE;
	}

}
