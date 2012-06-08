package main;

import java.io.IOException;
import java.util.List;

import neuronalnetwork.BackPropagation;
import neuronalnetwork.MSE;
import neuronalnetwork.NeuralNetwork;
import neuronalnetwork.TrainItem;
import neuronalnetwork.function.TanhFunction;
import neuronalnetwork.function.TransferFunction;

public class BackpropagationTest {
	
	private static NeuralNetwork net;
	
	public static void main(String[] args) throws IOException {
		List<TrainItem> examples = getExamplesTp();		
		TransferFunction f = new TanhFunction();
		ExamplesUtils.normalizeTanh(examples);
		
		System.out.println("Error before training: " + MSE.calc(net, f, examples));
		BackPropagation b = new BackPropagation(f, 0.1f);
		b.train(net, examples, 500);
		System.out.println("Error after training: " + MSE.calc(net, f, examples));
	}
	
	private static List<TrainItem> getExamplesTp() throws IOException {
		int input = 2;
		int output = 1;
		net = new NeuralNetwork(new int[] {input, 15, 15, 10, output});
		return ExamplesUtils.loadExamples("./TPE4/res/examples.txt", input, output);
	}

}
