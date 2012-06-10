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

	@Override
	public void replace(Chromosome[] childs) {
		int[] selected = selectBestK(config.N - childs.length);
		createNewPopulation(selected, childs);
	}
	
	private int[] selectBestK(int k) {
		sumFitness = calctTotalFitness(config.population);
		computeSlotSize();
		computeCumProbability();
		float rnd = 0;
		boolean individualChosen = false;
		// Get the best k individuals
		int[] selected = new int[k];
		int N = config.population.length;
		//boolean[] used = new boolean[N];
		//for( int i = 0 ; i < N ; i ++){
	//		used[i] = false;
		//}
		for (int i = 0; i < selected.length; i++) {
			
			//individualChosen = false;
			//while(!individualChosen){
				//boolean found = false;
				rnd = (float) Math.max(Math.random(), 0.000001f);	// rnd should never be 0!
				if (rnd < array[0].qi) {
				//	if(!used[0]){
						individualChosen = true;
						selected[i] = array[0].index;
				//		used[0] = true;
				//	}
				} else {
					individualChosen = false;
					for (int j = 1; !individualChosen && j < selected.length /*&& !found*/; j++) {
						if (rnd > array[j - 1].qi && rnd <= array[j].qi) {
				//			if(!used[j]){
								selected[i] = j;
								individualChosen = true;
			//					used[j] = true;
			//				}
			//				else{
			//					found = true;
			//				}
						}
					}
				//}
			}
		}
		return selected;
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
