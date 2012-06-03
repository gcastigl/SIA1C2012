package neuronalnetwork;

import java.util.Arrays;

public class TrainItem {
	
	public float[] input;
	public float[] output;
	
	public TrainItem(int in, int out, float... points) {
		if (in + out != points.length) {
			throw new IllegalArgumentException("Inconsistent parameter data");
		}
		input = Arrays.copyOf(points, in);
		output = Arrays.copyOfRange(points, in, in  + out);
	}
	
	public TrainItem(float[] input, float[] output) {
		this.input = input;
		this.output = output;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += Arrays.toString(input);
		s += Arrays.toString(output);
		return s;
	}
}
