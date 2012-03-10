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
		Board currBoard = ((BuildingState) state).getCurrentBoard();
		if(currBoard.validatePosition(row, col, height)){
			System.out.println("Returned!");
			return new BuildingState(currBoard.addAndDuplicate(row, col, height));
		}
		else{
			System.out.println("Not appliable");
			throw new NotAppliableException();
		}
	}

	@Override
	public Integer getCost() {
		return 1;
	}

	@Override
	public String getName() {
		return "Poner el numero " + height + " en la posicion (" + row + ", " + col + ").";
	}

}
