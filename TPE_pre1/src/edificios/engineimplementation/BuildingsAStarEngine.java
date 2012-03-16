package edificios.engineimplementation;

import gps.GPSEngineAStar;
import gps.GPSNode;
import gps.api.GPSProblem;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BuildingsAStarEngine extends GPSEngineAStar {
	private static final int INITIAL_QUE_SIZE = 100;
	
	@Override
	public void engine(GPSProblem problem) {
		open = new PriorityQueue<GPSNode>(INITIAL_QUE_SIZE, new Comparator<GPSNode>() {
			@Override
			public int compare(GPSNode o1, GPSNode o2) {
				if (o1.f() != o2.f()) {
					return o1.f() - o2.f();
				}
				return o1.h() - o2.h();
			}
		});
		super.engine(problem);
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
