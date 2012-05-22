package hopfield;

import java.util.Arrays;

import utils.PatternUtils;

public class AsynchHopfieldNetWithParityBit extends AsynchHopfieldNet {

	@Override
	public void storePatterns(int[][] patterns) {
		int[][] new_patterns = new int[patterns.length][patterns[0].length + 1];
		for (int i = 0; i < patterns.length; i++) {
			for (int j = 0; j < patterns[0].length; j++) {
				new_patterns[i][j] = patterns[i][j];
			}
			new_patterns[i][patterns[0].length] = 1;
		}
		super.storePatterns(new_patterns);
	}

	@Override
	public void initialize(int[] recognize) {
		int[] recWithParity = new int[recognize.length + 1];
		for (int i = 0; i < recognize.length; i++) {
			recWithParity[i] = recognize[i];
		}
		recWithParity[recognize.length] = 1;
		super.initialize(recWithParity);
	}

	@Override
	public int[] iterateUntilConvergence() {
		int[] recognized = super.iterateUntilConvergence();
		if (recognized[recognized.length - 1] == -1) {
			PatternUtils.invert(recognized);
		}
		return Arrays.copyOfRange(recognized, 0, recognized.length - 1);
	}
}
