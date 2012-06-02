package neuronalnetwork;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import neuronalnetwork.function.SigmoidFunction;
import neuronalnetwork.function.TransferFunction;

public class NeuronalNetworkTest {

	public static void main(String[] args) {
		NeuralNetwork net = new NeuralNetwork(new int[] {2, 1});
		TransferFunction f = new SigmoidFunction();
		Map<float[], float[]> examples = getAndTrainingPoints();
		evalNet(net, examples, f);
		System.out.println("************************");
		BackPropagation backProp = new BackPropagation(f, 0.1f);
		backProp.train(net, examples, 1000);
		evalNet(net, examples, f);
		System.out.println("Termino");
//		float[] output = net.evaluate(input, f);
//		System.out.println(Arrays.toString(output));
	}
	
	private static Map<float[], float[]> getAndTrainingPoints() {
		Map<float[], float[]> examples = new HashMap<float[], float[]>();
		examples.put(new float[] {-1, -1}, new float[] {-1});
		examples.put(new float[] {-1, 1}, new float[] {-1});
		examples.put(new float[] {1, -1}, new float[] {-1});
		examples.put(new float[] {1, 1}, new float[] {1});
		return examples;
	}

	private static void evalNet(NeuralNetwork net, Map<float[], float[]> examples, TransferFunction f) {
		for (Entry<float[], float[]> entry: examples.entrySet()) {
			float[] input = entry.getKey();
			float[] output = net.evaluate(input, f);
			System.out.println("Eval: " + Arrays.toString(input) + " ===> " + Arrays.toString(output));
		}
	}
}
