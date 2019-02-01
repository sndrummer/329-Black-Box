package misc;

/**
 * A FIFO queue with a max size. Holds collection of Objects that are enqueued and dequeued for processing.
 * If exceeds max size the last
 */
public class Queue {
    Object[] arr;
    int size;
    int first;
    int last;

    /**
     * Queue Constructor, initializes Queue object
     *
     * @param max the max size of the queue
     * @cs329.requires an int value > 0 to initialize the size of the queue
     * @cs329.ensures an empty queue is created of with the maximum size set to @param max
     */
    public Queue(int max) {
        arr = new Object[max];
        size = 0;
        first = 0;
        last = 0;
    }


    /**
     * Returns the current size of the queue
     *
     * @return current size of queue
     * @cs329.requires none
     * @cs329.ensures the current size of the queue is returned
     */
    public int size() {
        return size;
    }

    /**
     * Returns the maximum size of the queue
     *
     * @return the maximum size of the queue
     * @cs329.requires none
     * @cs329.ensures the maximum size of the queue is returned
     */
    public int max() {
        return arr.length;
    }

    /**
     * Adds an Object/element to the back of the queue
     *
     * @param x Object to be placed on the queue
     * @cs329.requires none
     * @cs329.ensures Object x is added to the queue
     */
    public void enqueue(Object x) {
        arr[last] = x;
        last++;
        if (last == arr.length)
            last = 0;
        size++;
    }


    /**
     * Pops the first Object at the front of the queue off the queue and returns it
     *
     * @return the Object at the front of the queue
     */
    public Object dequeue() {
        if (size == 0)
            return null;
        Object x = arr[first];
        first++;
        if (first == arr.length)
            first = 0;
        size--;
        return x;
    }
}
