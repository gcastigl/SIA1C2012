package hopfield.test;

import hopfield.AsynchHopfieldNet;
import hopfield.Config;
import hopfield.HopfieldNet;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class AsynchHopfieldTest {

	@Test
	public void reconocerPatron() {
		String[] patternName = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int totalPatterns = patternName.length;
		int[][] patterns = new int[totalPatterns][];
		for (int i = 0; i < totalPatterns; i++) {
			patterns[i] = Config.getImageAsState(patternName[i]);
		}
		
		int N = patterns[0].length;
		HopfieldNet net = new AsynchHopfieldNet(N);
		net.storePatterns(patterns);
		int[] recognize = Config.getImageAsState("line1.png");
		net.initialize(recognize);
		int[] ans = net.iterateUntilConvergence();
		Assert.assertTrue(Arrays.equals(recognize, ans));
	}
	
	@Test
	public void noReconocerPatron() {
		String[] patternName = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int totalPatterns = patternName.length;
		int[][] patterns = new int[totalPatterns][];
		for (int i = 0; i < totalPatterns; i++) {
			patterns[i] = Config.getImageAsState(patternName[i]);
		}
		
		int N = patterns[0].length;
		HopfieldNet net = new AsynchHopfieldNet(N);
		net.storePatterns(patterns);
		int[] recognize = Config.getImageAsState("pacman.png");
		net.initialize(recognize);
		int[] ans = net.iterateUntilConvergence();
		// FIXME: Que se supone que devuelve la red cuando se le da 
		// un patron que nada que ver con los que tiene memorizados??
		Assert.assertFalse(Arrays.equals(recognize, ans));
	}
}
