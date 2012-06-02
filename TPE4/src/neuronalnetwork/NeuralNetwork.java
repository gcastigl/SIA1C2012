package neuronalnetwork;

import neuronalnetwork.function.TransferFunction;

public class NeuralNetwork {

	private Layer[] layers;
	
	/**
	 * Crea una red con input de largo structure[0].
	 * Todos los demas valores que se encuentren entre 1 y structure.length - 1 
	 * son creados como capas ocultas.
	 */
	public NeuralNetwork(int[] structure) {
		if (structure.length < 2) {
			throw new IllegalArgumentException("Net must have at least one layer");
		}
		layers = new Layer[structure.length - 1];
		for (int i = 1; i < structure.length; i++) {
			layers[i - 1] = createLayer(structure, i);
		}
	}

	private Layer createLayer(int[] structure, int index) {
		return new Layer(structure[index - 1], structure[index]);
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
