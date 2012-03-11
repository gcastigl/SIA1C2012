package edificios.engineimplementation;

import gps.GPSEngine;
import gps.GPSNode;
import gps.api.GPSProblem;

import java.util.HashSet;
import java.util.Set;

public class BuildingsBFSEngine extends GPSEngine {
	
	private Set<GPSNode> visited = new HashSet<GPSNode>();
	
	@Override
	public void engine(GPSProblem problem) {
		visited.clear();
		super.engine(problem);
	}
	
	@Override
	public void addNode(GPSNode node) {
		if (!visited.contains(node)) {
			open.add(node);
			visited.add(node);
		}
	}
	
	@Override
	public String getStrategyName() {
		return "BFS";
	}

}
