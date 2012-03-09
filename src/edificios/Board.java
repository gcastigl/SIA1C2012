package edificios;

public class Board {

	private static final int TOP = 0;
	private static final int BOTTOM = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	
	private int n;
	private int[][] buildings;
	private int[][] restrictions;
	
	
	public Board(int n, int[][] restrictions ) {
		buildings = new int[n][n];
		this.restrictions = restrictions;
		this.n = n;
	}
	
	public boolean validatePosition(int row, int col, int height){
		
		return checkRow(row, height) && checkCol(col, height) && checkViewInCol(col, height) && checkViewInRow(row, height);
		
	}
	
	// Repeated values on row
	private boolean checkRow(int row, int height){
		for(int x: buildings[row]){
			if(x == height)
				return false;
		}
		
		return true;
	}
	
	// Repeated values on column
	private boolean checkCol(int col, int height){
		for (int i = 0; i < n; i++) {
			
			if(buildings[i][col] == height)
				return false;
		}
		
		return true;
	}
	
	private boolean checkViewInRow(int row, int height){
		int viewDistance = restrictions[LEFT][row];
		int count = 0;
		boolean satisfies = true;
		for (int i = 0; i < n; i++) {
			int value = buildings[row][i];
			if(value == 0){
				// satisfies = true;
			}
			else if(value < height){
				count++;
				if(count > viewDistance)
					return false;
			}
		}
		if (!satisfies) {
			return false;
		}
		
		viewDistance = restrictions[RIGHT][row];		
		count = 0;
		for (int i = n - 1; i >= 0; i--) {
			int value = buildings[row][i];
			if(value == 0){
				return true;
			}
			else if(value < height){
				count++;
				if(count > viewDistance)
					return false;
			}
		}
		return true;
	}
	
	private boolean checkViewInCol(int col, int height){
		
		int viewDistance = restrictions[TOP][col];
		int count = 0;
		boolean satisfies = true;
		for (int i = 0; i < n; i++) {
			int value = buildings[i][col];
			if(value == 0){
				// satisfies = true;
			}
			else if(value < height){
				count++;
				if(count > viewDistance)
					return false;
			}
		}
		if (!satisfies) {
			return false;
		}
		
		viewDistance = restrictions[BOTTOM][col];		
		count = 0;
		for (int i = n - 1; i >= 0; i--) {
			int value = buildings[i][col];
			if(value == 0){
				return true;
			}
			else if(value < height){
				count++;
				if(count > viewDistance)
					return false;
			}
		}
		return true;
	}
	
	public int[][] getBuildings() {
		return buildings;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Board)) {
			return false;
		}
		Board other = (Board) obj;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (buildings[i][j] != other.buildings[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[][] rules = {{3,2,1},{1,2,2},{3,2,1}, {1,2,2}};
		Board myBoard = new Board(3, rules);
		
		int[][] buildings = myBoard.getBuildings();
		buildings[0][0] = 1;
		buildings[0][1] = 2;
		buildings[0][2] = 3;

		buildings[1][0] = 2;
		buildings[1][1] = 3;
		buildings[1][2] = 1;
		
		buildings[2][0] = 3;
		buildings[2][1] = 1;
//		buildings[2][2] = 2;
		
		
		System.out.println(myBoard.validatePosition(2, 2, 2));
		
	}

	public int getSize() {
		return n;
	}
}
