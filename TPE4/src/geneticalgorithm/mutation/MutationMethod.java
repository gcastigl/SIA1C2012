package geneticalgorithm.mutation;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.BackPropagation;
import neuronalnetwork.NetConfiguration;

public abstract class MutationMethod {

	protected Configuration config;
	
	public MutationMethod(Configuration config) {
		this.config = config;
	}
	
	public void mutate(Chromosome[] childs) {
		for (Chromosome c: childs) {
			mutate(c);
		}
	}
	
	public void mutate(Chromosome c) {
		if (config.backpropp > Math.random()) {
			NetConfiguration netConfig = config.netConfig;
			BackPropagation trainer = new BackPropagation(netConfig.f, netConfig.eta);
			trainer.train(c.createIndividual(), netConfig.training, config.maxGen);
		}
	}

}
