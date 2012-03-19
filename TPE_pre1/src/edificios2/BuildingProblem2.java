package edificios2;

import edificios.Board;
import edificios.BuildingProblem;
import edificios.BuildingState;
import edificios.Settings;
import gps.api.GPSState;

public class BuildingProblem2 extends BuildingProblem {

	//Needed by MRV to access H function to see if it is infinite 
	public static BuildingProblem2 instance;

	public BuildingProblem2(Board board) {
		super(board);
		instance = this;
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
		BuildingState s = (BuildingState) state;
		Board board = ((BuildingState) state).getCurrentBoard();
		if ( checkNoSolution(board)) {
			return Integer.MAX_VALUE;
		}
		int n = board.getSize();
		return n * n - board.getBuildingOnBoard();
	}

	//Used by MRV
	public boolean checkNoSolution(Board board) {
		int[][] buildings = board.getBuildings();
		int n = board.getSize();
		// check top restrictions
		for (int i = 0; i < n; i++) {
			int a = Settings.restrictions[Settings.TOP][i];
			for (int row = 0; row < n; row++) {
				int k = buildings[row][i];
				if (k == 0) {
					continue;
				}
				int minDist = (a + k) - n - 1;
				if (row < minDist) {
					return true;
				}
			}
		}
		// check bottom restrictions
		for (int i = 0; i < n; i++) {
			int a = Settings.restrictions[Settings.BOTTOM][i];
			for (int row = 0; row < n; row++) {
				int k = buildings[row][i];
				if (k == 0) {
					continue;
				}
				int minDist = (a + k) - n - 1;
				if ((n - row - 1) < minDist) {
					return true;
				}
			}
		}
		// check left restrictions
		for (int i = 0; i < n; i++) {
			int a = Settings.restrictions[Settings.LEFT][i];
			for (int col = 0; col < n; col++) {
				int k = buildings[i][col];
				if (k == 0) {
					continue;
				}
				int minDist = (a + k) - n - 1;
				if (col < minDist) {
					return true;
				}
			}
		}
		// check right restrictions
		for (int i = 0; i < n; i++) {
			int a = Settings.restrictions[Settings.RIGHT][i];
			for (int col = 0; col < n; col++) {
				int k = buildings[i][col];
				if (k == 0) {
					continue;
				}
				int minDist = (a + k) - n - 1;
				if ((n - col - 1) < minDist) {
					return true;
				}
			}
		}
		return false;
	}
}
