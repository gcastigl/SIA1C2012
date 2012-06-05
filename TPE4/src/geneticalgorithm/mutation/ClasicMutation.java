package geneticalgorithm.mutation;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.NetSerializer;

public class ClasicMutation extends MutationMethod {

	public ClasicMutation(Configuration config) {
		super(config);
		if (config.p_mutate <= 0.01) {
			System.out.println("Warning: mutation value is very low: " + config.p_mutate);
		}
	}

	@Override
	public void mutate(Chromosome c) {
		super.mutate(c);
		if (config.p_mutate > Math.random()) {
			int randIndex = (int) (Math.random() * NetSerializer.totalConnections(c.getLayers()));
			NetSerializer.toArray(c.getLayers(), netAsArray);
			netAsArray[randIndex] = getMutatation(netAsArray[randIndex]);
		}
	}
	
	private float getMutatation(float value) {
		return value * 0.9f;
	}
}
