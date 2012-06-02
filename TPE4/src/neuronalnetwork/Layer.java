package neuronalnetwork;

import neuronalnetwork.function.TransferFunction;
import util.MoreMath;

public class Layer {

	private int neurons;
	private int inputLen;
	
	private float[][] weights;
	private float[] bias;			// Bias for each neuron
	
	// output before applying f() - Used for backpropagation
	private float[] h;
	
	public Layer(int inputLen, int neurons) {
		this.neurons = neurons;
		this.inputLen = inputLen;
		h = new float[neurons];
		weights = new float[inputLen][neurons];
		bias = new float[neurons];
		initWeights();
	}

	private void initWeights() {
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights[0].length; j++) {
				weights[i][j] = MoreMath.random(-0.5f, 0.5f);
			}
		}
		for (int i = 0; i < neurons; i++) {			
			bias[i] = MoreMath.random(-0.5f, 0.5f);
		}
	}

	public float[] evaluate(float[] in, TransferFunction f) {
		validateInputDimention(in.length);
		float[] output = new float[neurons];
		for (int i = 0; i < neurons; i++) {
			float sum = 0;
			for (int j = 0; j < inputLen; j++) {
				sum += weights[j][i] * in[j];
			}
			sum -= bias[i]; // Bias
			h[i] = sum;
			output[i] = f.valueAt(sum);
		}
		return output;
	}

	private void validateInputDimention(int in) {
		if (in != inputLen) {
			throw new IllegalArgumentException(
				"Invalid input dimention given: " + in + "but should be " + inputLen);
		}
	}

	public float[] getH() {
		return h;
	}

	public float[][] getWeights() {
		return weights;
	}

	public float[] getBias() {
		return bias;
	}
	
	public int getNeuronsDim() {
		return neurons;
	}

	public int getInputLen() {
		return inputLen;
	}
}
