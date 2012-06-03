package geneticalgorithm.mutation;

import neuronalnetwork.BackPropagation;
import neuronalnetwork.NetConfiguration;
import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public abstract class MutationMethod {

	protected Configuration config;
	
	public MutationMethod(Configuration config) {
		this.config = config;
	}
	
	public Chromosome[] mutate(Chromosome[] childs) {
		for (Chromosome c: childs) {
			if (config.backpropp > Math.random()) {
				NetConfiguration netConfig = config.netConfig;
				BackPropagation trainer = new BackPropagation(netConfig.f, netConfig.eta);
				trainer.train(c.createIndividual(), netConfig.training, config.maxGen);
			}
		}
		return childs;
	}

}
