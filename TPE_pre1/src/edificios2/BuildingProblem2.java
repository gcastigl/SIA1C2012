package edificios2;

import edificios.Board;
import edificios.BuildingProblem;
import edificios.BuildingState;
import edificios.Settings;
import gps.api.GPSState;

public class BuildingProblem2 extends BuildingProblem {
	

	public BuildingProblem2(Board board) {
		super(board);
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
		Board board = ((BuildingState) state).getCurrentBoard();
		// TODO: terminar esta funcion
		int n = board.getSize();
		return n * n - board.getBuildingOnBoard();
	}

}
