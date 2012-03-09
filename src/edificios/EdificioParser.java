package edificios;

public class EdificioParser {
	
	public static Board parse(String filename){
		int[][] rules = {{3,2,1},{1,2,2},{3,2,1}, {1,2,2}};
		Board parsed = new Board(3, rules);
		return parsed;
	}

}
