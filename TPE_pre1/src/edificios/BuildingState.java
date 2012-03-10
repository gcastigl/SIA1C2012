package edificios;

import gps.api.GPSState;

public class BuildingState implements GPSState {

	private Board currentBoard;
	
	public BuildingState(Board board) {
		this.currentBoard = board;
	}
	
	public Board getCurrentBoard() {
		return currentBoard;
	}
	
	@Override
	public boolean compare(GPSState state) {
		return currentBoard.equals(((BuildingState) state).getCurrentBoard());
	}
	
	@Override
	public boolean isGoalState() {
		int[][] buildings = currentBoard.getBuildings();
		int n = buildings.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (buildings[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

}
