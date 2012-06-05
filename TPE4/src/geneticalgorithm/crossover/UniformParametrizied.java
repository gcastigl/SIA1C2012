package geneticalgorithm.crossover;

import geneticalgorithm.Chromosome;
import geneticalgorithm.Configuration;
import neuronalnetwork.NetSerializer;

public class UniformParametrizied extends CrossoverMethod {

	protected float[] net2AsArray;
	
	public UniformParametrizied(Configuration config) {
		super(config);
		int connections = NetSerializer.totalConnections(config.netConfig.structure);
		net2AsArray = new float[connections];
	}

	@Override
	protected Chromosome[] cross(Chromosome c1, Chromosome c2) {
		NetSerializer.toArray(c1.getLayers(), netAsArray);
		NetSerializer.toArray(c2.getLayers(), net2AsArray);
		float p = config.pCross;
		for (int i = 0; i < netAsArray.length; i++) {
			if (p > Math.random()) {
				float aux = netAsArray[i];
				netAsArray[i] = net2AsArray[i];
				net2AsArray[i] = aux;
			}
		}
		Chromosome[] childs = new Chromosome[2];
		childs[0] = NetSerializer.fromArray(config.netConfig.structure, netAsArray);
		childs[1] = NetSerializer.fromArray(config.netConfig.structure, net2AsArray);
		return childs;
	}

}
