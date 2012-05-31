package neuronalnetwork;

import neuronalnetwork.function.TransferFunction;

public class NeuralNetwork {

	private Layer[] layers;
	
	/**
	 * Crea una red con el structure[0] input y structure[structure.length - 1] 
	 * output.
	 * Todos los demas valores que se encuentren entre 0 y structure.length - 1 
	 * son creados como capas ocultas.
	 */
	public NeuralNetwork(int[] structure) {
		if (structure.length < 2) {
			throw new IllegalArgumentException("Net must have at least one layer");
		}
		layers = new Layer[structure.length];
		for (int i = 0; i < structure.length; i++) {
			layers[i] = createLayer(structure, i);
		}
	}

	private Layer createLayer(int[] structure, int index) {
		int neurons = structure[index];
		if (index == structure.length - 1) {
			// Last layer is created as Output Layer
			return new Layer(neurons);
		}
		return new Layer(neurons, structure[index + 1]);
	}
	
	public float[] evaluate(float[] input, TransferFunction f) {
		float[] aux = input;
		for(Layer l: layers) {
			aux = l.evaluate(aux, f);
		}
		return aux;
	}
	
	public Layer getLayer(int index) {
		return layers[index];
	}
	
	public Layer[] getLayers() {
		return layers;
	}
	
	public int getTotalLayers() {
		return layers.length;
	}
}
