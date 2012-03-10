package edificios;

import gps.SearchStrategy;

import java.util.HashMap;
import java.util.Map;

import util.Logger;


public class Solver {

	public static void main(String[] args) {
		if(args.length == 0){
			System.out.println("Please declare what searching method you want to use (BFS, DFS)");
			System.out.println("Next time run: java -jar Solver.java BFS");
			return;
		}
		// init logger
		Logger.init();
		Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		
		Map<String, SearchStrategy> startegies = getStreategies();
		SearchStrategy se = startegies.get(args[0]);
		if (se == null) {
			Logger.log("Solver", args[0] + "strategy not found", Logger.LEVEL_ERROR);
			return;
		}
		// init problem engine solver
		BuildingProblem prob = new BuildingProblem();
		BuildingEngine eng = new BuildingEngine();
		long initialTime = System.currentTimeMillis();
		eng.engine(prob, se);
		long elapsedTime = System.currentTimeMillis() - initialTime;
		
		long ms = elapsedTime % 1000;
		long seconds = elapsedTime / 1000;
		String time = "Algorithm took " + seconds + " seconds and " + ms + " ms";
		Logger.log("Timing", time, Logger.LEVEL_TRACE);
	}
	
	private static Map<String, SearchStrategy> getStreategies() {
		Map<String, SearchStrategy> startegy = new HashMap<String, SearchStrategy>();
		startegy.put("DFS", SearchStrategy.DFS);
		startegy.put("BFS", SearchStrategy.BFS);
		return startegy;
	}
}
