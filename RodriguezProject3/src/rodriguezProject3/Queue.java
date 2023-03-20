package rodriguezProject3;

public interface Queue<T> {
	//enqueue, dequeue, first, size, isEmpty
	
	public void enqueue(T v); 
	// Add element to the start of the queue
	
	public T dequeue();
	// Remove and return the element at the end of the queue
	
	public T first();
	// Return the value of the first element of the queue
	
	public int size();
	// Return the size of the queue
	
	public boolean isEmpty();
	// Return t/f for the emptiness of the queue
	
	public String toString();
	

}
