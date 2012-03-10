package edificios;

import gps.SearchStrategy;

public class BuildingParser {
	
	public static Board parse(String filename){
		int[][] rules = {{1,1,1},{3,3,3},{1,1,1}, {3,3,3}};
		Settings.restrictions = rules;
		Board parsed = new Board(3);
		return parsed;
	}

}
