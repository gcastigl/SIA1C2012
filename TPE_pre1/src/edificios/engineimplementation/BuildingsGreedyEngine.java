package edificios.engineimplementation;

import gps.GPSEngine;
import gps.GPSNode;

import java.util.Iterator;
import java.util.LinkedList;

public class BuildingsGreedyEngine extends GPSEngine {
	
	@Override
	public void addNode(GPSNode node) {
		node.setH(problem.getHValue(node.getState()));
		if (open.isEmpty()) {
			((LinkedList<GPSNode>) open).addFirst(node);
			return;
		}
		Iterator<GPSNode> it = open.iterator();
		int depth = node.getDepth();
		int index = 0;
		while (it.hasNext()) {
			GPSNode n = it.next();
			if (n.getDepth() == depth && node.h() <= n.h()) {
				// if nodes have the same depth, enqueue node  
				// only if the h() value is less than this element
				((LinkedList<GPSNode>) open).add(index, node);
				return;
			} else if (n.getDepth() < depth) {
				// if nodes in queue have less depth than this node, 
				// enqueue it before them
				((LinkedList<GPSNode>) open).add(index, node);
				return;
			}
			index++;
		}
		((LinkedList<GPSNode>) open).add(node);
	}

	@Override
	public String getStrategyName() {
		return "Greedy Search";
	}

}
