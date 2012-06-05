package geneticalgorithm.mutation;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import geneticalgorithm.NetModificator;
import neuronalnetwork.BackPropagation;
import neuronalnetwork.NetConfiguration;

public abstract class MutationMethod extends NetModificator {

	public MutationMethod(Configuration config) {
		super(config);
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
