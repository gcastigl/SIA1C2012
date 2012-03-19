package heuristics;

import edificios.Board;
import edificios.BuildingState;
import gps.api.GPSState;

public class RestrictionDensityHeuristic extends Heuristic{
	
	@Override
	public Integer getH(GPSState state) {
		Board board = ((BuildingState) state).getCurrentBoard();
		if (checkNoSolution(board)) {
			return Integer.MAX_VALUE;
		}
		int n = board.getSize();
		int[][] buildings = board.getBuildings();
		int i,j, k = 0;
		double acum = 0;
		for( i = 0 ; i < n ; i ++){
			for( j = 0 ; j < n ; j ++ ){
				if( buildings[i][j] != 0 ){
					int count = checkHeightOptions(board, i, j);
					acum += count ;
					k++;
				}
			}
		}
		acum/=k;
		int toSubtract1 = (int)(((double)(acum)/(double)(n))*5);
		int toSubtract2 = (int)(((double)(k)/(double)(n*n))*5);
		//System.out.println("1:" + toSubtract1);
		//System.out.println("2:" + toSubtract2);
		return 10*(n * n - board.getBuildingOnBoard());
		//return 10*(n * n + toSubtract1);
	}

}
