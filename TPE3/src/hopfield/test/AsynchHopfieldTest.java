package hopfield.test;

import hopfield.AsynchHopfieldNet;
import hopfield.Config;
import hopfield.HopfieldNet;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class AsynchHopfieldTest {

	@Test
	public void reconocerPatron() {
		String[] patternNames = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int[] recognize = Config.getImageAsState("line1.png");
		int[] ans = testNet(getPatterns(patternNames), recognize);
		Assert.assertTrue(Arrays.equals(recognize, ans));
	}
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

	private int[][] getPatterns(String[] patternNames) {
		int totalPatterns = patternNames.length;
		int[][] patterns = new int[totalPatterns][];
		for (int i = 0; i < totalPatterns; i++) {
			patterns[i] = Config.getImageAsState(patternNames[i]);
		}
		return patterns;
	}
	
	private int[] testNet(int[][] patterns, int[] recognize) {
		int N = patterns[0].length;
		HopfieldNet net = new AsynchHopfieldNet(N);
		net.storePatterns(patterns);
		net.initialize(recognize);
		return net.iterateUntilConvergence();
	}
}
