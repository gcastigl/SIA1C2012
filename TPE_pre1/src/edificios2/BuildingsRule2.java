package edificios2;

import edificios.Board;
import edificios.BuildingState;
import edificios.Settings;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

import java.awt.Point;

public class BuildingsRule2 implements GPSRule {

	private int height;
	private static BoardIteratorStrategy strategy;
	
	//Cabeza pero works
	private static Point next;

	public BuildingsRule2(int height) {
		this.height = height;
		switch (Settings.PATHSTRATEGY){
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
		//System.out.println("---------------------------");
		//System.out.println(height);
		if(height == 1){
			next = getNext(state);
		}
		//System.out.println("" + next.x + "," + next.y);
		//board.printBoard();
		if(board.validatePosition(next.x, next.y, height)){
			return new BuildingState(board.duplicateAndSet(next.x, next.y, height));
		}
		else{
			throw new NotAppliableException();
		}
		
		
	}
	
	private static Point getNext(GPSState state){
		return strategy.getNext(state);
	}

}
