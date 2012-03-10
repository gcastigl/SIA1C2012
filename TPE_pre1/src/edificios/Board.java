package edificios;

public class Board {
	private int n;
	private int[][] buildings;
	
	public Board(int n) {
		buildings = new int[n][n];
		this.n = n;
	}
	
	public boolean validatePosition(int row, int col, int height){
		boolean check;
		check = buildings[row][col] == 0 && checkRow(row, height) && checkCol(col, height);
		if (check) {
			buildings[row][col] = height;
			check = checkViewInCol(col, height) && checkViewInRow(row, height);
			buildings[row][col] = 0;
		}
		return check;
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
			if (buildings[i][col] == height) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkViewInRow(int row, int height){
		int viewDistance = Settings.restrictions[Settings.LEFT][row];
		int count = 0;
		boolean satisfies = true;
		int currHeight = -1;
		for (int i = 0; i < n && buildings[row][i] != 0; i++) {
			int value = buildings[row][i];
			if(value != 0 && value > currHeight){
				count++;
				currHeight = value;
				if(count > viewDistance){
					satisfies = false;
				}
			}
			
		}
		viewDistance = Settings.restrictions[Settings.RIGHT][row];		
		count = 0;
		currHeight = -1;
		for (int i = n - 1; i >= 0 && buildings[row][i] != 0; i--) {
			int value = buildings[row][i];
			if (value != 0 && value > currHeight) {
				count++;
				currHeight = value;
				if (count > viewDistance) {
					satisfies = false;
				}
			}
		}
		return satisfies;
	}
	
	private boolean checkViewInCol(int col, int height) {
		int viewDistance = Settings.restrictions[Settings.TOP][col];
		int count = 0;
		int currHeight = -1;
		boolean satisfies = true;
		for (int i = 0; i < n && buildings[i][col] != 0; i++) {
			int value = buildings[i][col];
			if(value != 0 && value > currHeight){
				count++;
				currHeight = value;
				if(count > viewDistance){
					satisfies = false;
				}
			}
		}
		viewDistance = Settings.restrictions[Settings.BOTTOM][col];		
		count = 0;
		currHeight = -1;
		for (int i = n - 1; i >= 0 && buildings[i][col] != 0; i--) {
			int value = buildings[i][col];
			if(value != 0 && value > currHeight){
				count++;
				currHeight = value;
				if(count > viewDistance){
					satisfies = false;
				}
			}
		}
		return satisfies;
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
	
	public int getSize() {
		return n;
	}
	
	public Board addAndDuplicate(int row, int col, int height){
		Board clone = new Board(n);
		for(int i = 0 ; i < n ; i ++){
			clone.buildings[i] = buildings[i].clone();
		}
		clone.buildings[row][col] = height;
		return clone;
	}
	
	
	public void printBoard() {
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		String ret = "";
		String ln = "";
		int i,j;
		for( i = 0 ; i < n; i ++){
			ln += Settings.restrictions[Settings.TOP][i] + " ";
		}
		ret += " | " + ln + " | " + "\n";
		ln = "";
		for( i = 0 ; i < 2*n + 4 ; i ++){
			ln += "-";
		}
		ret += ln + "\n";
		for( i = 0; i < n; i ++){
			ln = "" + Settings.restrictions[Settings.LEFT][i] + "| ";
			for(j = 0 ; j < n ;j ++){
				ln += "" + buildings[i][j] + " ";
			}
			ln += " |" + Settings.restrictions[Settings.RIGHT][i];
			ret += ln + "\n";
		}
		ln = "";
		for( i = 0 ; i < 2*n + 4 ; i ++){
			ln += "-";
		}
		ret += ln + "\n";
		ln = " | ";
		for( i = 0 ; i < n; i ++){
			ln += Settings.restrictions[Settings.BOTTOM][i] + " ";
		}
		ret += ln + "\n";
		return ret;
	}

}
