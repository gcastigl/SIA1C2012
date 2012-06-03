package geneticalgorithm.crossover;

import util.MoreMath;
import neuronalnetwork.NetSerializer;
import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;

public class Multiple extends CrossoverMethod {

	public Multiple(Configuration config) {
		super(config);
	}

	@Override
	protected Chromosome[] cross(Chromosome c1, Chromosome c2) {
		Chromosome[] childs = new Chromosome[2];
		int totalConnections = NetSerializer.totalConnections(c1.getLayers());
		int r1 = (int) (Math.random() * (totalConnections - 1));
		int r2 = (int) MoreMath.random(r1, totalConnections);
		NetSerializer.toArray(c1.getLayers(), netAsArray, 0, r1);
		NetSerializer.toArray(c2.getLayers(), netAsArray, r1 + 1, r2);
		NetSerializer.toArray(c1.getLayers(), netAsArray, r2);
		childs[0] = NetSerializer.fromArray(config.netConfig.structure, netAsArray);
		NetSerializer.toArray(c2.getLayers(), netAsArray, 0, r1);
		NetSerializer.toArray(c1.getLayers(), netAsArray, r1 + 1, r2);
		NetSerializer.toArray(c2.getLayers(), netAsArray, r2);
		childs[1] = NetSerializer.fromArray(config.netConfig.structure, netAsArray);
		return childs;
	}

	@Override
	public Chromosome[] mutate(Chromosome[] childs) {
		return null;
	}

}
