package main;

import java.util.HashMap;
import java.util.Map;

import neuronalnetwork.BackPropagation;
import neuronalnetwork.MSE;
import neuronalnetwork.NeuralNetwork;
import neuronalnetwork.function.SgFunction;
import neuronalnetwork.function.TransferFunction;

public class BackpropagationTest {

	public static void main(String[] args) {
		NeuralNetwork net = new NeuralNetwork(new int[] {3, 10, 5, 1});
		TransferFunction f = new SgFunction();
		Map<float[], float[]> examples = getExamples();
		System.out.println("Error before training: " + MSE.calc(net, f, examples));
		BackPropagation b = new BackPropagation(f, 0.1f);
		b.train(net, examples, 100);
		System.out.println("Error after training: " + MSE.calc(net, f, examples));
	}

	private static Map<float[], float[]> getExamples() {
		Map<float[], float[]> examples = new HashMap<float[], float[]>();
		examples.put(new float[] { -1, -1, -1 }, new float[] { -1 });
		examples.put(new float[] { -1, -1, 1 }, new float[] { -1 });
		examples.put(new float[] { -1, 1, -1 }, new float[] { -1 });
		examples.put(new float[] { -1, 1, 1 }, new float[] { -1 });
		examples.put(new float[] { 1, -1, -1 }, new float[] { -1 });
		examples.put(new float[] { 1, -1, 1 }, new float[] { -1 });
		examples.put(new float[] { 1, 1, -1 }, new float[] { -1 });
		examples.put(new float[] { 1, 1, 1 }, new float[] { 1 });
		return examples;
	}
}
