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
			neurons[i] = new Neuron(inputLenght, 1.5f);
		}
	}

	public float[] evaluate(float[] input) {
		float[] out = new float[outputLenght];
		for (int i = 0; i < outputLenght; i++) {
			float neuronOutput = neurons[i].evaluate(input);
			float threshold = neurons[i].getThreshold();
			out[i] = (neuronOutput < threshold) ? -1 : 1;
		}
		return out;
	}

	public void train(Map<float[], float[]> triningSet) {
		for (Entry<float[], float[]> traingEntry : triningSet.entrySet()) {
			train(traingEntry.getKey(), traingEntry.getValue());
		}
	}
	
	private void train(float[] inputValues, float[] outputValues) {
		for (int i = 0; i < outputLenght; i++) {
			float output = neurons[i].evaluate(inputValues);
			if (output != outputValues[i]) {
				adjustWeights(neurons[i], outputValues[i], output, inputValues);
				adjustThreshold(neurons[i], outputValues[i], output);					
			}
		}
	}

	private void adjustWeights(Neuron neuron, float expected, float output, float[] inputValues) {
		float[] weights = neuron.getWeights();
		for (int k = 0; k < weights.length; k++) {
			float dw = n * (expected - output) * inputValues[k];
			weights[k] += dw;
			System.out.println("adjusted neuron w[" + k + "] by " + dw);
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
