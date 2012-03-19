package edificios;

import edificios.engineimplementation.BuildingsAStarEngine;
import edificios.engineimplementation.BuildingsBFSEngine;
import edificios.engineimplementation.BuildingsDFSEngine;
import edificios.engineimplementation.BuildingsGreedyEngine;
import edificios.engineimplementation.BuildingsHybridIDFSEngine;
import edificios.engineimplementation.BuildingsIDFSEngine;
import edificios2.BoardIteratorStrategy;
import edificios2.BuildingProblem2;
import edificios2.MRVStrategy;
import edificios2.SequenceStrategy;
import edificios2.SpiralStrategy;
import gps.GPSEngine;

import java.util.HashMap;
import java.util.Map;

import util.Logger;

public class Solver {
	
	private static final String DEFAULT_LOG_LEVEL = "med";
	
	private static final int ALGORITHM 		= 0;
	private static final int BOARD 			= 1;
	private static final int BOARD_STRATEGY = 2;
	private static final int LOG_LEVEL 		= 3;
	
	public static void main(String[] args) {
		if (args == null || args.length < 4) {
			printUsage();
			return;
		}
		initLogger(args[LOG_LEVEL].toLowerCase());
		initBoardIteratorStrategy(args[BOARD_STRATEGY].toLowerCase());
		GPSEngine gps = initGPS(args[ALGORITHM].toLowerCase());
		Board level;
		try {
			level = BuildingParser.parse(args[BOARD]);
		} catch (Exception e) {
			Logger.log("File", e.getMessage(), Logger.LEVEL_ERROR);
			return;
		}
		BuildingProblem problemBuilder = getProblemBuilders("red", level);
		long initialTime = System.currentTimeMillis();
		gps.engine(problemBuilder);
		long elapsedTime = System.currentTimeMillis() - initialTime;
		printFormattedElapsedTime(elapsedTime);
	}
	
	private static void printUsage() {
		System.out.println("Usage: Algorithm pathToMap boardIterator LoggingLevel\n");
		System.out.println("Available Algorithms: " + "[BFS | DFS | IDFS | HIDFS | AStar | Greedy]");
		System.out.println("Available board iterators: " + "[Spiral, MRV, SEQUENCIAL]");
		System.out.println("Logging level: " + "[MIN | MED | MAX]");
		System.out.println("Example: java -jar Solver.java AStar res/boards/board6x6 MED");
		System.out.println("** High logging level may reduce performance **");
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
	
	private static void initLogger(String logParameter) {
		Map<String, Integer> loggerLevels = new HashMap<String, Integer>();
		loggerLevels.put("off", Logger.LEVEL_OFF);
		loggerLevels.put("low", Logger.LEVEL_ERROR);
		loggerLevels.put("med", Logger.LEVEL_TRACE);
		loggerLevels.put("max", Logger.LEVEL_DEBUG);
		Logger.init();
		Integer level = loggerLevels.get(logParameter);
		if (level == null) {
			System.out.println("Unknown logging level: " + logParameter + ". Using Default intead.");
			level = loggerLevels.get(DEFAULT_LOG_LEVEL);
		}
		Logger.LOG_LEVEL = level;
	}
	
	private static void initBoardIteratorStrategy(String boardStrategy) {
		Map<String, BoardIteratorStrategy> boardStrategies = new HashMap<String, BoardIteratorStrategy>();
		boardStrategies.put("mrv", new MRVStrategy());
		boardStrategies.put("spiral", new SpiralStrategy());
		boardStrategies.put("sequential", new SequenceStrategy());
		Settings.strategy = boardStrategies.get(boardStrategy);
		if (Settings.strategy == null) {
			throw new IllegalArgumentException("Invalid strategy: " + boardStrategy);
		}
	}
		
	private static GPSEngine initGPS(String algorithm) {
		Map<String, GPSEngine> engines = new HashMap<String, GPSEngine>();
		engines.put("dfs", new BuildingsDFSEngine());
		engines.put("bfs", new BuildingsBFSEngine());
		engines.put("idfs", new BuildingsIDFSEngine());
		engines.put("hidfs", new BuildingsHybridIDFSEngine());
		engines.put("astar", new BuildingsAStarEngine());
		engines.put("greedy", new BuildingsGreedyEngine());
		GPSEngine gps = engines.get(algorithm);
		if (gps == null) {
			String error = algorithm + " algorithm not found";
			Logger.log("Solver", error, Logger.LEVEL_ERROR);
			throw new IllegalArgumentException(error);
		}
		return gps;
	}
	
	private static BuildingProblem getProblemBuilders(String builder, Board level){
		Map<String, BuildingProblem> builders = new HashMap<String, BuildingProblem>();
		builders.put("std", new BuildingProblem(level));
		builders.put("red", new BuildingProblem2(level));
		BuildingProblem problemBuilder = builders.get(builder); 
		if (problemBuilder == null) {
			throw new IllegalArgumentException("Unknown " + builder + " type");
		}
		return problemBuilder;
	}
}
