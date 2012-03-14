package edificios2;

import edificios.Board;
import edificios.BuildingState;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class BuildingsRule2 implements GPSRule {

	private int height;
	
	public BuildingsRule2(int height) {
		this.height = height;
	}
	
	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Set " + height + " at the first valid position.";
	}

	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		Board board = ((BuildingState) state).getCurrentBoard();
		int size = board.getSize();
		int[][] buildings = board.getBuildings();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (buildings[i][j] == height) {
					break;
				}
				if (buildings[i][j] == 0 && board.validatePosition(i, j, height)) {
					return new BuildingState(board.duplicateAndSet(i, j, height));
				}
			}
		}
		throw new NotAppliableException();
	}

}
