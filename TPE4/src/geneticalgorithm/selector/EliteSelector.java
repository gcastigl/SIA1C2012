package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

import java.util.Arrays;

/**
 * Por cada chormosoma, me quedo con los k mas aptos seteados en la
 * configuracion.
 */
public class EliteSelector extends CandidateSelector {

	// Used to sort chromosome to choose the best k
	ChromosomeContainer[] array;

	@Override
	public int[] select(Configuration config) {
		sortPopulationByFitness(config);
		// Get the best k individuals
		int[] selected = new int[config.k_elite];
		for (int i = 0; i < selected.length; i++) {
			selected[i] = array[i].index;
		}
		return selected;
	}

	private void sortPopulationByFitness(Configuration config) {
		Chromosome[] population = config.population;
		if (array == null) {
			initArray(population.length);
		}
		for (int i = 0; i < population.length; i++) {
			array[i].index = i;
			array[i].fitness = population[i].getFitnes();
		}
		Arrays.sort(array);
	}

	private void initArray(int length) {
		array = new ChromosomeContainer[length];
		for (int i = 0; i < length; i++) {
			array[i] = new ChromosomeContainer();
		}
	}

	@Override
	public void replace(Configuration config, Chromosome[] childs) {

	}

	private static class ChromosomeContainer implements
			Comparable<ChromosomeContainer> {
		int index;
		float fitness;

		@Override
		public int compareTo(ChromosomeContainer o) {
			return (int) (o.fitness - fitness);
		}
	}
}
