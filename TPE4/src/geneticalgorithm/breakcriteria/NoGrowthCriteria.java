package geneticalgorithm.breakcriteria;

import neuronalnetwork.MSE;
import neuronalnetwork.NetConfiguration;
import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class NoGrowthCriteria extends BreakCriteria{
	
	float lastError;

	public NoGrowthCriteria(Configuration config) {
		super(config);
		
	}

	@Override
	public boolean isFinished() {
		double avg = 0;
		int gens = config.elapsedGen;
		if(!(gens == 1 || gens % 1000 == 0)){
			return false;
		}
			
		NetConfiguration netConfig = config.netConfig;
		for (Chromosome chrom : config.population) {
			avg +=  MSE.calc(chrom.createIndividual(), netConfig.f, netConfig.training);
		}
		avg /= config.population.length;
		if(gens == 1 || avg/lastError < 0.95){
			lastError = (float)avg;
			return false;
		}
		return true;
		
	}

}
