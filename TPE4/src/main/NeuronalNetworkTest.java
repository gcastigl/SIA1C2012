package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import neuronalnetwork.BackPropagation;
import neuronalnetwork.MSE;
import neuronalnetwork.NeuralNetwork;
import neuronalnetwork.TrainItem;
import neuronalnetwork.function.SgFunction;
import neuronalnetwork.function.TransferFunction;

public class NeuronalNetworkTest {

	private static final int INPUT = 2;
	private static final int OUTPUT = 1;
	
	private static TransferFunction f = new SgFunction();
	private static NeuralNetwork net = new NeuralNetwork(new int[] {2, 2, 1});
	
	public static void main(String[] args) {
		List<TrainItem> examples = getAndTrainingPoints();
		evalNet(net, examples, f);
		System.out.println("************************");
		BackPropagation backProp = new BackPropagation(f, 0.1f);
		backProp.train(net, examples, 100);
		evalNet(net, examples, f);
		System.out.println("Termino");
//		float[] output = net.evaluate(input, f);
//		System.out.println(Arrays.toString(output));
	}
	
	private static List<TrainItem> getAndTrainingPoints() {
		List<TrainItem> examples = new ArrayList<TrainItem>();
		examples.add(new TrainItem(INPUT, OUTPUT, -1, -1, -1));
		examples.add(new TrainItem(INPUT, OUTPUT, -1, 1, -1));
		examples.add(new TrainItem(INPUT, OUTPUT, 1, -1, -1));
		examples.add(new TrainItem(INPUT, OUTPUT, 1, 1, 1));
		return examples;
	}

	private static void evalNet(NeuralNetwork net, List<TrainItem> examples, TransferFunction f) {
		for (TrainItem item: examples) {
			float[] output = net.evaluate(item.input, f);
			System.out.println("Eval: " + Arrays.toString(item.input) + 
				" ===> " + Arrays.toString(output));
		}
		System.out.println(MSE.calc(net, f, examples));
	}
}
