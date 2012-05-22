package hopfield;

import Jama.Matrix;

public class PseudoInverseHopfield extends AsynchHopfieldNetWithParityBit {

	Matrix inverseOverlapMatrix = null;

	@Override
	protected void setWeight(int i, int j, int[][] patterns) {
		if (inverseOverlapMatrix == null)
			calculateInverseMatrix(patterns);
		int P = patterns.length;
		weights[i][j] = 0;
		if (i != j) {
			for (int k = 0; k < P; k++) {
				for (int l = 0; l <= k; l++) {
					weights[i][j] += patterns[k][i] * patterns[l][j] * inverseOverlapMatrix.get(k, l);
				}
			}
			weights[i][j] /= weights[0].length;
			// mirrored matrix!
			weights[j][i] = weights[i][j];
		}
	}

	private void calculateInverseMatrix(int[][] patterns) {
		int P = patterns.length;
		Matrix overlapMatrix = new Matrix(new double[P][P]);
		for (int i = 0; i < P; i++) {
			for (int j = 0; j <= i; j++) {
				double toStore = ((double) dotProduct(patterns[i], patterns[j])) / P;
				overlapMatrix.set(i, j, toStore);
				overlapMatrix.set(j, i, toStore);
			}
		}
		inverseOverlapMatrix = overlapMatrix.inverse();
		overlapMatrix = null; // free the matrix
	}

	private int dotProduct(int[] p1, int[] p2) {
		int k, acum = 0;
		for (k = 0; k < p1.length; k++) {
			acum += p1[k] * p2[k];
		}
		return acum;
	}

}
