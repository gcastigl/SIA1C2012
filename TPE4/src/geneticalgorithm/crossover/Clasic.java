package geneticalgorithm.crossover;

import neuronalnetwork.NetSerializer;
import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;


/**
 * Cruce de un solo punto.
 */
public class Clasic extends CrossoverMethod {
	
	public Clasic(Configuration config) {
		super(config);
	}

	@Override
	protected Chromosome[] cross(Chromosome c1, Chromosome c2) {
		Chromosome[] childs = new Chromosome[2];
		int totalConnections = NetSerializer.totalConnections(c1.getLayers());
		int p = (int) (Math.random() * totalConnections);
		NetSerializer.toArray(c1.getLayers(), netAsArray, 0, p);
		NetSerializer.toArray(c2.getLayers(), netAsArray, p + 1);
		childs[0] = NetSerializer.fromArray(config.netConfig.structure, netAsArray);
		NetSerializer.toArray(c2.getLayers(), netAsArray, 0, p);
		NetSerializer.toArray(c1.getLayers(), netAsArray, p + 1);
		childs[1] = NetSerializer.fromArray(config.netConfig.structure, netAsArray);
		return childs;
	}

}
