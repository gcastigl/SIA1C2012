import java.util.Arrays;


public class SimplePerceptron {

	private static float n = 0.2f;
	
	private Neuron[] layer;
	private int input, output;

	public SimplePerceptron(int input, int output) {
		this.input = input;
		this.output = output;
		layer = new Neuron[output];
		for (int i = 0; i < output; i++) {
			layer[i] = new Neuron(input);
		}
	}

	public float[] evaluate(float[] input) {
		float[] out = new float[output];
		for (int i = 0; i < output; i++) {
			out[i] = layer[i].evaluate(input);
		}
		return out;
	}
	
	public void train(float[] inputValues, float[] expectedAnswer) {
		for (int i = 0; i < output; i++) {
			float ans = layer[i].evaluate(inputValues);
			adjustW(layer[i], ans, expectedAnswer[i], inputValues);
		}
	}

	private void adjustW(Neuron neuron, float ans, float expected, float[] inputValues) {
		if (ans != expected) {
			for (int k = 0; k < input; k++) {
				float dw = n * (expected - ans) * inputValues[k];
				neuron.w[k] += dw;
				System.out.println("adjusted neuron w[" + k + "] by " + dw);
			}
		} else {
			System.out.println("No adjusting");
		}
	}

	public Neuron[] getLayer() {
		return layer;
	}
	
	@Override
	public String toString() {
		String s = "SimplePerceptron{";
		s += "input: " + input + ", ";
		s += "output: " + output + ", ";
		s += "Neurons: " + Arrays.toString(layer);
		return s + "}";
	}
}
