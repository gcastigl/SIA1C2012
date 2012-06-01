package geneticalgorithm;

import neuronalnetwork.Layer;

public class Chromosome {

	private Layer[] layers;
	
	public Chromosome(Layer[] layers) {
		this.layers = layers;
	}
	
	public Layer[] getLayers() {
		return layers;
	}

	public float fitnes() {
		return 0;
	}
}
