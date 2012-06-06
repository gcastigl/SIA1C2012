package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class RouletteSelector extends CandidateSelector {

	private ChromosomeContainer[] array;
	private float sumFitness; 	// Sum of the fitness of each individual in the
								// population

	public RouletteSelector(Configuration config) {
		super(config);
	}

	@Override
	public int[] select() {
		return selectBestK((int) (config.G * config.N));
	}

	private int[] selectBestK(int k) {
		sumFitness = calctTotalFitness(config.population);
		computeSlotSize();
		computeCumProbability();
		float rnd = 0;
		boolean individualChosen = false;
		// Get the best k individuals
		int[] selected = new int[k];
		for (int i = 0; i < selected.length; i++) {
			rnd = (float) Math.max(Math.random(), 0.000001f);	// rnd should never be 0!
			if (rnd < array[0].qi) {
				selected[i] = array[0].index;
			} else {
				individualChosen = false;
				for (int j = 1; !individualChosen && j < selected.length; j++) {
					if (rnd > array[j - 1].qi && rnd <= array[j].qi) {
						selected[i] = array[i].index;
						individualChosen = true;
					}
				}
			}
		}
		return selected;
	}

	@Override
	public void replace(Chromosome[] childs) {
		Chromosome[] oldPpulation = config.population;
		Chromosome[] newPopulation = new Chromosome[config.N];
		int[] selected = selectBestK(config.N - childs.length);
		int index = 0;
		while (index < childs.length) {
			newPopulation[index] = childs[index];
			index++;
		}
		for (int i = 0; i < selected.length; index++, i++) {
			newPopulation[index] = oldPpulation[selected[i]];
		}
		// update population
		config.population = newPopulation;
	}

	private void computeSlotSize() {
		Chromosome[] population = config.population;
		if (array == null) {
			initArray(population.length);
		}
		for (int i = 0; i < population.length; i++) {
			array[i].index = i;
			array[i].fitness = population[i].getFitness();
			array[i].fi = array[i].fitness / sumFitness;
		}
	}

	private void computeCumProbability() {
		float slotSumWithLimit = 0;
		for (ChromosomeContainer e : array) {
			slotSumWithLimit += e.fi;
			e.qi = slotSumWithLimit;
		}
	}

	private void initArray(int length) {
		array = new ChromosomeContainer[length];
		for (int i = 0; i < length; i++) {
			array[i] = new ChromosomeContainer();
		}
	}

	private static class ChromosomeContainer {
		int index;
		float fitness;
		float fi; // Slot size
		float qi; // Cumulative probability
	}

}
