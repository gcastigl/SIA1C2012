import java.util.Arrays;
import java.util.Random;

public class Neuron {
	float[] w;
	float t;

	public Neuron(int inputs) {
		Random generator = new Random();
		w = new float[inputs];
		for (int i = 0; i < inputs; i++) {
			w[i] = generator.nextFloat() - 0.5f; // [-0.5 , 0.5]
		}
	}

	public float evaluate(float[] input) {
		float total = 0;
		for (int i = 0; i < input.length; i++) {
			total += w[i] * input[i];
		}
		return (total >= t) ? 1 : -1;
	}

	@Override
	public String toString() {
		String s = "Neuron {";
		s += Arrays.toString(w) + ", ";
		s += "t=" + t;
		return s + "}";
	}
}
