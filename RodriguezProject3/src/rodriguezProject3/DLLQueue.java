package rodriguezProject3;

import java.util.LinkedList;

public class DLLQueue<T> implements Queue<T> {
	LinkedList<T> queue;
	
	public DLLQueue() {
		queue = new LinkedList();
	}

	@Override
	public void enqueue(T v) {
		queue.addFirst(v);
	}

	@Override
	public T dequeue() {
		return queue.removeLast();
	}

	@Override
	public T first() {
		return queue.getFirst();
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}
	
	@Override
	public String toString() {
		return queue.toString();
	}

}
