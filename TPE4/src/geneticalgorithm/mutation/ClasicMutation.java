package geneticalgorithm.mutation;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.NetSerializer;

public class ClasicMutation extends MutationMethod {

	public ClasicMutation(Configuration config) {
		super(config);
		System.out.println("Mutation value: " + config.pMutate);
	}

	@Override
	public void mutate(Chromosome c) {
		super.mutate(c);
		if (config.pMutate > Math.random()) {
			int randIndex = (int) (Math.random() * NetSerializer.totalConnections(c.getLayers()));
			NetSerializer.toArray(c.getLayers(), netAsArray);
			netAsArray[randIndex] = getMutatation(netAsArray[randIndex]);
		}
	}
	
	private float getMutatation(float value) {
		return value += Math.random();
	}
}
