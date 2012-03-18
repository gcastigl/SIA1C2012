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
	
	private static int[][] directions = { { -1, 0 } , { 0 , -1} , { 1, 0 } , { 0 , 1}};
	
	//Cabeza pero works
	private static Point next;

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
		Board board = ((BuildingState) state).getCurrentBoard();
		Point ret = null;
		int size = board.getSize();
		int[][] buildings = board.getBuildings();
		switch (Settings.PATHSTRATEGY){
		case Settings.SEQUENCE:
			for (int i = 0; i < size && ret == null; i++) {
				for (int j = 0; j < size && ret == null; j++) {
					if (buildings[i][j] == 0) {
						ret = new Point(i,j);
					}
				}
			}
			break;
		case Settings.SPIRAL:
			int dir = 0;
			int currSize = 1;
			int x = size/2;
			int y = size/2;
			int traversed = 0;
			int parity = 0;
			while(buildings[x][y] != 0){
				x += directions[dir][0];
				y += directions[dir][1];
				traversed ++;
				if(traversed == currSize){
					traversed = 0;
					dir ++;
					dir = dir % 4;
					parity++;
					if(parity == 2){
						parity = 0;
						currSize++;
					}
				}
			}
			ret = new Point(x, y);
			break;
		case Settings.MRV:
			int min = Integer.MAX_VALUE;
			ret = new Point();
			int i,j, k;
			for( i = 0 ; i < size ; i ++){
				for( j = 0 ; j < size ; j ++){
					if(buildings[i][j] == 0){
						int count = 0;
						for( k=1 ; k <= size ; k ++){
							if(board.validatePosition(i,j, k)){
								count++;
							}
						}
						if( count < min ){
							min = count;
							ret.x = i;
							ret.y = j;
						}
					}
				}
			}
			break;
		}
		return ret;
			
	}

}
