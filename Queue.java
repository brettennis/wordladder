/**
	This class implements a circular array.
	It expands if the queue becomes full.
**/
public class Queue {
	private QNode [] queue;	// Array that stores the queue elements.
	public int front;		// Index pointing to the front of the queue.
	public int end;			// Index pointing to the element that is 
								// one less than the end of the queue.
	private int numElements;// The number of elements currently stored in the queue.
	private int size;		// The capacity of the allocated array. If the number
								// of elements reaches this capacity, we need to expand.

	public Queue() {
		numElements = 0;
		size = 1000;
		front = size - 1;
		end = size - 1;
		queue = new QNode[size];
	}
	
	/**
		This function enqueues a new element p into the queue. 
		This also expands the array if it is full.
	**/
	public void enqueue(QNode p) {
		if (numElements == size) {
    		QNode[] newqueue = new QNode[size*2]; // new array
			int newsize = size * 2;

    		int j = front; // points to previous array
			int k = newsize - 1; // points to new array
    		for (int i = 0; i < size; i++) {
    		    newqueue[k] = queue[j];
    	    	j = (j - 1 + size) % size;
				k--;
	    	}

			queue = newqueue;
			end = size - 1;
			front = newsize - 1;
			size = newsize;
		}
		queue[end] = p;
		end = (end - 1 + size) % size;
		numElements++;
	}

	/**
		This function removes and returns the end front element in the queue.
	**/
	public QNode dequeue() {
		if (numElements == 0) {
			return null;
		}
		QNode ret = queue[front];
       	front = (front - 1 + size) % size;
		numElements--;
    	return ret;
	}

	public boolean isEmpty() {
		if (numElements == 0) 
			return true;
		return false;
	}

	public void print() {
		int i;
    	for (i = front; i != end; i = (i - 1 + size) % size) {
    		System.out.println(queue[i]);
		}
	}
	
}
