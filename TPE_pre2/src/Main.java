import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		SimplePerceptron perceptron = new SimplePerceptron(2, 1);
		trainAnd(perceptron);
		testAnd(perceptron);
	}

	private static void trainAnd(SimplePerceptron perceptron) {
		System.out.println("Before training => " + perceptron);
		Map<float[], float[]> trainng = new HashMap<float[], float[]>();
		trainng.put(new float[] { 1, 1 }, new float[] { 1 });
		trainng.put(new float[] { 0, 0 }, new float[] { 0 });
		trainng.put(new float[] { 1, 0 }, new float[] { 0 });
		trainng.put(new float[] { 0, 1 }, new float[] { 0 });
		perceptron.train(trainng, 100);
		System.out.println("After training => " + perceptron);
	}
	
	private static void testAnd(SimplePerceptron perceptron) {
		Map<float[], float[]> test = new HashMap<float[], float[]>();
		test.put(new float[] { 1, 1 }, new float[] { 1 });
		test.put(new float[] { 0, 0 }, new float[] { 0 });
		test.put(new float[] { 1, 0 }, new float[] { 0 });
		test.put(new float[] { 0, 1 }, new float[] { 0 });
		System.out.println("\nTesting perceptron: " + perceptron);
		for (Entry<float[], float[]> testEntry: test.entrySet()) {
			float[] eval = perceptron.evaluate(testEntry.getKey());
			System.out.println(Arrays.toString(testEntry.getKey()) + 
				" - test passed? " + Arrays.equals(eval, testEntry.getValue()));
		}
		System.out.println("-------------------------");
	}
}
