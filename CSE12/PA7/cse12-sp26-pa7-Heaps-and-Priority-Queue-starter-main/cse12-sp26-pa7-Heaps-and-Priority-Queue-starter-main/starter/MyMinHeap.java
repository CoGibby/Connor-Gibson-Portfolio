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

import java.util.ArrayList;
import java.util.Collection;
/**
 * A generic minimum heap implementation backed by an ArrayList
 * Stores elements in heap order where the smallest element is always at the root
 *
 * @param <E> the type of elements stored, must be Comparable
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    private final static int two = 2;
    protected ArrayList<E> data;

    /**
     * Constructs an empty MyMinHeap.
     */
    public MyMinHeap() {
        this.data = new ArrayList<E>();
    }

    /**
     * -Constructs a MyMinHeap from an existing collection of elements
     * -Throws NullPointerException if the collection or any element is null
     * -Builds the heap using bottom-up heap construction
     *
     * @param collection the collection of elements to build the heap from
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null){
            throw new NullPointerException();
        }
        for (E Element : collection){
            if (Element == null){
                throw new NullPointerException();
            }
        }
        this.data = new ArrayList<E>(collection);
        for (int i = (data.size() - two) / two; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Swaps two elements in the heap at the given indices
     *
     * @param from the index of the first element
     * @param to the index of the second element
     */
    protected void swap(int from, int to) {
        E holdValue = data.get(from);
        data.set(from, data.get(to));
        data.set(to, holdValue);
    }

    /**
     * Returns the parent index of a given index in the heap
     *
     * @param index the index of the child
     * @return the index of the parent
     */
    protected static int getParentIdx(int index){
        int parentIdx = (index - 1) / two;
        return parentIdx;
    }

    /**
     * Returns the left child index of a given index in the heap
     *
     * @param index the index of the parent
     * @return the index of the left child
     */
    protected static int getLeftChildIdx(int index) {
        int leftChildIdx = (two * index) + 1;
        return leftChildIdx;
    }

    /**
     * Returns the right child index of a given index in the heap
     *
     * @param index the index of the parent
     * @return the index of the right child
     */
    protected static int getRightChildIdx(int index){
        int rightChildIdx = (two * index) + two;
        return rightChildIdx;
    }

    /**
     * Returns the index of the smaller child of the given index
     * Returns -1 if the node has no children
     *
     * @param index the index of the parent node
     * @return the index of the smaller child, or -1 if none exist
     */
    protected int getMinChildIdx(int index) {
        int left = getLeftChildIdx(index);
        int right = getRightChildIdx(index);
        if (left >= data.size()){
            return -1;
        }
        if (right >= data.size()){
            return left;
        }
        E leftData = data.get(left);
        E rightData = data.get(right);
        if (leftData.compareTo(rightData) > 0){
            return right;
        }
        return left;
    }

    /**
     * Percolates the element at the given index 
     * upward until heap order is restored
     *
     * @param index the index of the element to percolate up
     */
    protected void percolateUp(int index) {
        while (index > 0) {
            int parent = getParentIdx(index);

            if (data.get(index).compareTo(data.get(parent)) >= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    /**
     * Percolates the element at the given index 
     * downward until heap order is restored.
     *
     * @param index the index of the element to percolate down
     */
    protected void percolateDown(int index){
        while (true) {
            int child = getMinChildIdx(index);

            if (child == -1) {
                break;
            }
            if (data.get(index).compareTo(data.get(child)) <= 0) {
                break;
            }
            swap(index, child);
            index = child;
        }
    }

    /**
     * Deletes and returns the element at the specified index.
     * Restores heap order by percolating up or down as needed.
     *
     * @param index the index of the element to delete
     * @return the removed element
     */
    protected E deleteIndex(int index){
        E removedElem = data.get(index);
        if (index == data.size() - 1){
            data.remove(index);
            return removedElem;
        }
        data.set(index, data.get(data.size() - 1));
        data.remove(data.size()- 1);
        if (index > 0) {
            int parent = getParentIdx(index);
            if (data.get(index).compareTo(data.get(parent)) < 0) {
                percolateUp(index);
                return removedElem;
            }
        }
        percolateDown(index);
        return removedElem;
    }

    /**
     * Inserts a new element into the heap and restores heap order
     *
     * @param element the element to insert
     */
    public void insert(E element){
        if (element == null){
            throw new NullPointerException();
        }
        data.add(element);
        int newElement = data.size() - 1;
        percolateUp(newElement);
    }

    /**
     * Returns the minimum (root) element of the heap
     * Returns null if the heap is empty
     *
     * @return the smallest element, or null if empty
     */
    public E getMin() {
        if (data.size() == 0) {
            return null;
        }
        return data.get(0);
    }

    /**
     * Removes and returns the minimum element of the heap
     * Returns null if the heap is empty
     *
     * @return the removed minimum element, or null if empty
     */
    public E remove() {
        if (data.size() == 0){
            return null;
        }
        return deleteIndex(0);
    }

    /**
     * Returns the number of elements in the heap
     *
     * @return the size of the heap
     */
    public int size(){
        return data.size();
    }

    /**
     * Removes all elements from the heap, leaving it empty
     */
    public void clear() {
        data.clear();
    }
}
