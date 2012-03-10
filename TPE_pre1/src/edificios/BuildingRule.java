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
		((BuildingState) state).getCurrentBoard().validatePosition(row, col, height);
		return null;
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
