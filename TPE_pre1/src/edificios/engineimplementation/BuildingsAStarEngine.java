package edificios.engineimplementation;

import gps.GPSEngine;
import gps.GPSNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BuildingsAStarEngine extends GPSEngine {
	
	private static final int INITIAL_QUE_SIZE = 100;
	
	public BuildingsAStarEngine() {
		open = new PriorityQueue<GPSNode>(INITIAL_QUE_SIZE, new Comparator<GPSNode>() {
			@Override
			public int compare(GPSNode o1, GPSNode o2) {
				if (o1.f() != o2.f()) {
					return o1.f() - o2.f();
				}
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
