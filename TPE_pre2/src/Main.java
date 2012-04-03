import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		SimplePerceptron perceptron = new SimplePerceptron(2, 1);
		perceptron.getLayer()[0].t = 1.5f;
		trainAnd(perceptron);
		float[] eval = perceptron.evaluate(new float[] { 1, 1 });
		System.out.println(Arrays.toString(eval));
	}

	private static void trainAnd(SimplePerceptron perceptron) {
		float[] input, expected;
		System.out.println(perceptron);
		// 1, 1
		input = new float[] { 1, 1 };
		expected = new float[] { 1 };
		perceptron.train(input, expected);
		System.out.println(perceptron);
		// 0, 0
		input = new float[] { 0, 0 };
		expected = new float[] { 0 };
		perceptron.train(input, expected);
		System.out.println(perceptron);
		// 1, 0
		input = new float[] { 1, 0 };
		expected = new float[] { 0 };
		perceptron.train(input, expected);
		System.out.println(perceptron);
		// 1, 0
		input = new float[] { 0, 1 };
		expected = new float[] { 0 };
		perceptron.train(input, expected);
		System.out.println(perceptron);
	}
}
