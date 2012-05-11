package hopfield;

import java.util.Arrays;

import utils.MatrixUtils;

/**
 *
 * This class represents a Synchroneus Network.
 * Remember that Hopfield networks updates its states asynchroneusly.
 *
 */ 	
public class SynchHopfieldNet extends HopfieldNet {

	/**
	 *
	 * Creates a synchroneus hopfield network with N neurons.
	 *
	 * @N: Number of units.
	 */ 
	public SynchHopfieldNet(int N) {
		super(N);
	}
	

	/**
	 *
	 * Initially "states" will contain the pattern which we want the network
	 * to recognize. We will multiply the weights matrix (final) by the states
	 * vector assigning this answer to "states" until we detect a fixed point
	 * (no changes between the previous step and the current step in the vec).
	 * 
	 * Note that we can fall into cycles with lenght 2 and we must prevent
	 * this pitfall.
	 *
	 */ 
	@Override
	public int[] iterateUntilConvergence() {
		boolean finished = false;
		int[] nextStates = null;
		while (!finished) {
			nextStates = sgn(MatrixUtils.multiply(weights, states), states);
			if (Arrays.equals(states, nextStates)) {
				finished = true;
			} else {
				states = nextStates;
			}
		}
		// should never get here
		return nextStates;
	}
	
	/**
	 *
	 * Applies the SGN() function to the input array (vec).
	 * In the case that vec[i] = 0 then the previous value is stored
	 * in that possition.
	 *
	 * @vec: States for each unit of the network
	 * @prevStates: States for each unit of the network. 
	 * The prevStates are used because the original states are beign modified 
	 * and we need the original states in the case that vec[i] = 0
	 * 
	 */ 
	protected int[] sgn(float[] vec, int[] prevStates) {
		int[] ans = new int[vec.length];
		for (int i = 0; i < vec.length; i++) {
			ans[i] = (vec[i] > 0) ? STATE_POSITIVE : 
				((vec[i] < 0) ? STATE_NEGATIVE : prevStates[i]);
		}
		return ans;
	}
}
