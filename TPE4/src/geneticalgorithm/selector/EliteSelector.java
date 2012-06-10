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
	private ChromosomeContainer[] array;

	public EliteSelector(Configuration config) {
		super(config);
	}

	@Override
	public int[] select() {
		return selectBestK((int) (config.G * config.N));
	}

	@Override
	protected int[] selectBestK(int k) {
		sortPopulationByFitness();
		// Get the best k individuals
		int[] selected = new int[k];
		for (int i = 0; i < selected.length; i++) {
			selected[i] = array[i].index;
		}
		return selected;
	}

	private void sortPopulationByFitness() {
		Chromosome[] population = config.population;
		if (array == null) {
			initArray(population.length);
		}
		for (int i = 0; i < population.length; i++) {
			array[i].index = i;
			array[i].fitness = population[i].getFitness();
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
	public void replace(Chromosome[] childs) {
		int[] selected = selectBestK(config.N - childs.length);
		createNewPopulation(selected, childs);
	}

	private static class ChromosomeContainer implements
			Comparable<ChromosomeContainer> {
		int index;
		float fitness;

		@Override
		public int compareTo(ChromosomeContainer o) {
			if(o.fitness == fitness)
				return 0;
			if( o.fitness < fitness)
				return -1;
			return 1;
		}
	}
}
