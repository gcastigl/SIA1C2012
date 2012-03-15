package edificios;

public class LevelGenerator {
	
	static int [][] m;
	//static int [] numbers = { 3, 7, 2, 1, 4, 5, 6, 3};
	static int [] numbers = { 1,2,3};
	static int dim = 7;
	
	
	public static void main(String[] args) {
		
		m = new int[dim][dim];
		int i,j,k;
		for( i = 0 ; i < dim ; i ++){
			for(j = 0 ; j < dim; j ++) {
				m[i][j] = 0;
			}
		}
		int a = fillSpace( 0, 0);
		System.out.println(a);
		for( i = 0 ; i < dim ; i ++){
			String pr = "";
			for( j = 0 ; j < dim; j ++){
				pr +=m[i][j] + ",";
			}
			System.out.println(pr);
		}
		System.out.println("FILE");
		System.out.println("-------------");
		System.out.println(dim);
		for( i = 0 ; i < dim ; i ++){
			
		}
	}
	
	public static int fillSpace(  int r, int c){
		if( c == dim){
			
			return fillSpace(r+1, 0);
		}
		if( r == dim){
			return 1;
		}
		int k;
		for( k = 1 ; k <=dim; k++ ){
			if(isValid(r,c,k)){
				m[r][c] = k;
				if(fillSpace(r, c+1) == 1)
					return 1;
				m[r][c] = 0;
			}
		}
		return 0;
	}
	
	public static boolean isValid( int r, int c, int k){
		int i;
		for( i = 0 ; i < r ; i ++){
			if(m[i][c] == k)
				return false;
		}
		for( i = 0 ; i < c ; i ++){
			if(m[r][i] == k){
				return false;
			}
		}
		return true;
	}
	

}
