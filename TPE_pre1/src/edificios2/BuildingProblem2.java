package edificios2;

import edificios.Board;
import edificios.BuildingProblem;
import edificios.BuildingState;
import gps.api.GPSState;
import heuristics.Heuristic;

public class BuildingProblem2 extends BuildingProblem {

	// Needed by MRV to access H function to see if it is infinite
	public static BuildingProblem2 instance;
	private Heuristic heuristic;
	
	public BuildingProblem2(Board board, Heuristic heur) {
		super(board);
		instance = this;
		heuristic = heur;
	}

	@Override
	protected void initializeRules() {
		int maxHeight = initBoard.getSize();
		for (int i = 1; i <= maxHeight; i++) {
			rules.add(new BuildingsRule2(i));
		}
	}

	@Override
	protected void sortRules() {
		// nothing for now
	}

	@Override
	public Integer getHValue(GPSState state) {
		return heuristic.getH(state);
	}
}
