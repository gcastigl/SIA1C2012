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
		sortRules();
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

	private void sortRules() {
		sortTop();
		sortBottom();
		sortLeft();
		sortRight();
	}
	
	private void sortTop() {
		int topHeigth = initBoard.getSize();
		int[] topRules = Settings.restrictions[Settings.TOP];
		for (int i = 0; i < topRules.length; i++) {
			if (topRules[i] == 1) {
				setRuleFirst(0, i, topHeigth);
			}
		}
	}
	
	private void sortBottom() {
		int topHeigth = initBoard.getSize();
		int[] bottomRules = Settings.restrictions[Settings.BOTTOM];
		for (int i = 0; i < bottomRules.length; i++) {
			if (bottomRules[i] == 1) {
				setRuleFirst(topHeigth - 1, i, topHeigth);
			}
		}
	}
	
	private void sortLeft() {
		int topHeigth = initBoard.getSize();
		int[] leftRules = Settings.restrictions[Settings.LEFT];
		for (int i = 0; i < leftRules.length; i++) {
			if (leftRules[i] == 1) {
				setRuleFirst(i, 0, topHeigth);
			}
		}
	}

	private void sortRight() {
		int topHeigth = initBoard.getSize();
		int[] rightRules = Settings.restrictions[Settings.RIGHT];
		for (int i = 0; i < rightRules.length; i++) {
			if (rightRules[i] == 1) {
				setRuleFirst(i, topHeigth - 1, topHeigth);
			}
		}
	}

	private void setRuleFirst(int row, int col, int height) {
		int index = 0;
		for (GPSRule r: rules) {
			BuildingRule br = (BuildingRule) r;
			if (br.getRow() == row && br.getCol() == col && 
				br.getHeight() == height) {
				break;
			}
			index++;
		}
		GPSRule r = rules.remove(index);
		((LinkedList<GPSRule>) rules).addLast(r);
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
