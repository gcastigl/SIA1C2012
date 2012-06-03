package neuronalnetwork;

import java.util.Map;
import java.util.Map.Entry;

import neuronalnetwork.function.TransferFunction;
import util.MoreMath;

public class BackPropagation {

	private NeuralNetwork net;
	private TransferFunction f;
	private float eta;
	
	public BackPropagation(TransferFunction f, float eta) {
		this.f = f;
		this.eta = eta;
	}
	
	public void train(NeuralNetwork net, Map<float[], float[]> train, int epochs) {
		this.net = net;
		while (epochs-- > 0) { 
			for (Entry<float[], float[]> entryTrain: train.entrySet()) {
				float[] input = entryTrain.getKey();
				float[] expectedOutput = entryTrain.getValue();
				train(input, expectedOutput);
			}
		}
	}
	
	private void train(float[] input, float[] expectedOutput) {
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
		float[][] nextLayerWeights = net.getLayer(m + 1).getWeights();

		for (int i = 0; i < delta.length; i++) {
			float sum = MoreMath.dotProduct(nextLayerWeights[i], nextLayerDelta);
			delta[i] = f.valueAtDerivated(h[i]) * sum;
		}
		return delta;
	}

	private void updateUnits(float[][] deltas, float[] input) {
		for (int m = 0; m < deltas.length; m++) {
			Layer curr = net.getLayer(m);
			float[][] weights = curr.getWeights();
			float[] bias = curr.getBias();
			// update connections
			for (int i = 0; i < curr.getNeuronsDim(); i++) {
				for (int j = 0; j < curr.getInputLen(); j++) {
					float delta = getDelta(deltas, m, i, j, input);
					weights[j][i] += delta;
				}
				// update bias
				float biasDelta = eta * deltas[m][i] * (-1);
				bias[i] += biasDelta;
			}
		}
	}
	
	private float getDelta(float[][] deltas, int layer, int neuron, int in, float[] input) {
		float[] h = net.getLayer(layer).getH();
		float v;
		if (layer == 0) {
			v = input[in];
		} else {
			v = f.valueAt(h[neuron]);
		}
		return eta * deltas[layer][neuron] * v;
	}
}
