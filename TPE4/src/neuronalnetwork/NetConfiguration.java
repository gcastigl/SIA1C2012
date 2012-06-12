package neuronalnetwork;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.ExamplesUtils;
import neuronalnetwork.function.TransferFunction;

public class NetConfiguration {

	public static final String examplesFile = "./TPE4/res/examples.txt";
	public int[] structure;
	public TransferFunction f;
	public int epochs;
	public float eta;
	public List<TrainItem> training;
	public List<TrainItem> testing;
	public List<TrainItem> allexamples;
	
	// Procentaje de los ejemplos para usar para training; los demas son para
	// testing
	public float p;

	/**
	 * Lee los ejemplos del archivo examplesFile y los carga en los aprametros
	 * training y testing de acuerdo a p.
	 */
	public void initialize() throws IOException {
		int inputDim = structure[0];
		int outputDim = structure[structure.length - 1];
		try {
			allexamples = ExamplesUtils.loadExamples(examplesFile, inputDim, outputDim);
			// Shuffle set before dividing the examples!
			Collections.shuffle(allexamples);
			divideExamples(allexamples);
		} catch (IOException e) {
			System.out.println("File: " + examplesFile + " could not be loaded. " + e.getMessage());
			throw e;
		}
	}

	private void divideExamples(List<TrainItem> examples) {
		int forTraining = (int) (examples.size() * p);
		training = new ArrayList<TrainItem>();
		testing = new ArrayList<TrainItem>();
		int offset = 0;
		for (TrainItem item: examples) {
			if (offset < forTraining) {
				training.add(item);
			} else {
				testing.add(item);
			}
			offset++;
		}
	}
}
