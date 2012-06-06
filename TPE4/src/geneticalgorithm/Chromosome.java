package geneticalgorithm;

import neuronalnetwork.Layer;
import neuronalnetwork.NeuralNetwork;

public class Chromosome {

	private Layer[] layers;
	private float fitness;
	
	public Chromosome(Layer[] layers) {
		this.layers = layers;
	}
	
	public Layer[] getLayers() {
		return layers;
	}

	public float getFitness() {
		return fitness;
	}
	
	public void setFitness(float fitness) {
		this.fitness = fitness;
	}
	
	public NeuralNetwork createIndividual() {
		return new NeuralNetwork(layers);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Chromosome)) {
			return false;
		}
		return equals((Chromosome) obj);
	}
	
	public boolean equals(Chromosome c) {
		if (layers.length != c.layers.length) {
			return false;
		}
		for (int i = 0; i < layers.length; i++) {
			if (layers[i].equals(c.layers[i])) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (Layer l: layers) {
			hashCode += l.hashCode();
		}
		return hashCode;
	}
}
