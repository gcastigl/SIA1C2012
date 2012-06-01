package neuronalnetwork;

import java.util.Map;
import java.util.Map.Entry;

import neuronalnetwork.function.TransferFunction;

public class BackPropagation {

	private NeuralNetwork net;
	private TransferFunction f;
	private float eta;
	
	public BackPropagation(TransferFunction f, float eta) {
		this.f = f;
		this.eta = eta;
	}
	
	public void train(NeuralNetwork net, Map<float[], float[]> train) {
		this.net = net;
		for (Entry<float[], float[]> entryTrain: train.entrySet()) {
			float[] input = entryTrain.getKey();
			float[] expectedOutput = entryTrain.getValue();
//			train(input, expectedOutput);
		}
	}
	
	private void train(float[] input, float[] expectedOutput) {
		float[] output = net.evaluate(input, f);
		float[][] deltas = new float[net.getTotalLayers()][];
		int k = net.getTotalLayers() - 1;
		Layer l = net.getLayer(k);
		deltas[k] = getLastLayerDelta(l, output, expectedOutput);
		for (int m = k - 1; m > 0; m--) {
//			deltas[m] = getLayerDelta(m, output, expectedOutput, nextDelta);
		}
	}
	
	private float[] getLastLayerDelta(Layer last, float[] output, float[] expectedOutput) {
		float[] delta = new float[last.getNeurons()];
		float[] h = last.getH();
		for (int i = 0; i < delta.length; i++) {
			delta[i] = (expectedOutput[i] - output[i]) * f.valueAtDerivated(h[i]);
		}
		return delta;
	}
	
	private float[] getLayerDelta(int m, float[] output, float[] expectedOutput, float[] nextDelta) {
		Layer layer = net.getLayer(m);
		float[] delta = new float[layer.getNeurons()];
		float[] h = net.getLayer(m + 1).getH();
		for (int i = 0; i < delta.length; i++) {
			delta[i] = f.valueAtDerivated(h[i]);
		}
		return delta;
	}
	
	private float[] getDesiredOutput(Layer layer, float[] deltas, Layer nextLayer) {
		float[] out = new float[layer.getNeurons()];
		float[][] weigths = layer.getWeights();
		for (int j = 0; j < weigths.length; j++) {
			
		}
		return out;
	}
}
