package edificios.engineimplementation;

import gps.GPSEngine;
import gps.GPSNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BuildingsGreedyEngine extends GPSEngine{
	
private static final int INITIAL_QUEUE_SIZE = 100;
	
	public BuildingsGreedyEngine() {
		open = new PriorityQueue<GPSNode>(INITIAL_QUEUE_SIZE, new Comparator<GPSNode>() {
			@Override
			public int compare(GPSNode o1, GPSNode o2) {
				return o1.h() - o2.h();
			}
		});
	}
	
	@Override
	protected GPSNode removeHead() {
		return ((PriorityQueue<GPSNode>) open).remove();
	}
	
	@Override
	public void addNode(GPSNode node) {
		node.setH(problem.getHValue(node.getState()));
		open.add(node);
	}

	@Override
	public String getStrategyName() {
		return "A*";
	}

}
