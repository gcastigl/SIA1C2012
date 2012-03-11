package edificios;


public class BuildingParser {
	
	public static Board parse(String filename) {
		return board4_2();
	}
	
	private static Board board5() {
		int[][] rules = new int[4][];
		rules[Settings.TOP] = new int[] {2, 5, 2, 2, 1};
		rules[Settings.BOTTOM] = new int[] {3, 1, 2, 2, 4};
		rules[Settings.LEFT] = new int[] {4, 1, 2, 3, 2};
		rules[Settings.RIGHT] = new int[] {1, 2, 2, 2, 4};
		Settings.restrictions = rules;
		return new Board(rules[0].length);
	}

	private static Board board4() {
		int[][] rules = new int[4][];
		rules[Settings.TOP] = new int[] {4, 2, 2, 1};
		rules[Settings.BOTTOM] = new int[] {1, 2, 2, 4};
		rules[Settings.LEFT] = new int[] {4, 2, 2, 1};
		rules[Settings.RIGHT] = new int[] {1, 2, 2, 4};
		Settings.restrictions = rules;
		return new Board(rules[0].length);
	}
	
	private static Board board4_2() {	
		// version of the board4() but TOP <-> BOTTOM and LEFT <-> RIGHT
		int[][] rules = new int[4][];
		rules[Settings.BOTTOM] = new int[] {4, 2, 2, 1};
		rules[Settings.TOP] = new int[] {1, 2, 2, 4};
		rules[Settings.RIGHT] = new int[] {4, 2, 2, 1};
		rules[Settings.LEFT] = new int[] {1, 2, 2, 4};
		Settings.restrictions = rules;
		return new Board(rules[0].length);
	}
	
	private static Board board3() {
		int[][] rules = {{1,2,2},{3,1,2},{1,2,2}, {3,1,2}};
		Settings.restrictions = rules;
		return new Board(rules[0].length);
	}

	private static Board board2() {
		int[][] rules = new int[4][];
		rules[Settings.TOP] = new int[] {2, 1};
		rules[Settings.BOTTOM] = new int[] {1, 2};
		rules[Settings.LEFT] = new int[] {2, 1};
		rules[Settings.RIGHT] = new int[] {1, 2};
		Settings.restrictions = rules;
		return new Board(rules[0].length);
	}
}
