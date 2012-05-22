package hopfield;


public class AsynchHopfieldNet extends HopfieldNet {

	@Override
	public int[] iterateUntilConvergence() {
		int maxIterationsWithoutChanges = 5 * states.length;
		int iterationsWithoutChanges = 0;
		// Repetir la iteración hasta que el vector de estados permanezca sin cambiar
		int iterations = 0;
		while (iterationsWithoutChanges < maxIterationsWithoutChanges) {
			iterations++;
			if (iterations%200 == 0) {
//				Config.saveStateToImage(states);
			}
			int index = (int) (Math.random() * getNumNeurons());
			int prevState = states[index];
			updateUnit(index);
			if (states[index] == prevState) {
				iterationsWithoutChanges++;
			} else {
				iterationsWithoutChanges = 0;
			}
		}
		return states;
	}
	
	private void updateUnit(int index) {
		float h = 0;
		for (int i = 0; i < getNumNeurons(); i++) {
			h += weights[index][i] * states[i];
		}
		states[index] = sgn(h, states[index]);
	}

}
