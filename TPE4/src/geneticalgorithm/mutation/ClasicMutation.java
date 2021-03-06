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
			Chromosome mutated = buildChormosome();
			c.setTo(mutated);
		}
	}
	
	private float getMutatation(float value) {
		float a = (float)(0.75 +  Math.random() / 2.0);
		return value*a + (float)( Math.random() - 0.5);
	}
}
