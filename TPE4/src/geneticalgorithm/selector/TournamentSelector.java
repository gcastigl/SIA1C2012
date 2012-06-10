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
		return selectBestK((int) (config.G * config.N));
	}

	@Override
	protected int[] selectBestK(int k) {
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
		Chromosome c1 = config.population[individual1];
		Chromosome c2 = config.population[individual2];
		int better =  (c1.getFitness() >= c2.getFitness()) ? individual1 : individual2;
		int worst = (better == individual1) ? individual2 : individual1;
		float rnd = (float) Math.random();
		if (rnd < 0.75)
			return better;
		else
			return worst;
	}

	@Override
	public void replace(Chromosome[] childs) {
		int[] selected = selectBestK(config.N - childs.length);
		createNewPopulation(selected, childs);
	}

}
