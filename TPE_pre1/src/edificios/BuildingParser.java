package edificios;

import gps.SearchStrategy;

public class BuildingParser {
	
	public static Board parse(String filename){
		int[][] rules = {{1,2,2},{3,1,2},{1,2,2}, {3,1,2}};
		Settings.restrictions = rules;
		Board parsed = new Board(3);
		return parsed;
	}

}
