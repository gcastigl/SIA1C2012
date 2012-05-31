package neuronalnetwork;

import java.util.Arrays;

import neuronalnetwork.function.SgFunction;
import neuronalnetwork.function.TransferFunction;

public class NeuronalNetworkTest {

	public static void main(String[] args) {
		NeuralNetwork net = new NeuralNetwork(new int[] {2, 1});
		float[] input = new float[] {-1, 1};
		TransferFunction f = new SgFunction();
		float[] output = net.evaluate(input, f);
		System.out.println(Arrays.toString(output));
	}
}
