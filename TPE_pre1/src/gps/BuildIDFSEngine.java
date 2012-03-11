package gps;

import java.util.LinkedList;


public class BuildIDFSEngine extends GPSEngine {

	private int currentMaxDepth = 1;
	
	@Override
	public void addNode(GPSNode node) {
		((LinkedList<GPSNode>) open).addFirst(node);
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
}
