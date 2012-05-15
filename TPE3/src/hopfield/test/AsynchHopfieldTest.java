package hopfield.test;

import hopfield.AsynchHopfieldNet;
import hopfield.Config;
import hopfield.HopfieldNet;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class AsynchHopfieldTest {
	
	private HopfieldNet net = new AsynchHopfieldNet();
	
	@Test
	public void reconocerPatron() {
		String[] patternNames = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int[] recognize = Config.getImageAsState("line1.png");
		int[] ans = testNet(getPatterns(patternNames), recognize);
		Assert.assertTrue(Arrays.equals(recognize, ans));
	}

	@Test
	public void reconocerPatronConRuido() {
		String[] patternNames = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int[] recognize = Config.getImageAsState("line1.png");
		// add some noise to the image
		int[] noisyImage = recognize.clone();
		for (int i = 0; i < noisyImage.length / 2; i++) {
			noisyImage[i] = HopfieldNet.STATE_NEGATIVE;
		}
		int[] ans = testNet(getPatterns(patternNames), noisyImage);
		Assert.assertTrue(Arrays.equals(recognize, ans));
	}
	
	@Test
	public void noReconocerPatron() {
		String[] patternNames = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int[] recognize = Config.getImageAsState("pacman.png");
		int[] ans = testNet(getPatterns(patternNames), recognize);
		// FIXME: Que se supone que devuelve la red cuando se le da 
		// un patron que nada que ver con los que tiene memorizados??
		Assert.assertFalse(Arrays.equals(recognize, ans));
	}

	@Test
	public void mostrarPatronesInversos() {
		String[] patternNames = {"line1.png", "line2.png", "line3.png", "line4.png"};
		int[][] patterns = getPatterns(patternNames);
		int[][] invertedPatterns = getPatterns(patternNames);
		// invert all pattterns
		for (int[] pattern: invertedPatterns) {
			invertPattern(pattern);
		}
		// FIXME: la red siempre tendria que encontrar siempre el patron 
		// inverso al original?
		boolean allDifferent = false;
		int[][] ans = testNet(patterns, invertedPatterns);
		for (int i = 0; i < ans.length; i++) {
			allDifferent |= Arrays.equals(ans[i], patterns[i]);
		}
		Assert.assertFalse(allDifferent);
	}
	
	private int[][] getPatterns(String[] patternNames) {
		int totalPatterns = patternNames.length;
		int[][] patterns = new int[totalPatterns][];
		for (int i = 0; i < totalPatterns; i++) {
			patterns[i] = Config.getImageAsState(patternNames[i]);
		}
		return patterns;
	}
	
	private void invertPattern(int[] pattern) {
		for (int i = 0; i < pattern.length; i++) {
			pattern[i] = (pattern[i] == HopfieldNet.STATE_NEGATIVE) ? 
				HopfieldNet.STATE_POSITIVE : HopfieldNet.STATE_NEGATIVE;
		}
	}
	
	private int[] testNet(int[][] patterns, int[] recognize) {
		net.storePatterns(patterns);
		net.initialize(recognize);
		return net.iterateUntilConvergence();
	}
	
	private int[][] testNet(int[][] patterns, int[][] recognize) {
		net.storePatterns(patterns);
		int[][] ans = new int[recognize.length][];
		for (int i = 0; i < recognize.length; i++) {
			net.initialize(recognize[i]);
			ans[i] = net.iterateUntilConvergence();
		}
		return ans;
	}
}
