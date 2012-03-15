package edificios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import util.Logger;
import edificios.engineimplementation.BuildingsBFSEngine;
import edificios.engineimplementation.BuildingsDFSEngine;
import edificios.engineimplementation.BuildingsHybridIDFSEngine;
import edificios.engineimplementation.BuildingsIDFSEngine;
import exceptions.CorruptFileException;
import gps.GPSEngine;
import gps.SearchStrategy;


public class Solver {
	
	private static final int STANDARD_RULES = 0;
	private static final int REDUCED_RULES=1;

	public static void main(String[] args) {
		if(args == null || args.length == 0) {
			printUsage();
			return;
		}
		
		// init logger
		Logger.init();
		Integer selectedRules = null;
		if (args.length == 3 || args.length == 4) {
			Map<String, Integer> loggerLevels = getLogLevels();
			Map<String, Integer> ruleSet = getRuleSet();
			
			Integer level = loggerLevels.get(args[2]);
			if (level == null) {
				Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
				selectedRules = ruleSet.get(args[2]);
				System.out.println(args[2]);
				System.out.println(selectedRules + "asdf");
			} else {
				Logger.LOG_LEVEL = level;
				selectedRules = ruleSet.get(args[3]);
				System.out.println(args[3]);
				System.out.println(selectedRules);
			}
			
		} else {
			Logger.LOG_LEVEL = Logger.LEVEL_TRACE;
		}
		
		if(selectedRules == null){
			selectedRules = STANDARD_RULES;
		}
		Map<String, SearchStrategy> startegies = getStrategies();
		SearchStrategy se = startegies.get(args[0]);
		if (se == null) {
			Logger.log("Solver", args[0] + " strategy not found", Logger.LEVEL_ERROR);
			return;
		}
		
		if (args[1] == null) {
			Logger.log("Solver", "You must provide a problem number", Logger.LEVEL_ERROR);
			printUsage();
			return;
		}
		
		// init problem engine solver
		Board level = parseLevel(args[1]);
		if (level == null) {
			Logger.log("Level error", "null level returned by level parser", Logger.LEVEL_ERROR);
			return;
		}
		BuildingProblem prob;
		System.out.println("RULES: " + selectedRules);
		if(selectedRules == STANDARD_RULES)
			prob = new BuildingProblem(level);
		else
			prob = new edificios2.BuildingProblem2(level);
		GPSEngine eng = getEngines().get(se);
		long initialTime = System.currentTimeMillis();
		eng.engine(prob);
		long elapsedTime = System.currentTimeMillis() - initialTime;
		printFormattedElapsedTime(elapsedTime);
	}
	
	private static Board parseLevel(String fileName) {
		try{
			return BuildingParser.parse(fileName);			
		}
		catch(CorruptFileException e){
			Logger.log("File", e.getMessage(), Logger.LEVEL_ERROR );
			return null;
		}
		catch(FileNotFoundException e){
			Logger.log("File", e.getMessage(), Logger.LEVEL_ERROR);
			return null;
		}
		catch(IOException e){
			Logger.log("File", e.getMessage(), Logger.LEVEL_ERROR);
			return null;
		}
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
		engines.put(SearchStrategy.BFS, new BuildingsBFSEngine());
		engines.put(SearchStrategy.DFS, new BuildingsDFSEngine());
		engines.put(SearchStrategy.IDFS, new BuildingsIDFSEngine());
		engines.put(SearchStrategy.HIDFS, new BuildingsHybridIDFSEngine());
		return engines;
	}
	
	private static void printUsage() {
		System.out.println("Usage: Algorithm [Logging level] ProblemN");
		System.out.println("Available Algorithms: " + "[BFS | DFS | IDFS | HIDFS]");
		System.out.println("Logging level (optional): " + "[MIN | MED | MAX]");
		System.out.println("Example: java -jar Solver.java DFS MED 2");
	}
	
	private static Map<String, Integer> getRuleSet(){
		Map<String, Integer> ret = new HashMap<String, Integer>();
		ret.put("STD",STANDARD_RULES);
		ret.put("RED", REDUCED_RULES);
		return ret;
	}
}
