package edificios;

import java.util.HashMap;
import java.util.Map;

import util.Logger;
import edificios.engineimplementation.BuildingsAStarEngine;
import edificios.engineimplementation.BuildingsBFSEngine;
import edificios.engineimplementation.BuildingsDFSEngine;
import edificios.engineimplementation.BuildingsGreedyEngine;
import edificios.engineimplementation.BuildingsHybridIDFSEngine;
import edificios.engineimplementation.BuildingsIDFSEngine;
import edificios2.BuildingProblem2;
import edificios2.MRVStrategy;
import edificios2.SequenceStrategy;
import edificios2.SpiralStrategy;
import gps.GPSEngine;

public class Solver {

	public static void main(String[] args) {
		if (args == null || args.length < 2) {
			printUsage();
			return;
		}
		Settings.PATHSTRATEGY = Settings.STRATEGY_MRV;
		initLogger(args);
		initBoardIteratorStrategy();
		// init engine
		Map<String, GPSEngine> engines = getEngines();
		GPSEngine gps = engines.get(args[0]);
		if (gps == null) {
			Logger.log("Solver", args[0] + " strategy not found", Logger.LEVEL_ERROR);
			return;
		}
		// get level
		Board level;
		try {
			level = BuildingParser.parse(args[1]);
		} catch (Exception e) {
			Logger.log("File", e.getMessage(), Logger.LEVEL_ERROR);
			return;
		}
		// init problem builder
		Map<String, BuildingProblem> problemBuilders = getProblemBuilders(level);
		BuildingProblem prob = null;
		if (args.length == 3 || args.length == 4) {
			prob = problemBuilders.get(args[2]);
		}
		if (prob == null) {
			prob = problemBuilders.get("RED");
		}
		// start engine
		long initialTime = System.currentTimeMillis();
		gps.engine(prob);
		long elapsedTime = System.currentTimeMillis() - initialTime;
		printFormattedElapsedTime(elapsedTime);
	}
	
	private static void initLogger(String[] args) {
		Logger.init();
		if (args.length == 3 || args.length == 4) {
			Map<String, Integer> loggerLevels = getLogLevels();			
			Integer level = loggerLevels.get(args[2]);
			if (level == null && args.length == 4) {
				level = loggerLevels.get(args[3]);	
			}
			if (level == null) {
				Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
			} else {
				Logger.LOG_LEVEL = level;
			}
		} else {
			Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		}
	}
	
	private static void initBoardIteratorStrategy() {
		switch (Settings.PATHSTRATEGY) {
			case Settings.STRATEGY_SEQUENCE:
				Settings.strategy = new SequenceStrategy();
				break;
			case Settings.STRATEGY_SPIRAL:
				Settings.strategy = new SpiralStrategy();
				break;
			case Settings.STRATEGY_MRV:
				Settings.strategy = new MRVStrategy();
				break;
			default: throw new IllegalArgumentException("Invalid strategy@Settings.PATHSTRATEGY");
		}
	}
	
	private static void printUsage() {
		System.out.println("Usage: Algorithm pathToMap ruleSet [Logging level]\n");
		System.out.println("Available Algorithms: " + "[BFS | DFS | IDFS | HIDFS | AStar]");
		System.out.println("Rules Set: " + "[STD | RED]");
		System.out.println("Logging level (optional): " + "[MIN | MED | MAX]");
		System.out.println("Example: java -jar Solver.java DFS res/boards/board3 RED");
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
	
	private static Map<String, GPSEngine> getEngines() {
		Map<String, GPSEngine> startegy = new HashMap<String, GPSEngine>();
		startegy.put("DFS", new BuildingsDFSEngine());
		startegy.put("BFS", new BuildingsBFSEngine());
		startegy.put("IDFS", new BuildingsIDFSEngine());
		startegy.put("HIDFS", new BuildingsHybridIDFSEngine());
		startegy.put("AStar", new BuildingsAStarEngine());
		startegy.put("Greedy", new BuildingsGreedyEngine());
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
	
	private static Map<String, BuildingProblem> getProblemBuilders(Board level){
		Map<String, BuildingProblem> ret = new HashMap<String, BuildingProblem>();
		ret.put("STD", new BuildingProblem(level));
		ret.put("RED", new BuildingProblem2(level));
		return ret;
	}
}
