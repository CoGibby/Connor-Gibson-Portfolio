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
import java.util.Collection;

/**
 * A priority queue implementation backed by a MyMinHeap
 * Elements with smaller values have higher priority
 *
 * @param <E> the type of elements stored, must be Comparable
 */
public class MyPriorityQueue<E extends Comparable<E>> {

    protected MyMinHeap<E> heap;

    /**
     * Constructs an empty priority queue backed by an empty MyMinHeap
     */
    public MyPriorityQueue() {
        this.heap = new MyMinHeap();
    }

    /**
     * Constructs a priority queue from an existing collection of elements
     * Throws NullPointerException if the collection or any element is null
     *
     * @param collection the collection of 
     * elements to initialize the priority queue
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        if (collection == null){
            throw new NullPointerException();
        }
        for (E element : collection) {
            if (element == null) {
                throw new NullPointerException();
            }
        }
        this.heap = new MyMinHeap<>(collection);
    }

    /**
     * Inserts an element into the priority queue
     * Smaller elements have higher priority
     *
     * @param element the element to insert
     */
    public void push(E element){
        heap.insert(element);
    }

    /**
     * Returns the highest-priority element without removing it
     * Returns null if the priority queue is empty
     *
     * @return the smallest element, or null if empty
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * Removes and returns the highest-priority element
     * Returns null if the priority queue is empty
     *
     * @return the removed smallest element, or null if empty
     */
    public E pop(){
        return heap.remove();
    }

    /**
     * Returns the number of elements in the priority queue
     *
     * @return the number of stored elements
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * Removes all elements from the priority queue, leaving it empty
     */
    public void clear(){
        heap.clear();
    }
}
