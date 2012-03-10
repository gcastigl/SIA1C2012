package edificios;

import gps.SearchStrategy;

import java.util.HashMap;
import java.util.Map;

import util.Logger;


public class Solver {

	public static void main(String[] args) {
		if(args.length < 2){
			System.out.println("Please declare what searching method you want to use (BFS, DFS) and the logging level");
			System.out.println("Next time run: java -jar Solver.java BFS LEVEL_DEBUG");
			System.out.println("For more information on how to run the solver please read the Readme.md file");
			return;
		}
		
		
		// init logger
		Logger.init();
		
		
		if(args[1].equals("LEVEL_DEBUG"))
			Logger.LOG_LEVEL = Logger.LEVEL_DEBUG;
		else if(args[1].equals("LEVEL_TRACE"))
			Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		else if(args[1].equals("LEVEL_WARNING"))
			Logger.LOG_LEVEL = Logger.LEVEL_WARNING;
		else if(args[1].equals("LEVEL_ERROR"))
			Logger.LOG_LEVEL = Logger.LEVEL_ERROR;
		else{
			Logger.log("Solver", args[1] + " logger mode not found", Logger.LEVEL_ERROR);
			return ;
		}
		
		Map<String, SearchStrategy> startegies = getStreategies();
		SearchStrategy se = startegies.get(args[0]);
		if (se == null) {
			Logger.log("Solver", args[0] + " strategy not found", Logger.LEVEL_ERROR);
			return;
		}
		// init problem engine solver
		BuildingProblem prob = new BuildingProblem();
		BuildingEngine eng = new BuildingEngine();
		long initialTime = System.currentTimeMillis();
		eng.engine(prob, se);
		long elapsedTime = System.currentTimeMillis() - initialTime;
		Logger.log("Result", "Algorithm took " + elapsedTime + " ms", Logger.LEVEL_TRACE);
		
	}
	
	private static Map<String, SearchStrategy> getStreategies() {
		Map<String, SearchStrategy> startegy = new HashMap<String, SearchStrategy>();
		startegy.put("DFS", SearchStrategy.DFS);
		startegy.put("BFS", SearchStrategy.BFS);
		return startegy;
	}
}
