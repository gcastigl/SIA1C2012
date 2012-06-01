package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;


public abstract class CandidateSelector {
	
	public abstract int[] select(Configuration config);
	
	protected float[] calcFitness(Configuration config) {
		int i = 0;
		float[] fitness = new float[config.population.length];
		for (Chromosome c: config.population) {
			fitness[i++] = c.fitnes();
		}
		return null;
	}
}
