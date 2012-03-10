package edificios;

import gps.SearchStrategy;


public class Solver {

	public static void main(String[] args) {
		
		if(args.length == 0){
			System.out.println("Please declare what searching method you want to use (BFS, DFS)");
			System.out.println("Next time run: java -jar Solver.java BFS");
			return;
		}
		

		BuildingProblem prob = new BuildingProblem();
		BuildingEngine eng = new BuildingEngine();
		
		if(args[0].equals("DFS")){
			eng.engine(prob, SearchStrategy.DFS);
		} else if(args[0].equals("BFS")){
			eng.engine(prob, SearchStrategy.BFS);
		}else{
			System.out.println(args[0] + "strategy not found");
			return ;
		}
			
		
	}
}
