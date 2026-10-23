/*
 * Name: Connor Gibson
 * Email: cogibosn@ucsd.edu
 * PID: A18677169
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */
/*
 * This method creates a deque type of ADT
 */
public class MyDeque<E> implements DequeInterface<E>{

    private static final int defaultCapacity = 10;
    private static final int expandMultiplier = 2; 
    private static final int rearSubtract = 1; 

    Object[] data;
    int size; 
    int rear; 
    int front; 

    /**
     * Constructs a new MyDeque with the specified initial capacity
     *
     * @param initialCapacity Initial size of the underlying array
     */
    public MyDeque(int initialCapacity) { 
        if (initialCapacity < 0 ){
            throw new IllegalArgumentException();
        }
        this.data = new Object[initialCapacity];
        this.rear = 0;
        this.front = 0;
        this.size = 0;
    }

    /**
     * Returns the number of elements currently stored in this deque
     *
     * @return the number of valid elements
     */
    @Override
    public int size(){
        return size;
    }

    /**
     * Doubles the capacity of the underlying array,
     * order of elements is preserved,
     * and front is reset to index 0
     */
    @Override
    public void expandCapacity(){
        if (data.length == 0){
            data = new Object[defaultCapacity];
            rear = 0;
            front = 0;
            return;
        }
        int prevLength = data.length;
        Object[] expandData = new Object[prevLength * expandMultiplier];
        for (int i = 0; i < size; i++){
            expandData[i] = data[(front + i) % prevLength];
        }
        data = expandData;
        front = 0;
        if (size == 0){
            rear = 0;
        } else {
            rear = size - rearSubtract;
        }
    }

    /**
     * Adds the specified element to the front of this deque
     * Expands capacity if the deque is full
     *
     * @param element Element to add
     */
    @Override
    public void addFirst(E element) {
        if (element == null){
            throw new NullPointerException();
        }
        if (size == data.length){
            expandCapacity();
        }
        if (size == 0) {
            data[front] = element; 
        } else {
            front = (front - 1 + data.length) % data.length;
            data[front] = element;
        }
        size++;
    }

    /**
     * Adds the specified element to the rear of this deque
     * Expands capacity if the deque is full
     *
     * @param element the element to add
     */
    @Override
    public void addLast(E element) {
        if (element == null){
            throw new NullPointerException();
        }
        if (size == data.length){
            expandCapacity();
        }
        if (size == 0) {
            data[rear] = element;
        } else {
            rear = (rear + 1) % data.length;
            data[rear] = element;
        }
        size++;
    }

    /**
     * Removes and returns the element at the front of this deque
     * Returns null if the deque is empty
     *
     * @return the removed element, or null if empty
     */
    @Override
    public E removeFirst() {
        if (size == 0){
            return null;
        }
        E removed = (E) data[front];
        data[front] = null;
        if (size == 1) {
            front = 0;
            rear = 0;
        } else {
            front = (front + 1) % data.length;
        }
        size--;
        return removed;
    }

    /**
     * Removes and returns the element at the rear of this deque
     * Returns null if the deque is empty
     *
     * @return the removed element, or null if empty
     */
    @Override
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        E removed = (E) data[rear];
        data[rear] = null;
        if (size == 1) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear - 1 + data.length) % data.length;
        }
        size--;
        return removed;
    }

    /**
     * Returns the element at the front of this deque,
     * or null if the deque is empty
     *
     * @return the front element, or null if empty
     */
    @Override
    public E peekFirst(){
        if (size == 0){
            return null;
        }
        E peek = (E) data[front];
        return peek;
    }

    /**
     * Returns the element at the rear of this deque,
     * or null if the deque is empty
     *
     * @return the rear element, or null if empty
     */
    @Override
    public E peekLast(){
        if (size == 0){
            return null;
        }
        E peek = (E) data[rear];
        return peek;
    }
}
