package edificios.engineimplementation;

import edificios.BuildingProblem;
import gps.GPSEngine;
import gps.GPSNode;
import gps.api.GPSProblem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BuildingsDFSEngine extends GPSEngine {
	
	private Set<GPSNode> visited = new HashSet<GPSNode>();
	
	@Override
	public void engine(GPSProblem problem) {
		((BuildingProblem) problem).invertRules();
		visited.clear();
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
	public String getStrategyName() {
		return "DFS";
	}
}
