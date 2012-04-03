import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		SimplePerceptron perceptron = new SimplePerceptron(2, 1);
		trainAnd(perceptron);
		float[] eval = perceptron.evaluate(new float[] { 1, 1 });
		System.out.println(Arrays.toString(eval));
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
}
