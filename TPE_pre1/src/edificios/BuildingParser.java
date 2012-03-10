package edificios;


public class BuildingParser {
	
	public static Board parse(String filename){
		return board1();
	}
	
	private static Board board1() {
		int[][] rules = new int[4][];
		rules[Settings.TOP] = new int[] {2, 5, 2, 2, 1};
		rules[Settings.BOTTOM] = new int[] {3, 1, 2, 2, 4};
		rules[Settings.LEFT] = new int[] {4, 1, 2, 3, 2};
		rules[Settings.RIGHT] = new int[] {1, 2, 2, 2, 4};
		Settings.restrictions = rules;
		Board parsed = new Board(rules[0].length);
		return parsed;
	}

/*
	private static Board board1() {
		int[][] rules = {{1,2,2},{3,1,2},{1,2,2}, {3,1,2}};
		Settings.restrictions = rules;
		Board parsed = new Board(rules[0].length);
		return parsed;
	}

*/	
	private static Board board2() {
		int[][] rules = new int[4][];
		rules[Settings.TOP] = new int[] {2, 1};
		rules[Settings.BOTTOM] = new int[] {1, 2};
		rules[Settings.LEFT] = new int[] {2, 1};
		rules[Settings.RIGHT] = new int[] {1, 2};
		Settings.restrictions = rules;
		Board parsed = new Board(rules[0].length);
		return parsed;
	}
}
