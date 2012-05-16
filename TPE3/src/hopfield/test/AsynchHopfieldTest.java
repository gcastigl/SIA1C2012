package hopfield.test;

import hopfield.AsynchHopfieldNet;
import hopfield.Config;
import hopfield.HopfieldNet;

import java.util.Arrays;

import utils.PatternUtils;

public class AsynchHopfieldTest {
	
	private HopfieldNet net = new AsynchHopfieldNet();
	
	public boolean reconocerPatron() {
		String[] patternNames = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int[] recognize = Config.getImageAsState("line1.png");
		int[] ans = testNet(getPatterns(patternNames), recognize);
		return Arrays.equals(recognize, ans);
	}

	public boolean reconocerPatronConRuido() {
		String[] patternNames = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int[][] patterns = getPatterns(patternNames);
		int[] recognize = Config.getImageAsState("line1.png");
		int[] noisyImage = recognize.clone(); 
		PatternUtils.addNoise1(noisyImage);
		int[] ans = testNet(patterns, noisyImage);
		return Arrays.equals(recognize, ans);
	}
	
	public boolean noReconocerPatron() {
		String[] patternNames = {"h.png", "line1.png", "line2.png", "line3.png", "line4.png"};
		int[] recognize = Config.getImageAsState("pacman.png");
		int[] ans = testNet(getPatterns(patternNames), recognize);
		// FIXME: Que se supone que devuelve la red cuando se le da 
		// un patron que nada que ver con los que tiene memorizados??
		return Arrays.equals(recognize, ans);
	}

	public boolean mostrarPatronesInversos() {
		String[] patternNames = {"line1.png", "line2.png", "line3.png", "line4.png"};
		int[][] patterns = getPatterns(patternNames);
		int[][] invertedPatterns = getPatterns(patternNames);
		// invert all pattterns
		for (int[] pattern: invertedPatterns) {
			PatternUtils.invert(pattern);
		}
		// FIXME: la red siempre tendria que encontrar siempre el patron 
		// inverso al original?
		boolean allDifferent = false;
		int[][] ans = testNet(patterns, invertedPatterns);
		for (int i = 0; i < ans.length; i++) {
			allDifferent |= Arrays.equals(ans[i], patterns[i]);
		}
		return allDifferent;
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
