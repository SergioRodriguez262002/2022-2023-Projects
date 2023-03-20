import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra {

	public Dijkstra() {

	}

	public static ArrayList<Node> algorithm(String start, String goal, PriorityQueue queue,
			HashMap<String, ArrayList<Node>> map) {

		// On the hashmap the key is a string, as a result the start and stop strings
		// need to be converted to objects for the algorithm to function. Note that the
		// objects are pointers to the objects in the hashmap
		Node startObject = Node.getObject(start, map);
		Node goalObject = Node.getObject(goal, map);
		queue.decreaseKey(startObject, 0);
		ArrayList<Node> S = new ArrayList<Node>();
		S.add(startObject);
		while (queue.size() > 0) {
			Node u = queue.extractMin(); // Pointer to node in queue
			S.add(u);
			Node gFound = relax(u, map.get(u.getWord()), goalObject, queue, S);
			if (gFound == goalObject) {
				S.add(gFound);
				return backTrack(S, gFound);
			}

		}
		return null;

	}

	public static Node relax(Node u, ArrayList<Node> nodes, Node Goal, PriorityQueue queue, ArrayList<Node> S) {
		// all the adjacent nodes are the nodes connected to the word.
		// ex. selene [->|4 silene|, ->|6 serene|]

		int test;
		for (int i = 0; i < nodes.size(); i++) {
			test = u.getVd() + nodes.get(i).getWeight();
			if (test < nodes.get(i).getVd()) {
				queue.decreaseKey(nodes.get(i), test);
				nodes.get(i).setPred(u);
			}

			if (nodes.get(i).equals(Goal)) {
				return nodes.get(i);
			}
		}
		return null;
	}

	public static ArrayList<Node> backTrack(ArrayList<Node> S, Node goal) {
		ArrayList<Node> end = new ArrayList<Node>();
		Node pointerCurrent = goal;
		end.add(pointerCurrent);
		while (pointerCurrent.getVd() > 0) {
			pointerCurrent = pointerCurrent.getPred();
			end.add(pointerCurrent);
		}
		end.remove(0);
		return end;
	}

}
