package geneticalgorithm.crossover;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public abstract class CrossoverMethod {

	/**
	 * Crea NUEVOS individuos a partir de los indicados en el parametro
	 * selected. No modifica los originales.
	 */
	public abstract Chromosome[] cross(Configuration config, int[] selected);

}
