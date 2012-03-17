package gps;

import edificios.BuildingState;
import gps.api.GPSProblem;
import gps.api.GPSRule;
import gps.api.GPSState;
import gps.exception.NotAppliableException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import util.Logger;

public abstract class GPSEngine {

	protected Collection<GPSNode> open;
	protected Collection<GPSNode> closed;
	protected GPSProblem problem;
	
	public GPSEngine() {
		open = new LinkedList<GPSNode>();
		closed = new LinkedList<GPSNode>();
	}
	
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
				GPSNode currentNode = removeHead();
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

	protected GPSNode removeHead() {
		return ((List<GPSNode>) open).remove(0);
	}
	
	protected boolean isGoal(GPSNode currentNode) {
		return currentNode.getState() != null && currentNode.getState().isGoalState();
	}

	protected boolean explode(GPSNode node) {
		Logger.log("Explode", node, Logger.LEVEL_DEBUG);
		Logger.log("Frontieer", "Nodes in open list: " + open.size(), Logger.LEVEL_DEBUG);
		
		for (GPSRule rule : problem.getRules()) {
			GPSState newState = null;
			try {
				newState = rule.evalRule(node.getState());
//				Logger.log("Applied Rule", rule, Logger.LEVEL_DEBUG);
			} catch (NotAppliableException e) {
//				Logger.log("Invalid Rule", rule, Logger.LEVEL_DEBUG);
				// Do nothing
			}
			if (newState != null
					&& !checkBranch(node, newState)
					&& !checkOpenAndClosed(node.getCost() + rule.getCost(),
							newState)) {
				GPSNode newNode = new GPSNode(newState, node.getCost()
						+ rule.getCost(), node.getDepth() + 1);
				newNode.setParent(node);
				addNode(newNode);
				Logger.log("New State", "New state added to open list", Logger.LEVEL_DEBUG);
			}
		}
		return true;
	}

	private boolean checkOpenAndClosed(Integer cost, GPSState state) {
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

	private boolean checkBranch(GPSNode parent, GPSState state) {
		if (parent == null) {
			return false;
		}
		return checkBranch(parent.getParent(), state) || state.compare(parent.getState());
	}

	public abstract void addNode(GPSNode node);
	
	public abstract String getStrategyName();
}
