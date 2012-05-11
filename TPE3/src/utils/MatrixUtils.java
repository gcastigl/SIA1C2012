package utils;
import java.util.Arrays;

public class MatrixUtils {

	/**
	 *
	 * Multiplies a matrix by a vector
	 *
	 * @m1: Float matrix
	 * @m2: Int vector
	 *
	 */ 
	public static float[] multiply(float[][] m1, int[] m2) {
		float[] ans = new float[m2.length];
		for (int i = 0; i < m1.length; i++) {
			float sum = 0;
			for (int j = 0; j < m1[0].length; j++) {
				  sum += m1[i][j] * m2[j];
			}
			ans[i] = sum;
		}
		return ans;
	}
	
	/**
	 *
	 * Nice printed output for a matrix
	 *
	 * @m1: Float input matrix
	 *
	 */ 
	public static void print(float[][] matrix) {
		for (float[] row: matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

}
