package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class UniversalSelector extends CandidateSelector{

	public UniversalSelector(Configuration config) {
		super(config);
	}

	@Override
	public int[] select() {
		
		int k = (int)(config.G * config.N);
		return selectBestK(k);
		
	}
	
	private int[] selectBestK( int k){
		double F = 0;
		for(Chromosome c: config.population){
			F += c.getFitness();
		}
		
		int[] selected = new int[k];
		double[] cumProb = new double[config.population.length];
		for ( int i = 0 ; i < config.population.length ; i ++){
			cumProb[i] = config.population[i].getFitness()/F;
			if( i != 0 )
				cumProb[i] += cumProb[i-1];
		}
		double g = Math.random() * F;
		for( int i = 0 ; i < k ; i ++){
			boolean chosen = false;
				double r = (g+i)/(double)k;
				for( int j = 0 ; j < config.population.length && !chosen; j++){
					if(r < cumProb[j]){
						selected[i] = j;
						chosen = true;
					}
				}
			}
			
		
		return selected;
	}

	@Override
	public void replace(Chromosome[] childs) {
		int[] selected = selectBestK(config.N - childs.length);
		createNewPopulation(selected, childs);
		
	}

}
