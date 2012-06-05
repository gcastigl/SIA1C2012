package main;

import java.util.List;

import neuronalnetwork.TrainItem;

public class ExamplesNormalizer {

	public static void normalizeSigmoid(List<TrainItem> examples) {
		float[] max = max(examples);
		for (TrainItem item: examples) {
			float[] v = item.input;
			for (int i = 0; i < max.length; i++) {
				v[i] /= (2 * max[i]);	// [-0.5, 0.5]
				v[i] += 0.5f;			// [0, 1]
			}
//			System.out.println(Arrays.toString(v));
		}
	}
	
	public static void normalizeTanh(List<TrainItem> examples) {
		float[] max = max(examples);
		for (TrainItem item: examples) {
			float[] v = item.input;
			for (int i = 0; i < max.length; i++) {
				v[i] /= (max[i]);	// [-1, 1]
			}
//			System.out.println(Arrays.toString(v));
		}
	}
	
	private static float[] max(List<TrainItem> examples) {
		float[] max = examples.get(0).input.clone();
		for (TrainItem item: examples) {
			float[] v = item.input;
			for (int i = 0; i < max.length; i++) {
				max[i] = Math.max(max[i], Math.abs(v[i]));
			}
		}
		return max;
	}
}