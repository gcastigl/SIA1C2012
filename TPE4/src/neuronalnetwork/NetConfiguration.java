package neuronalnetwork;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import neuronalnetwork.function.TransferFunction;

public class NetConfiguration {

	private static final String examplesFile = "./TPE4/res/examples.txt";

	public int[] structure;
	public TransferFunction f;
	public Map<float[], float[]> training;
	public Map<float[], float[]> testing;

	// Procentaje de los ejemplos para usar para training; los demas son para
	// testing
	public float p = 0.5f;

	/**
	 * Lee los ejemplos del archivo examplesFile y los carga en los aprametros
	 * training y testing de acuerdo a p.
	 */
	public void initialize() throws IOException {
		Map<float[], float[]> allexamples = new HashMap<float[], float[]>();
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(
					examplesFile));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				// FIXME: Hardcoded values!
				float[] input = new float[2];
				float[] output = new float[1];
				parseLine(strLine, input, output);
				allexamples.put(input, output);
			}
			divideExamples(allexamples);
			in.close();
		} catch (IOException e) {
			System.out.println("File: " + examplesFile
					+ " could not be loaded. " + e.getMessage());
			throw e;
		}
	}

	private void parseLine(String line, float[] in, float[] out) {
		StringTokenizer tokenizer = new StringTokenizer(line.trim());
		for (int i = 0; i < in.length; i++) {
			in[i] = Float.parseFloat(tokenizer.nextToken());
		}
		for (int i = 0; i < out.length; i++) {
			out[i] = Float.parseFloat(tokenizer.nextToken());
		}
	}

	private void divideExamples(Map<float[], float[]> examples) {
		training = new HashMap<float[], float[]>();
		testing = new HashMap<float[], float[]>();
		int forTraining = (int) (examples.size() * p);
		Iterator<Entry<float[], float[]>> it = examples.entrySet().iterator();
		for (int i = 0; i < forTraining; i++) {
			Entry<float[], float[]> entry = it.next();
			training.put(entry.getKey(), entry.getValue());
		}
		while (it.hasNext()) {
			Entry<float[], float[]> entry = it.next();
			testing.put(entry.getKey(), entry.getValue());
		}
	}
}
