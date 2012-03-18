package edificios2;

import edificios.Board;
import edificios.BuildingState;
import gps.api.GPSState;

import java.awt.Point;

public class SpiralStrategy implements BoardIteratorStrategy {

	private static final int[][] DIRECTIONS = {{-1, 0}, {0 , -1}, {1, 0}, {0 , 1}};
	
	@Override
	public Point getNext(GPSState state) {
		Board board = ((BuildingState) state).getCurrentBoard();
		int size = board.getSize();
		int[][] buildings = board.getBuildings();
		
		int dir = 0;
		int currSize = 1;
		int x = size/2;
		int y = size/2;
		int traversed = 0;
		int parity = 0;
		while(buildings[x][y] != 0){
			x += DIRECTIONS[dir][0];
			y += DIRECTIONS[dir][1];
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
		return new Point(x, y);
	}

}
