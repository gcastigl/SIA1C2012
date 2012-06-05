package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import util.MoreMath;

public class TournamentSelector extends CandidateSelector {

	public TournamentSelector(Configuration config) {
		super(config);
	}

	@Override
	public int[] select() {
		return selectK((int) (config.G * config.N));
	}

	private int[] selectK(int k) {
		// Get the best k individuals
		int[] selected = new int[k];
		for (int i = 0; i < selected.length; i++) {
			selected[i] = playTournament();
		}
		return selected;
	}

	private int playTournament() {
		int individual1 = (int) MoreMath.random(0, config.population.length - 1);
		int individual2 = (int) MoreMath.random(0, config.population.length - 1);
		
		int better = config.population[individual1].getFitness() >= config.population[individual2].getFitness() ? individual1:individual2;
		int worst = better == individual1 ? individual2:individual1;
		
		
		float rnd = (float) Math.random();
		
		if(rnd < 0.75)
			return better;
		else
			return worst;
	}

	@Override
	public void replace(Chromosome[] childs) {
		Chromosome[] oldPpulation = config.population;
		Chromosome[] newPopulation = new Chromosome[config.N];
		int[] selected = selectK(config.N - childs.length);
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

}
