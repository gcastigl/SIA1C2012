package edificios;

import gps.SearchStrategy;

public class BuildingParser {
	
	public static Board parse(String filename){
		int[][] rules = {{3,2,1},{1,2,2},{3,2,1}, {1,2,2}};
		Settings.restrictions = rules;
		Settings.strategy = SearchStrategy.DFS;
		Board parsed = new Board(3);
		return parsed;
	}

}
