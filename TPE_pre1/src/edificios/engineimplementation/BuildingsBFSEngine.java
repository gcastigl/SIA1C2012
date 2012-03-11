package edificios.engineimplementation;

import gps.GPSEngine;
import gps.GPSNode;

public class BuildingsBFSEngine extends GPSEngine {
	
	@Override
	public void addNode(GPSNode node) {
		if (!open.contains(node))
			open.add(node);
	}
	
	@Override
	public String getStrategyName() {
		return "BFS";
	}

}
