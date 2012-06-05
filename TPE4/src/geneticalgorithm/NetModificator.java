package geneticalgorithm;

import neuronalnetwork.NetSerializer;

public abstract class NetModificator {

	protected float[] netAsArray;
	protected Configuration config;
	
	public NetModificator(Configuration config) {
		this.config = config;
		int connections = NetSerializer.totalConnections(config.netConfig.structure);
		netAsArray = new float[connections];
	}
	
	/**
	 * Construye el cromosoma guardado en netAsArray
	 */
	protected Chromosome buildChormosome() {
		return NetSerializer.fromArray(config.netConfig.structure, netAsArray);
	}
}
