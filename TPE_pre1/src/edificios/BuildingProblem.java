package edificios;

import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;

import java.util.LinkedList;
import java.util.List;

public class BuildingProblem implements GPSProblem {

	private Board initBoard;
	private List<GPSRule> rules;
	
	public BuildingProblem() {
		initBoard = BuildingParser.parse("./resources/board.txt");
		initializeRules();
	}
	
	private void initializeRules() {
		rules = new LinkedList<GPSRule>();
		int maxHeight = initBoard.getSize();
		for (int i = 0; i < maxHeight; i++) {
			for (int j = 0; j < maxHeight; j++) {
				for (int k = 1; k <= maxHeight; k++) {
					rules.add(new BuildingRule(i,j,k));
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
		return new BuildingState(initBoard);
	}

	@Override
	public List<GPSRule> getRules() {
		return rules;
	}
	
	@Override
	public String toString() {
		return "\n" + initBoard.toString();
	}

}
