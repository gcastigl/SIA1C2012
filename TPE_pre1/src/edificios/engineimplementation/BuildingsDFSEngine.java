package edificios.engineimplementation;

import java.util.LinkedList;

import gps.GPSEngine;
import gps.GPSNode;

public class BuildingsDFSEngine extends GPSEngine {

	private int index = 0, indexDepth = 0;
	
	@Override
	public void addNode(GPSNode node) {
		if (indexDepth != node.getDepth()) {
			indexDepth = node.getDepth();
			index = 0;
		}
		((LinkedList<GPSNode>) open).add(index, node);
		index++;
	}
	
	@Override
	public String getStrategyName() {
		return "DFS";
	}
}
