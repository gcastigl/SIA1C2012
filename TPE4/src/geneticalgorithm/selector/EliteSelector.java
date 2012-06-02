package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

/**
 * Por cada chormosoma, me quedo con los k mas aptos seteados en la
 * configuracion.
 */
public class EliteSelector extends CandidateSelector {

	@Override
	protected void calcFitness(Chromosome c) {
		
	}
	
	@Override
	public int[] select(Configuration config) {
		return null;
	}

	@Override
	public void replace(Configuration config) {
		
	}

}
