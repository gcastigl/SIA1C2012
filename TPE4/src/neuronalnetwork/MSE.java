package neuronalnetwork;

import java.util.Map;
import java.util.Map.Entry;

import neuronalnetwork.function.TransferFunction;

public class MSE {

	public static float calc(NeuralNetwork net, TransferFunction f,
			Map<float[], float[]> testExamples) {
		float mse = 0;
		for (Entry<float[], float[]> test : testExamples.entrySet()) {
			mse += calc(net, f, test.getKey(), test.getValue());
		}
		return mse / 2;
	}

	public static float calc(NeuralNetwork net, TransferFunction f,
			float[] input, float[] expectedOutput) {
		float[] outout = net.evaluate(input, f);
		float mse = 0;
		for (int i = 0; i < outout.length; i++) {
			float diff = expectedOutput[i] - outout[i];
			mse += diff * diff;
		}
		return mse;
	}
}
