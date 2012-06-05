package main;

import java.util.ArrayList;
import java.util.List;

import neuronalnetwork.BackPropagation;
import neuronalnetwork.MSE;
import neuronalnetwork.NeuralNetwork;
import neuronalnetwork.TrainItem;
import neuronalnetwork.function.TanhFunction;
import neuronalnetwork.function.TransferFunction;

public class BackpropagationTest {

	private static int INPUT;
	private static int OUTPUT;
	
	public static void main(String[] args) {
		List<TrainItem> examples = getExamplesTp();		
		TransferFunction f = new TanhFunction();
		ExamplesNormalizer.normalizeTanh(examples);
		
		NeuralNetwork net = new NeuralNetwork(new int[] {INPUT, 8, 8, OUTPUT});
		
		
		System.out.println("Error before training: " + MSE.calc(net, f, examples));
		BackPropagation b = new BackPropagation(f, 0.1f);
		b.train(net, examples, 1000);
		System.out.println("Error after training: " + MSE.calc(net, f, examples));
	}

	private static List<TrainItem> getExamplesSg() {
		List<TrainItem> examples = new ArrayList<TrainItem>();
		INPUT = 3;
		OUTPUT = 1;
		examples.add(new TrainItem(INPUT, OUTPUT, -1, -1, -1,	-1));
		examples.add(new TrainItem(INPUT, OUTPUT, -1, -1, 1,	-1));
		examples.add(new TrainItem(INPUT, OUTPUT, -1, 1, -1, 	-1));
		examples.add(new TrainItem(INPUT, OUTPUT, -1, 1, 1, 	-1));
		examples.add(new TrainItem(INPUT, OUTPUT, 1, -1, -1,	-1));
		examples.add(new TrainItem(INPUT, OUTPUT, 1, -1, 1, 	-1 ));
		examples.add(new TrainItem(INPUT, OUTPUT, 1, 1, -1, 	-1));
		examples.add(new TrainItem(INPUT, OUTPUT, 1, 1, 1, 		1 ));
		return examples;
	}
	
	private static List<TrainItem> getExamplesTp() {
		List<TrainItem> examples = new ArrayList<TrainItem>();
		INPUT = 2;
		OUTPUT = 1;
		examples.add(new TrainItem(INPUT, OUTPUT, 2.6259f,-0.2792f, 0.0393f));
		examples.add(new TrainItem(INPUT, OUTPUT, -0.2701f, -0.8842f, 0.2138f));
		examples.add(new TrainItem(INPUT, OUTPUT, -1.8973f, -0.8842f, 0.0121f));
		examples.add(new TrainItem(INPUT, OUTPUT, -1.3664f, -2.2171f, 0.0009f));
		examples.add(new TrainItem(INPUT, OUTPUT, -1.1384f, 1.2464f, 0.8889f));
		examples.add(new TrainItem(INPUT, OUTPUT, 2.1128f, -3.0484f, 0.0040f));
		examples.add(new TrainItem(INPUT, OUTPUT, -1.8973f, 2.0433f, 0.1599f));
		examples.add(new TrainItem(INPUT, OUTPUT, -1.3664f, -1.3962f, 0.0059f));
		examples.add(new TrainItem(INPUT, OUTPUT, 0.6269f, -3.0484f, 0.0135f));
		examples.add(new TrainItem(INPUT, OUTPUT, -0.5923f, 1.2464f ,  0.7949f));
		return examples;
	}

}
