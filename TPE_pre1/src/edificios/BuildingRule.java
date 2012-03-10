package edificios;

import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

public class BuildingRule implements GPSRule {

	private int row, col, height;
	
	public BuildingRule(int row, int col, int heigth) {
		this.row = row;
		this.col = col;
		this.height = heigth;
	}
	
	@Override
	public GPSState evalRule(GPSState state) throws NotAppliableException {
//		System.out.println(this.getName() + " --> Evaluating rule...");
		Board currBoard = ((BuildingState) state).getCurrentBoard();
		if(currBoard.validatePosition(row, col, height)){
//			System.out.println("Returned!");
			Board newBoard = currBoard.addAndDuplicate(row, col, height);
//			newBoard.printBoard();
			return new BuildingState(newBoard);
		}
		else{
//			System.out.println(this.getName() + " --> Not applicable");
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

}
