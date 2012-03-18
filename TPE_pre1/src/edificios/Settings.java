package edificios;


/**
 * 
 * Simple struct containing static redundant data that does not belong in the states.
 *
 */

public class Settings {
	public static final int TOP = 0;
	public static final int BOTTOM = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public static int[][] restrictions;

	
	public static int PATHSTRATEGY;
	public static final int SEQUENCE = 0;
	public static final int SPIRAL = 1;
	public static final int MRV = 2;
}
