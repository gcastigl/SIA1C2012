package edificios2;

import edificios.Board;
import edificios.BuildingState;
import gps.api.GPSState;

import java.awt.Point;

public class MRVStrategy implements BoardIteratorStrategy {

	@Override
	public String getName() {
		return "MRV iteration";
	}
	
	@Override
	public Point getNext(GPSState state) {
		Board board = ((BuildingState) state).getCurrentBoard();
		int size = board.getSize();
		int[][] buildings = board.getBuildings();
		int min = Integer.MAX_VALUE;
		Point ret = new Point();
		int i,j, k;
		for( i = 0 ; i < size ; i ++){
			for( j = 0 ; j < size ; j ++){
				if(buildings[i][j] == 0){
					int count = 0;
					for( k=1 ; k <= size ; k ++){
						if(board.validatePosition(i,j, k)){
							buildings[i][j] = k;
							if(BuildingProblem2.instance.getHValue(state) != Integer.MAX_VALUE){
								count++;									
							}
							buildings[i][j] = 0;
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
		return ret;
	}

}
