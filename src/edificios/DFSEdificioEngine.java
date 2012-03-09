package edificios;

import gps.GPSEngine;
import gps.GPSNode;

import java.util.LinkedList;

public class DFSEdificioEngine extends GPSEngine {

	@Override
	public void addNode(GPSNode node) {
		((LinkedList<GPSNode>) open).addFirst(node);
	}

}
