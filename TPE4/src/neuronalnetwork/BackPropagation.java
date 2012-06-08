package neuronalnetwork;

import java.util.List;

import neuronalnetwork.function.TransferFunction;

public class BackPropagation {

	protected NeuralNetwork net;
	protected TransferFunction f;
	protected float eta;
	
	public BackPropagation(TransferFunction f, float eta) {
		this.f = f;
		this.eta = eta;
	}
	
	public void train(NeuralNetwork net, List<TrainItem> train, int epochs) {
		this.net = net;
		while (epochs-- > 0) { 
			for (TrainItem item: train) {
				train(item.input, item.output);
			}
		}
	}
	
	protected void train(float[] input, float[] expectedOutput) {
		float[] output = net.evaluate(input, f);
		int k = net.getTotalLayers();
		float[][] deltas = new float[k][];
		// Eval delta for last layer (k)
		k--;								// -1 to make k an index
		deltas[k] = getLastLayerDelta(net.getLayer(k), output, expectedOutput);
		// Eval all deltas for all layers from 0 to k - 1;
		for (int m = k - 1; m >= 0; m--) {
			deltas[m] = getLayerDelta(m, deltas[m + 1]);
		}
		updateUnits(deltas, input);
	}
	
	private float[] getLastLayerDelta(Layer last, float[] output, float[] expectedOutput) {
		float[] delta = new float[last.getNeuronsDim()];
		float[] h = last.getH();
		for (int i = 0; i < delta.length; i++) {
			delta[i] = (expectedOutput[i] - output[i]) * f.valueAtDerivated(h[i]);
		}
		return delta;
	}
	
	private float[] getLayerDelta(int m, float[] nextLayerDelta) {
		Layer curr = net.getLayer(m);
		float[] h = curr.getH();
		float[] delta = new float[curr.getNeuronsDim()];
		
		Layer next = net.getLayer(m + 1);
		float[][] nextLayerWeights = next.getWeights();

		for (int i = 0; i < delta.length; i++) {
			// Por cada neurona j de la capa siguiente, tomo la coneccion entre 
			// la nurona i de esta capa con la nuerona j (nextLayerWeights[j][i])
			float sum = 0;
			for (int j = 0; j < next.getNeuronsDim(); j++) {
				sum += nextLayerWeights[j][i] * nextLayerDelta[j];	
			}
			delta[i] = f.valueAtDerivated(h[i]) * sum;
		}
		return delta;
	}

	private void updateUnits(float[][] deltas, float[] input) {
		for (int layer = 0; layer < deltas.length; layer++) {				// por cada capa
			Layer curr = net.getLayer(layer);
			float[][] weights = curr.getWeights();
			float[] layerH = curr.getH();
			for (int neuron = 0; neuron < weights.length; neuron++) {		// por cada neurona neuron
				float neuronDelta = deltas[layer][neuron];
				float df = f.valueAtDerivated(layerH[neuron]);
				float[] neuronWeights = weights[neuron];
				for (int i = 0; i < weights[neuron].length; i++) {			// por cada coneccion i
					float y = getY(layer, neuron, i, neuronWeights, input);
					weights[neuron][i] += eta * neuronDelta * df * y;
				}
			}
		}
	}
	
	private float getY(int layer, int neuron, int connection, float[] neuronWeights, float[] input) {
		float y;
		boolean lastLayer = isLastLayer(connection, neuronWeights.length);
		if (lastLayer) {
			return -1;
		}
		if (layer == 0) {
			y = input[connection];
		} else {
			float[] prevLeayerH = net.getLayer(layer - 1).getH();
			y = f.valueAt(prevLeayerH[connection]);
		}
		return y;
	}
	
	private boolean isLastLayer(int connection, int arrayLen) {
		return connection == arrayLen - 1;
	}
}
