package rodriguezProject3;

public interface Stack<T> {
	// @return the number of elements in the stack
	public int size();

	// @return true if the stack is empty, false otherwise
	public boolean isEmpty();

	// @param v is the element to add
	public void push(T v);

	// @return the top element on the stack, removing it (null if empty)
	public T pop();

	//
	// @return the value on the top of the stack, (null if empty)
	public T top();

}
