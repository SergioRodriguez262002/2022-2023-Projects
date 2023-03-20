import java.util.ArrayList;
import java.util.Collections;

public class PriorityQueue {
	
	private ArrayList<Node> queue = new ArrayList<Node>();
	
	public PriorityQueue() {
		
	}
	
	public void insert(Node node) {
		queue.add(node);
		Collections.sort(queue, node);
	}
	
	public void initSource(Node node) {
		queue.add(node);
	}
	
	public Node minimum() { 
		return queue.get(0);
	}
	
	public Node extractMin() {
		Node temp = queue.get(0);
		queue.remove(0);
		return temp;
	}
	
	public void decreaseKey(Node node, int k) {
		queue.get(queue.indexOf(node)).setVd(k);
		Collections.sort(queue, node);
	}
	
	public int size() {
		return queue.size();
	}
	
	public boolean duplicateWord(Node test) {
		return queue.get(queue.indexOf(test)) != null;
	}
		

}
