package geneticalgorithm.breakcriteria;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.MSE;
import neuronalnetwork.NetConfiguration;

public class MinErrorBreakCriteria extends BreakCriteria {

	double quota = 0.001;

	public MinErrorBreakCriteria(Configuration config) {
		super(config);
	}

	@Override
	public boolean isFinished() {
		double acum = 0;
		NetConfiguration netConfig = config.netConfig;
		for (Chromosome chrom : config.population) {
			acum += MSE.calc(chrom.createIndividual(), netConfig.f, netConfig.training);
		}
		return acum < quota;
	}

}
