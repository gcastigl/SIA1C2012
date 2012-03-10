package edificios;

import util.Logger;
import gps.SearchStrategy;


public class Solver {

	public static void main(String[] args) {
		args = new String[] {"DFS"};
		if(args.length == 0){
			System.out.println("Please declare what searching method you want to use (BFS, DFS)");
			System.out.println("Next time run: java -jar Solver.java BFS");
			return;
		}

		Logger.init();
		Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		
		BuildingProblem prob = new BuildingProblem();
		BuildingEngine eng = new BuildingEngine();
		long initialTime = System.currentTimeMillis();
		if(args[0].equals("DFS")){
			eng.engine(prob, SearchStrategy.DFS);
		} else if(args[0].equals("BFS")){
			eng.engine(prob, SearchStrategy.BFS);
		} else{
			Logger.log("Solver", args[0] + "strategy not found", Logger.LEVEL_TRACE);
			return ;
		}
		long elapsedTime = System.currentTimeMillis() - initialTime;
		Logger.log("Result", "Algorithm took " + elapsedTime + " ms", Logger.LEVEL_TRACE);
		
	}
}
