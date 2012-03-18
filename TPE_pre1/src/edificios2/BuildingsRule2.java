package edificios2;

import edificios.Board;
import edificios.BuildingState;
import edificios.Settings;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

import java.awt.Point;

public class BuildingsRule2 implements GPSRule {

	//Cabeza pero works
	private static BoardIteratorStrategy strategy;
	private static Point next;
	static {
		switch (Settings.PATHSTRATEGY) {
			case Settings.STRATEGY_SEQUENCE:
				strategy = new SequenceStrategy();
				break;
			case Settings.STRATEGY_SPIRAL:
				strategy = new SpiralStrategy();
				break;
			case Settings.STRATEGY_MRV:
				strategy = new MRVStrategy();
				break;
			default: throw new IllegalArgumentException("Invalid strategy@Settings.PATHSTRATEGY");
		}
	}
	
	private static Point getNext(GPSState state){
		return strategy.getNext(state);
	}
	
	
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
		if (height == 1) {
			next = getNext(state);
		}
		//board.printBoard();
		if (board.validatePosition(next.x, next.y, height)) {
			return new BuildingState(board.duplicateAndSet(next.x, next.y, height));
		} else{
			throw new NotAppliableException();
		}
	}

}
