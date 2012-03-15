package edificios.engineimplementation;

import edificios.BuildingProblem;
import gps.GPSEngine;
import gps.GPSNode;
import gps.api.GPSProblem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class BuildingsIDFSEngine extends GPSEngine {

	private Set<GPSNode> visited = new HashSet<GPSNode>();
	private int currentMaxDepth;
	
	@Override
	public void engine(GPSProblem problem) {
		((BuildingProblem) problem).invertRules();
		visited.clear();
		currentMaxDepth = 1;
		super.engine(problem);
	}
	
	@Override
	public void addNode(GPSNode node) {
		if (visited.contains(node)) {
			return;
		}
		((LinkedList<GPSNode>) open).addFirst(node);
		visited.add(node);
	}
	
	@Override
	protected boolean explode(GPSNode node) {
		if (node.getDepth() >= currentMaxDepth) {
			open.clear();
			open.add(new GPSNode(problem.getInitState(), 0));
			currentMaxDepth++;
			return true;
		}
		return super.explode(node);
	}
	
	@Override
	public String getStrategyName() {
		return "Iterative DFS";
	}
}
