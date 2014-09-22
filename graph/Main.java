package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner goo = new Scanner(System.in);
		int numCities = goo.nextInt();
		for (int i = 0; i < numCities; i++) {
			System.out.println("City " + (i + 1));
			int numBuildings = goo.nextInt();
			Node[] buildings = new Node[numBuildings];
			for (int j = 0; j < buildings.length; j++) {
				buildings[j] = new Node(j + 1);
			}
			for (int j = 0; j < numBuildings; j++) {
				for (int k = 0; k < numBuildings; k++) {
					if (goo.nextInt() == 1) {
						buildings[j].connected.add(buildings[k]);
					}
				}
			}
			List<Node> sinks = new ArrayList<Node>();
			for (int j = 0; j < buildings.length; j++) {
				if (buildings[j].isSink())
					sinks.add(buildings[j]);
			}
			if (sinks.size() > 1) {
				System.out.println("NO PATH");
			} else {
				DNode decision = new DNode(buildings[0], null, buildings);
				List<DNode> leaves = DNode.getLeaves(decision);
				int best = Integer.MAX_VALUE;
				DNode bestLeaf = null;
				for (DNode dNode : leaves) {
					int curr = dNode.getPathLength(dNode);
					if (curr < best) {
						best = curr;
						bestLeaf = dNode;
					}
				}
				if (bestLeaf != null) {
					List<Node> path = DNode.getPath(bestLeaf);
					System.out.println(path);
				}
			}
		}
	}
}
