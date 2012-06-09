package geneticalgorithm.selector;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class BoltzmanSelector extends CandidateSelector{
	
	private final double MAX_T = 10000.0;
	private final double MIN_T = 1000.0;
	private final double DEC_RATE = 1.0;
	
	public BoltzmanSelector(Configuration config) {
		super(config);
	}

	@Override
	public void replace(Chromosome[] childs) {
		int[] selected = selectBestK(config.N - childs.length);
		createNewPopulation(selected, childs);
	}

	@Override
	public int[] select() {
		
		int k = (int)(config.G * config.N);
		return selectBestK(k);
		
	}
	
	private int[] selectBestK( int k){
		double T = getTemperature(config.elapsedGen);
		double avg = 0;
		for(Chromosome c: config.population){
			avg += Math.exp(c.getFitness()/T);
		}
		
		int[] selected = new int[k];
		double[] cumProb = new double[config.population.length];
		boolean used[] = new boolean[config.population.length];
		for ( int i = 0 ; i < config.population.length ; i ++){
			cumProb[i] = Math.exp(config.population[i].getFitness()/T)/avg;
			used[i] = false;
			if( i != 0 )
				cumProb[i] += cumProb[i-1];
		}
		for( int i = 0 ; i < k ; i ++){
			boolean chosen = false;
			while(!chosen){
				chosen = false;
				double r = Math.random();
				boolean found = false;
				for( int j = 0 ; j < config.population.length && !chosen && !found ; j++){
					if(r < cumProb[j]){
						if( used[j]){
							found = true;
						}
						else{
							chosen = true;
							selected[i] = j;
							used[j] = true;
						}
						
					}
				}
			}
			
		}
		return selected;
	}
	
	private double getTemperature(int generations){
		double ret = MAX_T - generations * DEC_RATE;
		if( ret < MIN_T)
			return MIN_T;
		return ret;
	}

}
