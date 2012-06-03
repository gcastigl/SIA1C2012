package main;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import neuronalnetwork.BackPropagation;
import neuronalnetwork.MSE;
import neuronalnetwork.NeuralNetwork;
import neuronalnetwork.function.SigmoidFunction;
import neuronalnetwork.function.TransferFunction;

public class BackpropagationTest {

	public static void main(String[] args) {
		NeuralNetwork net = new NeuralNetwork(new int[] {2, 25, 25, 1});
		TransferFunction f = new SigmoidFunction();
		Map<float[], float[]> examples = getExamplesSigmoid();
		System.out.println("Error before training: " + MSE.calc(net, f, examples));
		BackPropagation b = new BackPropagation(f, 0.1f);
		b.train(net, examples, 5000);
		System.out.println("Error after training: " + MSE.calc(net, f, examples));
	}

	private static Map<float[], float[]> getExamplesSg() {
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
	
	private static Map<float[], float[]> getExamplesSigmoid() {
		Map<float[], float[]> examples = new HashMap<float[], float[]>();
		
		examples.put(new float[] {2.6259f,-0.2792f}, new float[] {0.0393f});
		examples.put(new float[] {-0.2701f, -0.8842f}, new float[] {0.2138f});
		examples.put(new float[] {-1.8973f, -0.8842f}, new float[] {0.0121f});
		examples.put(new float[] {-1.3664f, -2.2171f}, new float[] {0.0009f});
		examples.put(new float[] {-1.1384f, 1.2464f}, new float[] {0.8889f});
		examples.put(new float[] {2.1128f, -3.0484f}, new float[] {0.0040f});
		examples.put(new float[] {-1.8973f, 2.0433f}, new float[] {0.1599f});
		examples.put(new float[] {-1.3664f, -1.3962f}, new float[] {0.0059f});
		examples.put(new float[] {0.6269f, -3.0484f}, new float[] {0.0135f});
		examples.put(new float[] { -0.5923f, 1.2464f }, new float[] { 0.7949f });
		normalizeSigmoid(examples);
		return examples;
	}
	
	public static void normalizeSigmoid(Map<float[], float[]> examples) {
		Iterator<Entry<float[], float[]>> it = examples.entrySet().iterator();
		float[] max = it.next().getKey().clone();
		while (it.hasNext()) {
			float[] v = it.next().getKey();
			for (int i = 0; i < max.length; i++) {
				max[i] = Math.max(max[i], Math.abs(v[i]));
			}
		}
		it = examples.entrySet().iterator();
		while (it.hasNext()) {
			float[] v = it.next().getKey();
			for (int i = 0; i < max.length; i++) {
				v[i] /= max[i];
			}
//			System.out.println(Arrays.toString(v));
		}
	}
}
