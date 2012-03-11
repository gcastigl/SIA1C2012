package edificios;

import edificios.engineimplementation.BuildingsEngine;
import edificios.engineimplementation.BuildingsHybridIDFSEngine;
import edificios.engineimplementation.BuildingsIDFSEngine;
import gps.GPSEngine;
import gps.SearchStrategy;

import java.util.HashMap;
import java.util.Map;

import util.Logger;


public class Solver {

	public static void main(String[] args) {
		if(args == null || args.length == 0) {
			printUsage();
			return;
		}
		
		// init logger
		Logger.init();
		if (args.length == 2) {
			Map<String, Integer> loggerLevels = getLogLevels();
			Integer level = loggerLevels.get(args[1]);
			if (level == null) {
				Logger.LOG_LEVEL = Logger.LEVEL_OFF;	
			} else {
				Logger.LOG_LEVEL = level;
			}
		} else {
			Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		}
		
		Map<String, SearchStrategy> startegies = getStrategies();
		SearchStrategy se = startegies.get(args[0]);
		if (se == null) {
			Logger.log("Solver", args[0] + " strategy not found", Logger.LEVEL_ERROR);
			return;
		}
		// init problem engine solver
		BuildingProblem prob = new BuildingProblem();
		GPSEngine eng = getEngines().get(se);
		long initialTime = System.currentTimeMillis();
		eng.engine(prob, se);
		long elapsedTime = System.currentTimeMillis() - initialTime;
		printFormattedElapsedTime(elapsedTime);
	}
	
	private static void printFormattedElapsedTime(long elapsedTime) {
		long ms = elapsedTime % 1000;
		long seconds = (elapsedTime / 1000) % 60;
		long minutes = elapsedTime / (60 * 1000);
		String time = "Algorithm took " ;
		if (minutes != 0) {
			time += minutes + " min ";
		}
		time += seconds + " seconds and " + ms + " ms";
		Logger.log("Timing", time, Logger.LEVEL_TRACE);
		System.out.println(time);
	}
	
	private static Map<String, SearchStrategy> getStrategies() {
		Map<String, SearchStrategy> startegy = new HashMap<String, SearchStrategy>();
		startegy.put("DFS", SearchStrategy.DFS);
		startegy.put("BFS", SearchStrategy.BFS);
		startegy.put("IDFS", SearchStrategy.IDFS);
		startegy.put("HIDFS", SearchStrategy.HIDFS);
		return startegy;
	}
	
	private static Map<String, Integer> getLogLevels() {
		Map<String, Integer> loggerLevels = new HashMap<String, Integer>();
		loggerLevels.put("OFF", Logger.LEVEL_OFF);
		loggerLevels.put("LOW", Logger.LEVEL_ERROR);
		loggerLevels.put("MED", Logger.LEVEL_TRACE);
		loggerLevels.put("MAX", Logger.LEVEL_DEBUG);
		return loggerLevels;
	}
	
	private static Map<SearchStrategy, GPSEngine> getEngines() {
		Map<SearchStrategy, GPSEngine> engines = new HashMap<SearchStrategy, GPSEngine>();
		engines.put(SearchStrategy.BFS, new BuildingsEngine());
		engines.put(SearchStrategy.DFS, new BuildingsEngine());
		engines.put(SearchStrategy.IDFS, new BuildingsIDFSEngine());
		engines.put(SearchStrategy.HIDFS, new BuildingsHybridIDFSEngine());
		return engines;
	}
	
	private static void printUsage() {
		System.out.println("Usage: Algorithm [Logging level]");
		System.out.println("Available Algorithms: " + "[BFS | DFS | IDFS | HIDFS]");
		System.out.println("Logging level (optional): " + "[MIN | MED | MAX]");
		System.out.println("Example: java -jar Solver.java DFS MED");
	}
}
