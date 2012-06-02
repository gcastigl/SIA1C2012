package geneticalgorithm.crossover;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;


/**
 * Cruce de un solo punto.
 */
public class Clasic extends CrossoverMethod {
	
	@Override
	protected Chromosome[] cross(Chromosome c1, Chromosome c2) {
		Chromosome[] childs = new Chromosome[2];
		return childs;
	}
	
	@Override
	public Chromosome[] mutate(Configuration config, Chromosome[] childs) {
		return childs;
	}

}
