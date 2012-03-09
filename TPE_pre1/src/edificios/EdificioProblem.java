package edificios;

import java.util.List;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

public class EdificioProblem implements GPSProblem {

	private Board initBoard;
	private List<GPSRule> rules;
	
	public EdificioProblem() {
		initBoard = EdificioParser.parse("./resources/board.txt");
		initializeRules();
	}
	
	private void initializeRules() {
		int maxHeight = initBoard.getSize();
		for (int i = 0; i < maxHeight; i++) {
			for (int j = 0; j < maxHeight; j++) {
				for (int k = 0; k < maxHeight; k++) {
					rules.add(new EdificioRule(i,j,k));
				}
			}
		}
	}

	@Override
	public GPSState getGoalState() {
		throw new IllegalStateException();
	}

	@Override
	public Integer getHValue(GPSState state) {
		throw new IllegalStateException();
	}

	@Override
	public GPSState getInitState() {
		return new EdificioState(initBoard);
	}

	@Override
	public List<GPSRule> getRules() {
		return rules;
	}

}
