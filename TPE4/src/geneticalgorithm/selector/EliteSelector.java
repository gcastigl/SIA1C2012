package geneticalgorithm.selector;

import java.util.Arrays;

import geneticalgorithm.Configuration;


public class EliteSelector extends CandidateSelector {

	@Override
	public int[] select(Configuration config) {
		float[] fitness = calcFitness(config);
		Arrays.sort(fitness);
		return null;
	}

}
