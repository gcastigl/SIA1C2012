import java.util.Arrays;
import java.util.Random;

public class Neuron {
	private float[] weights;
	private float threshold;

	public Neuron(int inputLenght) {
		Random generator = new Random();
		this.threshold = generator.nextFloat();
		weights = new float[inputLenght];
		for (int i = 0; i < inputLenght; i++) {
			weights[i] = generator.nextFloat() - 0.5f; // [-0.5 , 0.5]
		}
	}

	public float evaluate(float[] input) {
		float total = 0;
		for (int i = 0; i < input.length; i++) {
			total += weights[i] * input[i];
		}
		return (total < threshold) ? 0 : 1;
	}
	
	public void adjustWeights(float n, float expected, float output, float[] inputValues) {
		for (int k = 0; k < weights.length; k++) {
			float dw = n * (expected - output) * inputValues[k];
			weights[k] += dw;
		}
	}
	
	public void adjustThreshold(float n, float expected, float output) {
		threshold += (n * (expected - output) * -1);
	}
	
	@Override
	public String toString() {
		String s = "Neuron {";
		s += Arrays.toString(weights) + ", ";
		s += threshold;
		return s + "}";
	}
}
