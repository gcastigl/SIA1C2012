package geneticalgorithm.breakcriteria;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.MSE;
import neuronalnetwork.NetConfiguration;

public class MinErrorBreakCriteria extends BreakCriteria {

	public MinErrorBreakCriteria(Configuration config) {
		super(config);
		System.out.println("\tMin error criteria: " + config.minError_breakCriteria);
	}

	@Override
	public boolean isFinished() {
		double least = Double.MAX_VALUE;
		NetConfiguration netConfig = config.netConfig;
		for (Chromosome chrom : config.population) {
			double aux =  MSE.calc(chrom.createIndividual(), netConfig.f, netConfig.training);
			
			if( aux < least)
				least = aux;
		}
		System.out.println("MSE:"  + least);
		return least < (double)config.minError_breakCriteria;
	}

}
