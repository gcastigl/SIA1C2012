package edificios;

import gps.GPSEngine;
import gps.GPSNode;

import java.util.LinkedList;

public class BuildingEngine extends GPSEngine {
	
	@Override
	public void addNode(GPSNode node) {
		switch(strategy){
		case BFS:
			open.add(node);
			break;
		case DFS:
			((LinkedList<GPSNode>) open).addFirst(node);
			break;
		default:
			throw new IllegalStateException();
		}
	}

}
