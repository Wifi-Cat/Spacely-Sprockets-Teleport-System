package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DNode {
	public static boolean pathContains(final DNode node, final Node[] buildings) {
		final Set<Node> nodeSet = new HashSet<Node>();
		DNode current = node;
		while (current != null) {
			nodeSet.add(current.root);
			current = current.parent;
		}
		return nodeSet.size() == buildings.length;
	}

	List<DNode> children;
	Node root;
	DNode parent;

	boolean isDone;

	public DNode(final Node root, final DNode parent, final Node[] buildings) {
		this.parent = parent;
		this.root = root;
		isDone = false;
		if (root.isSink()) {
			isDone = true;
		} else if (pathContains(this, buildings)) {
			isDone = true;
		}
		if (!isDone) {
			for (final Node node : root.connected) {
				children.add(new DNode(node, this, buildings));
			}
		}
	}

	public static List<DNode> getLeaves(DNode root) {
		ArrayList<DNode> leaves = new ArrayList<DNode>();
		getLeaves(root, leaves);
		return leaves;
	}

	private static void getLeaves(DNode current, List<DNode> leaves) {
		if (current.children.size() == 0) {
			// leaf
			leaves.add(current);
		} else {
			for (DNode dNode : current.children) {
				getLeaves(dNode, leaves);
			}
		}
	}

	public static int getPathLength(DNode leaf) {
		DNode current = leaf;
		int counter = 1;
		while (current != null) {
			counter++;
			current = current.parent;
		}
		return counter;
	}

	public static List<Node> getPath(DNode leaf) {
		DNode current = leaf;
		LinkedList<Node> returnable = new LinkedList<Node>();
		for (int i = 0; i < getPathLength(leaf); i++) {
			returnable.add(leaf.root);
			current = current.parent;
		}
		List<Node> reversed = new ArrayList<Node>(returnable.size());
		for (int i = returnable.size() - 1; i >= 0; i--) {
			reversed.add(returnable.get(i));
		}
		return reversed;
	}
}
