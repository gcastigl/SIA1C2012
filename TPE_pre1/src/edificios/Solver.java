package edificios;

import gps.SearchStrategy;


public class Solver {

	public static void main(String[] args) {
		BuildingProblem prob = new BuildingProblem();
		BuildingEngine eng = new BuildingEngine();
		eng.engine(prob, SearchStrategy.BFS);
	}
}
