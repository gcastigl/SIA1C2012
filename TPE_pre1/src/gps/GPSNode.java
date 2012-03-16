package gps;

import gps.api.GPSState;

public class GPSNode {

	private GPSState state;
	private GPSNode parent;
	private Integer cost;
	private int h;
	private int depth;

	public GPSNode(GPSState state, Integer cost) {
		this(state, cost, 0);
	}
	
	public GPSNode(GPSState state, Integer cost, int depth) {
		super();
		this.state = state;
		this.cost = cost;
		this.depth = depth;
	}

	public GPSNode getParent() {
		return parent;
	}

	public void setParent(GPSNode parent) {
		this.parent = parent;
	}

	public GPSState getState() {
		return state;
	}

	public Integer getCost() {
		return cost;
	}

	public int getDepth() {
		return depth;
	}
	
	@Override
	public String toString() {
		return state.toString();
	}

	public String getSolution() {
		if (this.parent == null) {
			return this.state.toString();
		}
		return this.parent.getSolution() + "\n" + this.state;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof GPSNode)) {
			return false;
		}
		GPSNode node = (GPSNode) obj;
		return state.compare(node.state);
	}
	
	@Override
	public int hashCode() {
		return 457 * state.hashCode() + 823 * depth;
	}
	
	public int g() {
		return cost;
	}

	public int h() {
		return h;
	}
	
	public void setH(int h) {
		this.h = h;
	}
	
	public int f() {
		return cost + h;
	}
	
}
