package rodriguezProject3;

import java.util.LinkedList;

public class DLLStack<T> implements Stack<T> {

	LinkedList<T> stack;

	public DLLStack() {
		stack = new LinkedList<T>();
	}

	@Override
	public int size() {
		return stack.size();
	}

	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public void push(T v) {
		// Adds an element to the top of the stack.
		stack.add(v);
	}

	@Override
	public T pop() {
		// Returns and remove the top of the stack.
		return stack.removeLast();
	}

	@Override
	public T top() {
		// Return the value of the top of the stack.
		return stack.getLast();
	}
	
	@Override
	public String toString() {
		return stack.toString();
	}

}
