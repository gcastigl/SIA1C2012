package edificios.engineimplementation;

import gps.GPSEngine;
import gps.GPSNode;

import java.util.LinkedList;


public class BuildingsIDFSEngine extends GPSEngine {

	private int currentMaxDepth = 1;
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
	protected boolean explode(GPSNode node) {
		if (node.getDepth() >= currentMaxDepth) {
			resetToInitialState();
			open.add(new GPSNode(problem.getInitState(), 0));
			currentMaxDepth++;
			return true;
		}
		return super.explode(node);
	}
	
	private void resetToInitialState() {
		index = 0;
		indexDepth = 0;
		open.clear();
	}
	
	@Override
	public String getStrategyName() {
		return "Iterative DFS";
	}
}
