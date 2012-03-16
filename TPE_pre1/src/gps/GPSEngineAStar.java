package gps;

import edificios.BuildingState;
import gps.api.GPSProblem;

import java.util.PriorityQueue;

import util.Logger;

// TODO: this class is horribly because it repeats almost all the code from GPSEngine class
public abstract class GPSEngineAStar extends GPSEngine {

	protected PriorityQueue<GPSNode> open;
	
	public void engine(GPSProblem problem) {
		Logger.log("GPSEngine", "Solving with " + getStrategyName() + " the following board \n" + problem.getInitState().toString(), Logger.LEVEL_TRACE);
		Logger.log("Rules:", problem.getRules(), Logger.LEVEL_TRACE);
		
		this.problem = problem;
		GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
		boolean finished = false;
		boolean failed = false;
		long explosionCounter = 0;

		open.add(rootNode);
		while (!failed && !finished) {
			if (open.size() <= 0) {
				Logger.log("GSPEngine", "Algorithm failed - Empty open list", Logger.LEVEL_TRACE);
				failed = true;
			} else {
				GPSNode currentNode = open.remove();
				closed.add(currentNode);
				if (isGoal(currentNode)) {
					BuildingState state = (BuildingState) currentNode.getState();
					Logger.log("Solution", "\n\n" + state.getCurrentBoard(), Logger.LEVEL_TRACE);
					finished = true;
				} else {
					explosionCounter++;
					explode(currentNode);
				}
			}
		}

		if (finished) {
			Logger.log("GPSEngine", "OK! solution found!", Logger.LEVEL_TRACE);
			Logger.log("GPSEngine", "Expanded nodes: " + explosionCounter, Logger.LEVEL_TRACE);
			Logger.log("GPSEngine", "Frontieer nodes: " + open.size(), Logger.LEVEL_TRACE);
			Logger.log("GPSEngine", "Total generated nodes: " + (open.size() + closed.size()), Logger.LEVEL_TRACE);
		} else if (failed) {
			Logger.log("GSPEngine", "FAILED! solution not found!", Logger.LEVEL_ERROR);
		}
	}
	
}
