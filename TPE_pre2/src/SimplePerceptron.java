import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

public class SimplePerceptron {

	private static float n = 0.2f;

	private Neuron[] neurons;

	public SimplePerceptron(int inputLenght, int outputLenght) {
		neurons = new Neuron[outputLenght];
		for (int i = 0; i < outputLenght; i++) {
			neurons[i] = new Neuron(inputLenght);
		}
	}

	public float[] evaluate(float[] input) {
		float[] out = new float[neurons.length];
		for (int i = 0; i < neurons.length; i++) {
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
		for (int i = 0; i < neurons.length; i++) {
			float eval = neurons[i].evaluate(input);
			if (eval != output[i]) {
				neurons[i].adjustWeights(n, output[i], eval, input);
				neurons[i].adjustThreshold(n, output[i], eval);
				updated = true;
			}
		}
		return updated;
	}

	@Override
	public String toString() {
		String s = "SimplePerceptron{";
		s += "Neurons: " + Arrays.toString(neurons) + ", ";
		return s + "}";
	}
}
