package edificios;

public class Board {

	private static final int TOP = 0;
	private static final int BOTTOM = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	
	private int n;
	private int[][] buildings;
	
	
	
	public Board(int n) {
		buildings = new int[n][n];
		this.n = n;
	}
	
	public boolean validatePosition(int row, int col, int height){
		boolean check;
		check = checkRow(row, height) && checkCol(col, height) && buildings[row][col] == 0;
		if(check){
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
		int viewDistance = Settings.restrictions[LEFT][row];
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
		viewDistance = Settings.restrictions[RIGHT][row];		
		count = 0;
		currHeight = -1;
		for (int i = n - 1; i >= 0 && buildings[row][i] != 0; i--) {
			int value = buildings[row][i];
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
	
	private boolean checkViewInCol(int col, int height){
		
		int viewDistance = Settings.restrictions[TOP][col];
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
		viewDistance = Settings.restrictions[BOTTOM][col];		
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
		
		Board ret = new Board(n);
		int i,j;
		for( i = 0 ; i < n ; i ++){
			for( j = 0 ; j < n ; j ++){
				ret.buildings[i][j] = buildings[i][j];
			}
		}
		ret.buildings[row][col] = height;
		
		return ret;
	}
	
	
	public void printBoard(){
		
		System.out.println(toString());
		
		
	}
	
	@Override
	public String toString() {
		String ret = "";
		String ln = "";
		int i,j;
		for( i = 0 ; i < n; i ++){
			ln += Settings.restrictions[TOP][i] + " ";
		}
		ret += " | " + ln + " | " + "\n";
		ln = "";
		for( i = 0 ; i < 2*n + 4 ; i ++){
			ln += "-";
		}
		ret += ln + "\n";
		for( i = 0; i < n; i ++){
			ln = "" + Settings.restrictions[LEFT][i] + "| ";
			for(j = 0 ; j < n ;j ++){
				ln += "" + buildings[i][j] + " ";
			}
			ln += " |" + Settings.restrictions[RIGHT][i];
			ret += ln + "\n";
		}
		ln = "";
		for( i = 0 ; i < 2*n + 4 ; i ++){
			ln += "-";
		}
		ret += ln + "\n";
		ln = " | ";
		for( i = 0 ; i < n; i ++){
			ln += Settings.restrictions[BOTTOM][i] + " ";
		}
		ret += ln + "\n";
		return ret;
	}

}
