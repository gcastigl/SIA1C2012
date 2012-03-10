package gps;

import edificios.BuildingState;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.Logger;

public abstract class GPSEngine {

	protected List<GPSNode> open = new LinkedList<GPSNode>();
	protected List<GPSNode> closed = new ArrayList<GPSNode>();
	protected GPSProblem problem;
	// Use this variable in the addNode implementation
	protected SearchStrategy strategy;

	public void engine(GPSProblem myProblem, SearchStrategy myStrategy) {
		problem = myProblem;
		strategy = myStrategy;

		GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
		boolean finished = false;
		boolean failed = false;
		long explosionCounter = 0;

		open.add(rootNode);
		while (!failed && !finished) {
			if (open.size() <= 0) {
				Logger.log("GSPEngine", "Algorithm failed", Logger.LEVEL_TRACE);
				failed = true;
			} else {
				GPSNode currentNode = open.get(0);
				closed.add(currentNode);
				open.remove(0);
				if (isGoal(currentNode)) {
					BuildingState state = (BuildingState) currentNode.getState();
					Logger.log("Solution", "\n" + state.getCurrentBoard(), Logger.LEVEL_TRACE);
					finished = true;
				} else {
//					Logger.log("Debug", "------ Expanding node ------\n", Logger.LEVEL_DEBUG);
//					Logger.log("Debug", currentNode.getState().toString(), Logger.LEVEL_DEBUG);
//					Logger.log("Debug", "-----------------------------", Logger.LEVEL_DEBUG);
					explosionCounter++;
					explode(currentNode);
				}
			}
		}

		if (finished) {
			Logger.log("GPSEngine", "OK! solution found!", Logger.LEVEL_TRACE);
			Logger.log("GPSEngine", "Expanded nodes: " + explosionCounter, Logger.LEVEL_TRACE);
		} else if (failed) {
			Logger.log("GSPEngine", "FAILED! solution not found!", Logger.LEVEL_ERROR);
		}
	}

	private  boolean isGoal(GPSNode currentNode) {
		return currentNode.getState() != null
				&& currentNode.getState().isGoalState();
		// && currentNode.getState().compare(problem.getGoalState());
	}

	private  boolean explode(GPSNode node) {
		if(problem.getRules() == null){
			System.err.println("No rules!");
			return false;
		}
		for (GPSRule rule : problem.getRules()) {
			GPSState newState = null;
			try {
				newState = rule.evalRule(node.getState());
			} catch (NotAppliableException e) {
				// Do nothing
			}
			if (newState != null
					&& !checkBranch(node, newState)
					&& !checkOpenAndClosed(node.getCost() + rule.getCost(),
							newState)) {
				GPSNode newNode = new GPSNode(newState, node.getCost()
						+ rule.getCost());
				newNode.setParent(node);
				addNode(newNode);
			}
		}
		return true;
	}

	private  boolean checkOpenAndClosed(Integer cost, GPSState state) {
		for (GPSNode openNode : open) {
			if (openNode.getCost() < cost && openNode.getState().compare(state)) {
				return true;
			}
		}
		for (GPSNode closedNode : closed) {
			if (closedNode.getCost() < cost && closedNode.getState().compare(state)) {
				return true;
			}
		}
		return false;
	}

	private  boolean checkBranch(GPSNode parent, GPSState state) {
		if (parent == null) {
			return false;
		}
		return checkBranch(parent.getParent(), state)
				|| state.compare(parent.getState());
	}

	public abstract  void addNode(GPSNode node);
	
}
