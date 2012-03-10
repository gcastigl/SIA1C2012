package edificios;

import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;
import util.Logger;

public class BuildingRule implements GPSRule {

	private int row, col, height;
	
	public BuildingRule(int row, int col, int heigth) {
		this.row = row;
		this.col = col;
		this.height = heigth;
	}
	
	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
		Logger.log("Debug", "Eval " + this.getName(), Logger.LEVEL_DEBUG);

		Board currBoard = ((BuildingState) state).getCurrentBoard();
		if (currBoard.validatePosition(row, col, height)) {
//			System.out.println("Returned!");
			Board newBoard = currBoard.addAndDuplicate(row, col, height);
//			newBoard.printBoard();
			return new BuildingState(newBoard);
		}
		else{
			Logger.log("Debug", this.getName() + " --> Not applicable", Logger.LEVEL_DEBUG);
			throw new NotAppliableException();
		}
	}

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Rule: Put: " + height + " in ("+ row + ", " + col + ")";
	}

	@Override
	public String toString() {
		return getName();
	}
}
