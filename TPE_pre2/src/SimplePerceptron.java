import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public class SimplePerceptron {

	private static float n = 0.2f;

	private Neuron[] neurons;
	private int inputLenght, outputLenght;

	public SimplePerceptron(int inputLenght, int outputLenght) {
		this.inputLenght = inputLenght;
		this.outputLenght = outputLenght;
		neurons = new Neuron[outputLenght];
		for (int i = 0; i < outputLenght; i++) {
			neurons[i] = new Neuron(inputLenght);
		}
	}

	public float[] evaluate(float[] input) {
		float[] out = new float[outputLenght];
		for (int i = 0; i < outputLenght; i++) {
			out[i] = neurons[i].evaluate(input);
		}
		return out;
	}

	public void train(Map<float[], float[]> triningSet, int maxIterations) {
		boolean updated = true;
		int n = 0;
		while (updated && n != maxIterations) {
			updated = false;
			for (Entry<float[], float[]> traingEntry : triningSet.entrySet()) {
				updated |= train(traingEntry.getKey(), traingEntry.getValue());
			}
			n++;
		}
		System.out.println("Trained in " +  n + " iterations.");
	}
	
	private boolean train(float[] input, float[] output) {
		boolean updated = false;
		for (int i = 0; i < outputLenght; i++) {
			float eval = neurons[i].evaluate(input);
			if (eval != output[i]) {
				adjustWeights(neurons[i], output[i], eval, input);
				adjustThreshold(neurons[i], output[i], eval);
				updated = true;
			}
		}
		return updated;
	}

	private void adjustWeights(Neuron neuron, float expected, float output, float[] inputValues) {
		float[] weights = neuron.getWeights();
		for (int k = 0; k < weights.length; k++) {
			float dw = n * (expected - output) * inputValues[k];
			weights[k] += dw;
		}
	}
	
	private void adjustThreshold(Neuron neuron, float expected, float output) {
		float threshold = neuron.getThreshold();
		threshold += (n * (expected - output) * -1);
		neuron.setThreshold(threshold);
	}

	public Neuron[] getNeurons() {
		return neurons;
	}

	@Override
	public String toString() {
		String s = "SimplePerceptron{";
		s += "input: " + inputLenght + ", ";
		s += "output: " + outputLenght + ", ";
		s += "Neurons: " + Arrays.toString(neurons) + ", ";
		return s + "}";
	}
}
