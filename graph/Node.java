package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public final int building;
	public final List<Node> connected;

	public Node(int building) {
		this.building = building;
		connected = new ArrayList<Node>();
	}

	public boolean isSink() {
		return connected.size() == 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return building + "";
	}
}
