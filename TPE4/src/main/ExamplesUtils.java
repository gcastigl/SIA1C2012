package main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import neuronalnetwork.TrainItem;

public class ExamplesUtils {

	public static List<TrainItem> loadExamples(String fileName, int inputDim, int outputDim) throws IOException {
		List<TrainItem> allexamples = new LinkedList<TrainItem>();
		DataInputStream in = new DataInputStream(new FileInputStream(fileName));
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			float[] input = new float[inputDim];
			float[] output = new float[outputDim];
			parseLine(strLine, input, output);
			allexamples.add(new TrainItem(input, output));
		}
		in.close();
		return allexamples;
	}
	
	private static void parseLine(String line, float[] in, float[] out) {
		StringTokenizer tokenizer = new StringTokenizer(line.trim());
		for (int i = 0; i < in.length; i++) {
			in[i] = Float.parseFloat(tokenizer.nextToken());
		}
		for (int i = 0; i < out.length; i++) {
			out[i] = Float.parseFloat(tokenizer.nextToken());
		}
	}
	
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
